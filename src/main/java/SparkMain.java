public class SparkMain {
    public static void main() {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> airportsFile = sc.textFile();
        JavaRDD<String> flightsFile = sc.textFile();
    }
}
