package lab3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;


public class SparkMain {
    private static final String COMMA = ",";
    private static final String QUOTE = "\"";
    private static final String AIRPORT_DESCRIPTION_LINE = "Code";
    private static final String FLIGHTS_DESCRIPTION_LINE = "YEAR";
    private static final int ORIGIN_AIRPORT_ID_COLUMN = 11;
    private static final int DEST_AIRPORT_ID_COLUMN = 14;
    private static final int DELAY_COLUMN = 18;
    private static final int CANCELLED_COLUMN = 19;

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> airportsFile = sc.textFile("L_AIRPORT_ID.csv");
        JavaRDD<String> flightsFile = sc.textFile("664600583_T_ONTIME_sample.csv");

        JavaPairRDD<Integer, String> airportsPair = makeAirportsPair(airportsFile);
        JavaPairRDD<Tuple2<Integer, Integer>, AirportSerializable> flightsPair = flightsFile.filter(line -> !line.contains(FLIGHTS_DESCRIPTION_LINE)).mapToPair(SparkMain::makeFlightsPair);
        JavaPairRDD<Tuple2<Integer, Integer>> reducedFlightsData = reduceFlightsPair(flightsPair);

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

    private static JavaPairRDD<Tuple2<Integer, Integer>> reduceFlightsPair(JavaPairRDD<Tuple2<Integer, Integer>, AirportSerializable> flightsPair){
        
    }
}