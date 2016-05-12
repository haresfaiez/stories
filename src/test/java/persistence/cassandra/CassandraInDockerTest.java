package persistence.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.spark.connector.cql.CassandraConnector;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import stories.event.BuildEvent;
import stories.event.Event;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class CassandraInDockerTest {
    final String cassandraHost = "172.17.0.3";
    final String sparkMaster   = "local";

    UUID expectedUUID   = UUID.fromString("32b0a8e0-0a3d-11e6-8cf0-2d237e461979");
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
    @Ignore
    public void findAllEvents() {
        final String keyspace = "stories";
        final String table    = "event";
        CassandraEventRepository repository = CassandraEventRepository.from(spark, keyspace, table);

        Set<Event> actual = repository.allEvents();

        assertEquals(expectedAllEvents(), actual);
    }

    @Test
    public void useRepositoryFilter() {
        final String keyspace = "stories";
        final String table    = "event";
        CassandraEventRepository repository = CassandraEventRepository.from(spark, keyspace, table);

        Event actual = repository.eventWithId(expectedUUID);

        assertEquals(expectedEvent, actual);
        assertEquals(expectedEvent.specification.attendees, actual.specification.attendees);
    }

    @Test
    @Ignore
    public void useCQLToFindAnEventById() {
        CassandraConnector connector = CassandraConnector.apply(spark.getConf());
        Session session = connector.openSession();
        String query = String.format("SELECT * FROM Stories.Event WHERE id=%s", expectedUUID);
        ResultSet result = session.execute(query);
        Row first = result.iterator().next();
        assertEquals(expectedUUID, first.getUUID("id"));
    }

    @Test
    @Ignore
    public void useCQLToRetrieveAllEvents() {
        Cluster  cluster = Cluster.builder().addContactPoint(cassandraHost).build();
        Session  session = cluster.connect("Stories");
        ResultSet result = session.execute("SELECT * FROM Event");
        assertEquals(5, result.all().size());
    }

    @After
    public void tearDown() {
        spark.stop();
    }

    private Set<Event> expectedAllEvents() {
        return new HashSet<>(Arrays.asList(
                BuildEvent.identified(UUID.fromString("32b0a8e0-0a3d-11e6-8cf0-2d237e461976")).product(),
                BuildEvent.identified(UUID.fromString("32b0a8e0-0a3d-11e6-8cf0-2d237e461975")).product(),
                BuildEvent.identified(UUID.fromString("32b0a8e0-0a3d-11e6-8cf0-2d237e461978")).product(),
                BuildEvent.identified(UUID.fromString("32b0a8e0-0a3d-11e6-8cf0-2d237e461977")).product(),
                BuildEvent.identified(UUID.fromString("32b0a8e0-0a3d-11e6-8cf0-2d237e461979")).product()));
    }
}
