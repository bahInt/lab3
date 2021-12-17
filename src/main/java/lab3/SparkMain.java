package lab3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;

import java.util.Map;


public class SparkMain {
    private static final String COMMA = ",";
    private static final String QUOTE = "\"";
    private static final String AIRPORT_DESCRIPTION_LINE = "Code";
    private static final String FLIGHTS_DESCRIPTION_LINE = "YEAR";
    private static final int ORIGIN_AIRPORT_ID_COLUMN = 11;
    private static final int DEST_AIRPORT_ID_COLUMN = 14;
    private static final int DELAY_COLUMN = 18;
    private static final int CANCELLED_COLUMN = 19;
    private static final int FLIGHT_COUNTING = 1;

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> airportsFile = sc.textFile("L_AIRPORT_ID.csv");
        JavaRDD<String> flightsFile = sc.textFile("664600583_T_ONTIME_sample.csv");
        JavaPairRDD<Tuple2<Integer, Integer>, AirportSerializable> flightsPair = flightsFile.filter(line -> !line.contains(FLIGHTS_DESCRIPTION_LINE)).mapToPair(SparkMain::makeFlightsPair);
        JavaPairRDD<Tuple2<Integer, Integer>, FlightSerializable> reducedFlightsData = reduceFlightsPair(flightsPair);
        Map<Integer, String> stringAirportDataMap = makeAirportsPair(airportsFile).collectAsMap();
        final Broadcast<Map<Integer, String>> airportsBroadcasted = sc.broadcast(stringAirportDataMap);
        JavaRDD<String> resultStatistics = mapStatistics(reducedFlightsData, airportsBroadcasted);
    }



    private static JavaPairRDD<Integer, String> makeAirportsPair(JavaRDD<String> airportsFile) {
        return airportsFile.filter(line -> !line.contains(AIRPORT_DESCRIPTION_LINE)).mapToPair(line -> {
            line = line.replace(QUOTE, "");
            int commaIndex = line.indexOf(COMMA);
            Integer airportID = Integer.valueOf(line.substring(commaIndex));
            String airportName = line.substring(commaIndex + 1);
            return new Tuple2<>(airportID, airportName);
        });
    }

    private static float getDelay(String line) {
        try {
            return Float.parseFloat(line);
        } catch (NumberFormatException ignored) {
            return 0.f;
        }
    }

    private static Tuple2<Tuple2<Integer, Integer>, AirportSerializable> makeFlightsPair(String line) {
            line = line.replace(QUOTE, "");
            String[] flightsDataTable = line.split(COMMA);
            int departureAirportID = Integer.parseInt(flightsDataTable[ORIGIN_AIRPORT_ID_COLUMN]);
            int destinationAirportID = Integer.parseInt(flightsDataTable[DEST_AIRPORT_ID_COLUMN]);
            float delay = getDelay(flightsDataTable[DELAY_COLUMN]);
            boolean flightCancellation= flightsDataTable[CANCELLED_COLUMN].isEmpty();

            return new Tuple2<>(
                    new Tuple2<>(departureAirportID, destinationAirportID),
                    new AirportSerializable(departureAirportID, destinationAirportID, delay, flightCancellation)
            );
    }

    private static JavaPairRDD<Tuple2<Integer, Integer>, FlightSerializable> reduceFlightsPair(JavaPairRDD<Tuple2<Integer, Integer>, AirportSerializable> flightsPair){
        return flightsPair.combineByKey(flight ->
                new FlightSerializable(
                        flight.getDelay(),
                        flight.checkDelay(),
                        flight.checkCancellation(),
                        FLIGHT_COUNTING
                ),
                FlightSerializable::addValue,
                FlightSerializable::add
        );
    }

    private static JavaRDD<String> mapStatistics(JavaPairRDD<Tuple2<Integer, Integer>, FlightSerializable> reducedFlightsData,
                                                final Broadcast<Map<Integer, String>> airportsBroadcasted) {
        return reducedFlightsData.map(flight -> {
            Map<Integer, String> departureDestinationAirports = airportsBroadcasted.value();
            Tuple2<Integer, Integer> key = flight._1();
            String departureAirport = departureDestinationAirports.get(key._1);
            String destinationAirport = departureDestinationAirports.get(key._2);
            float maxDelayTime = flight._2.getMaxDelayTime();
            float delayedFlights = flight._2.getDelayedFlights();
            float cancelledFlights = flight._2.getCancelledFlights();
            int amountOfFlights = flight._2.getAmountOfFlights();
            float delayedAndCancelledPercentage = ((delayedFlights + cancelledFlights) / amountOfFlights) * 100;

            
            }
        )
    }
}