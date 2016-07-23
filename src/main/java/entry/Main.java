package entry;

import cli.Service;
import cli.Request;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import persistence.cassandra.CassandraEventRepository;
import persistence.neo4j.PersonNeo4jRepository;

import static persistence.cassandra.CassandraEventRepository.from;

public class Main {
    public static void main(String[] arguments) {
        final String cassandraHost = "172.17.0.3";
        final String keyspace      = "stories";
        final String table         = "event";

        SparkConf sparkConfiguration = new SparkConf();
        sparkConfiguration.setAppName("Stories");
        sparkConfiguration.setMaster("local");
        sparkConfiguration.set("spark.cassandra.connection.host", cassandraHost);
        sparkConfiguration.set("spark.driver.allowMultipleContexts", "true");

        JavaSparkContext         context      = new JavaSparkContext(sparkConfiguration);
        CassandraEventRepository repository   = from(context, keyspace, table);
        Service service = new Service(repository, PersonNeo4jRepository.main());

        Request request = new Request(service, arguments);

        System.out.println(request.response());
        System.out.println("---------------------");
        System.out.println(repository.allEvents());
    }
}
