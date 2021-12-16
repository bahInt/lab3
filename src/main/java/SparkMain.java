import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;


public class SparkMain {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> airportsFile = sc.textFile("L_AIRPORT_ID.csv");
        JavaRDD<String> flightsFile = sc.textFile("664600583_T_ONTIME_sample.csv");

        JavaPairRDD<Integer, String> airportsPair = makeAirportsPair(airportsFile);
        JavaPairRDD<Integer, Integer> flightsPair = makeFlightsPair(flightsFile);

    }

    private static JavaPairRDD<Integer, String> makeAirportsPair(JavaRDD<String> airportsFile) {
        return airportsFile.filter(line -> !line.contains("Code")).mapToPair( line -> {
            line = line.replace("\"", "");
            int commaIndex = line.indexOf(",");
            Integer airportID = Integer.valueOf(line.substring(commaIndex));
            String airportName = line.substring(commaIndex + 1);
            return new Tuple2<>(airportID, airportName);
                }
        );
    }
}

    private static JavaPairRDD<Integer, Integer> makeFlightsPair(JavaRDD<String> flightsFile) {
        return ;
    }