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
import org.junit.Test;
import stories.event.BuildEvent;
import stories.event.Event;

import java.util.UUID;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.javaFunctions;
import static org.junit.Assert.assertEquals;

public class CassandraInDockerTest {
    String cassandraHost = "172.17.0.1";
    String sparkMaster = "local";
    private SparkConf conf;
    private JavaSparkContext sc;
    private UUID expectedUUID = UUID.fromString("32b0a8e0-0a3d-11e6-8cf0-2d237e461979");

    @Before
    public void setUp() {
        conf = new SparkConf();
        conf.setAppName("Java API demo");
        conf.setMaster(sparkMaster);
        conf.set("spark.cassandra.connection.host", cassandraHost);
        sc = new JavaSparkContext(conf);
    }

    @Test
    public void cassandraRowToEventMapper() {
        CassandraTableScanJavaRDD<CassandraRow> response = javaFunctions(sc).cassandraTable("stories", "event");
        assertEquals(1, response.count());
        JavaRDD<Event> events = response.map(eventFromCassandraRow());
        assertEquals(1, response.count());
        assertEquals(events.first(), BuildEvent.identifiedBy(expectedUUID).product());
    }

    protected static Function<CassandraRow, Event> eventFromCassandraRow() {
        return row -> BuildEvent.identifiedBy(row.getUUID("id")).product();
    }

    @Test
    public void mapCassandraRowToEvent() {
        CassandraTableScanJavaRDD<CassandraRow> rdd = javaFunctions(sc)
                .cassandraTable("stories", "event");
        assertEquals(rdd.count(), 1);
        CassandraRow first = rdd.first();
        assertEquals(first.getUUID("id"), expectedUUID);
    }

    @Test
    public void convertToJavaRDD() {
        CassandraConnector connector = CassandraConnector.apply(sc.getConf());
        Session session = connector.openSession();
        ResultSet result = session.execute("SELECT * FROM Stories.Event");
        Row first = result.iterator().next();
        assertEquals(first.getUUID("id"), expectedUUID);
    }

    @Test
    public void findEventById() {
        Cluster cluster = Cluster.builder().addContactPoint(cassandraHost).build();
        Session session = cluster.connect("Stories");
        ResultSet result = session.execute("SELECT * FROM Event");
        Row first = result.iterator().next();
        assertEquals(first.getUUID("id"), expectedUUID);
    }

    @After
    public void tearDown() {
        sc.stop();
    }

}
