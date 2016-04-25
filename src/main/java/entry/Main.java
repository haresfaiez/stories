package entry;

import cli.EventDetails;
import cli.Request;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import stories.event.EventRepository;

public class Main {
    public static void main(String[] arguments) {
        String cassandraHost = "172.17.0.1";

        SparkConf sparkConfiguration = new SparkConf();
        sparkConfiguration.setAppName("Stories");
        sparkConfiguration.setMaster("local");
        sparkConfiguration.set("spark.cassandra.connection.host", cassandraHost);
        sparkConfiguration.set("spark.driver.allowMultipleContexts", "true");

        JavaSparkContext spark = new JavaSparkContext(sparkConfiguration);
        EventRepository repository = new EventRepository(spark);
        EventDetails eventDetails = new EventDetails(repository);
        Request request = new Request(eventDetails, arguments);
        String result = request.response();
        System.out.println(result);
    }
}
