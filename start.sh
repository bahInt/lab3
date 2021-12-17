mvn package
hadoop fs -rm -r outputLab3
hadoop fs -put ~/IdeaProjects/lab3/L_AIRPORT_ID.csv
hadoop fs -put ~/IdeaProjects/lab3/664600583_T_ONTIME_sample.csv
export HADOOP_CLASSPATH=target/hadoop-examples-1.0-SNAPSHOT.jar
spark-submit --class lab3.SparkMain --master yarn-client --num-executors 3 target/spark-examples-1.0-SNAPSHOT.jar
hadoop fs -copyToLocal outputLab3
exit 0
