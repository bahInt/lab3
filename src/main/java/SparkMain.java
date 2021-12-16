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
    private static final int ORIGIN_AIRPORT_ID = 11;
    private static final int DEST_AIRPORT_ID = 14;

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> airportsFile = sc.textFile("L_AIRPORT_ID.csv");
        JavaRDD<String> flightsFile = sc.textFile("664600583_T_ONTIME_sample.csv");

        JavaPairRDD<Integer, String> airportsPair = makeAirportsPair(airportsFile);
        JavaPairRDD<Integer, Integer> flightsPair = makeFlightsPair(flightsFile);

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

    private static JavaPairRDD<Integer, Integer> makeFlightsPair(JavaRDD<String> flightsFile) {
        return flightsFile.filter(line -> !line.contains(FLIGHTS_DESCRIPTION_LINE)).mapToPair(line -> {
            String[] flightsDataTable = line.split(COMMA);
            int departureAirportID = Integer.parseInt(flightsDataTable[ORIGIN_AIRPORT_ID]);
            int destinationAirportID = Integer.parseInt(flightsDataTable[DEST_AIRPORT_ID]);
            float delay = getDelay(flightsDataTable[DELAY_ID]);
            boolean isFlightCancelled = flightsDataTable[]
        });
    }
}