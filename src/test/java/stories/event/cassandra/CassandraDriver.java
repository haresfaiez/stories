package stories.event.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import org.cassandraunit.CQLDataLoader;
import org.cassandraunit.dataset.cql.ClassPathCQLDataSet;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import stories.event.Event;

import java.util.UUID;

public class CassandraDriver {
    Cluster cluster;
    Session session;

    private CassandraDriver(Cluster cluster) {
        this.cluster = cluster;
    }

    public void setUp() {
        session = cluster.connect();
    }

    public void install(String fixturePath) {
        CQLDataLoader dataLoader = new CQLDataLoader(session);
        dataLoader.load(new ClassPathCQLDataSet(fixturePath));
    }

    public Boolean hasInstalled(Event event) {
        ResultSet result = session.execute("select * from event");
        UUID id = result.iterator().next().getUUID("id");
        return id.equals(event.id);
    }

    public void tearDown() {
        session.execute(String.format("DROP TABLE %s", "Event"));
        session.close();
    }

    public static CassandraDriver embedded() throws Exception {
        EmbeddedCassandraServerHelper.startEmbeddedCassandra(20000L);
        Cluster cluster = new Cluster.Builder().addContactPoints("localhost")
                .withPort(9142).build();
        return new CassandraDriver(cluster);
    }
}
