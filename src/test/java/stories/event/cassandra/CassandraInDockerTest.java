package stories.event.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.spark.connector.cql.CassandraConnector;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class CassandraInDockerTest {
    String cassandraHost = "172.17.0.1";
    private String sparkMaster = "local";

    @Test
    public void convertToJavaRDD() {
        SparkConf conf = new SparkConf();
        conf.setAppName("Java API demo");
        conf.setMaster(sparkMaster);
        conf.set("spark.cassandra.connection.host", cassandraHost);
        JavaSparkContext sc = new JavaSparkContext(conf);
        CassandraConnector connector = CassandraConnector.apply(sc.getConf());
        Session session = connector.openSession();
        ResultSet result = session.execute("SELECT * FROM Stories.Event");
        Row first = result.iterator().next();
        assertEquals(first.getUUID("id"), UUID.fromString("32b0a8e0-0a3d-11e6-8cf0-2d237e461979"));
        sc.stop();
    }

    @Test

    public void findEventById() {
        Cluster cluster = Cluster.builder().addContactPoint(cassandraHost).build();
        Session session = cluster.connect("Stories");
        ResultSet result = session.execute("SELECT * FROM Event");
        Row first = result.iterator().next();
        assertEquals(first.getUUID("id"), UUID.fromString("32b0a8e0-0a3d-11e6-8cf0-2d237e461979"));
    }

}
