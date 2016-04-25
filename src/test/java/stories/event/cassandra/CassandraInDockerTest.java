package stories.event.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class CassandraInDockerTest {

    @Test
    public void findEventById() {
        String containerIp = "172.17.0.1";
        Cluster cluster = Cluster.builder().addContactPoint(containerIp).build();
        Session session = cluster.connect("Stories");
        ResultSet result = session.execute("SELECT * FROM Event");
        Row first = result.iterator().next();
        assertEquals(first.getUUID("id"), UUID.fromString("32b0a8e0-0a3d-11e6-8cf0-2d237e461979"));
    }

}
