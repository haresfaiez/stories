package stories.event.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.spark.connector.cql.CassandraConnector;
import com.datastax.spark.connector.japi.CassandraRow;
import com.datastax.spark.connector.japi.rdd.CassandraTableScanJavaRDD;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import persistence.CassandraEventRepository;
import stories.event.BuildEvent;
import stories.event.Event;

import java.util.UUID;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.javaFunctions;
import static org.junit.Assert.assertEquals;

public class CassandraInDockerTest {
    final String cassandraHost = "172.17.0.1";
    final String sparkMaster   = "local";

    UUID expectedUUID = UUID.fromString("32b0a8e0-0a3d-11e6-8cf0-2d237e461979");
    Event expectedEvent = BuildEvent.identified(expectedUUID).product();

    JavaSparkContext spark;

    @Before
    public void setUp() {
        SparkConf configuration = new SparkConf();
        configuration.setAppName("Cassandra in docker test");
        configuration.setMaster(sparkMaster);
        configuration.set("spark.cassandra.connection.host", cassandraHost);
        configuration.set("spark.driver.allowMultipleContexts", "true");
        spark = new JavaSparkContext(configuration);
    }

    @Test
    public void useRepositoryFilter() {
        final String keyspace = "stories";
        final String table    = "event";
        CassandraEventRepository repository =
                CassandraEventRepository.from(spark, keyspace, table);

        Event actual = repository.eventWithId(expectedUUID);

        assertEquals(expectedEvent, actual);
    }

    @Test
    @Ignore
    public void cassandraRowToEventMapper() {
        CassandraTableScanJavaRDD<CassandraRow> response =
                javaFunctions(spark).cassandraTable("stories", "event");
        assertEquals(1, response.count());
        JavaRDD<Event> events = response.map((Function<CassandraRow,Event>) row -> BuildEvent.identified(row.getUUID("id"))
                .entitled(row.getString("title"))
                .product());
        assertEquals(1, response.count());
        assertEquals(events.first(), expectedEvent);
    }

    @Test
    @Ignore
    public void mapCassandraRowToEvent() {
        CassandraTableScanJavaRDD<CassandraRow> rdd =
                javaFunctions(spark).cassandraTable("stories", "event");
        assertEquals(rdd.count(), 1);
        CassandraRow first = rdd.first();
        assertEquals(first.getUUID("id"), expectedUUID);
    }

    @Test
    @Ignore
    public void convertToJavaRDD() {
        CassandraConnector connector = CassandraConnector.apply(spark.getConf());
        Session session = connector.openSession();
        ResultSet result = session.execute("SELECT * FROM Stories.Event");
        Row first = result.iterator().next();
        assertEquals(first.getUUID("id"), expectedUUID);
    }

    @Test
    @Ignore
    public void findEventById() {
        Cluster cluster = Cluster.builder().addContactPoint(cassandraHost).build();
        Session session = cluster.connect("Stories");
        ResultSet result = session.execute("SELECT * FROM Event");
        Row first = result.iterator().next();
        assertEquals(first.getUUID("id"), expectedUUID);
    }

    @After
    public void tearDown() {
        spark.stop();
    }

}
