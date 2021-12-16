import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;


public class SparkMain {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);

        if(args.length != 3){
            System.err.println("Usage: App <input path> <output path>");
            System.exit(-1);
        }
        JavaRDD<String> airportsFile = sc.textFile("L_AIRPORT_ID.csv");
        JavaRDD<String> flightsFile = sc.textFile("664600583_T_ONTIME_sample.csv");

        JavaPairRDD<Integer, String> airportsPair = makeAirportsPair(airportsFile);

    }
}
