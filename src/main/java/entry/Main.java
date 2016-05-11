package entry;

import cli.EventDetails;
import cli.Request;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import persistence.cassandra.CassandraEventRepository;

import static persistence.cassandra.CassandraEventRepository.from;

public class Main {
    public static void main(String[] arguments) {
        final String cassandraHost = "172.17.0.1";
        final String keyspace      = "stories";
        final String table         = "event";

        SparkConf sparkConfiguration = new SparkConf();
        sparkConfiguration.setAppName("Stories");
        sparkConfiguration.setMaster("local");
        sparkConfiguration.set("spark.cassandra.connection.host", cassandraHost);
        sparkConfiguration.set("spark.driver.allowMultipleContexts", "true");

        JavaSparkContext         context      = new JavaSparkContext(sparkConfiguration);
        CassandraEventRepository repository   = from(context, keyspace, table);
        EventDetails             eventDetails = new EventDetails(repository);

        Request request = new Request(eventDetails, arguments);

        System.out.println(request.response());
        System.out.println("---------------------");
        System.out.println(repository.allEvents());
    }
}
