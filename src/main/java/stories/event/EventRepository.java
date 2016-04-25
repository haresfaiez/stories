package stories.event;

import com.datastax.spark.connector.japi.CassandraRow;
import com.datastax.spark.connector.japi.rdd.CassandraTableScanJavaRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import java.util.UUID;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.javaFunctions;

public class EventRepository {
    private JavaSparkContext context;

    public EventRepository(JavaSparkContext context) {
        this.context = context;
    }

    public Event eventWithId(UUID target) {
        CassandraTableScanJavaRDD<CassandraRow> response = javaFunctions(context).cassandraTable("stories", "event");
        JavaRDD<Event> events = response.map(eventFromCassandraRow());
        return eventWithIdFrom(events, target);
    }

    protected Event eventWithIdFrom(JavaRDD<Event> input, UUID target) {
        return input.filter(each -> target.equals(each.id)).first();
    }

    public static Function<CassandraRow, Event> eventFromCassandraRow() {
        return row -> BuildEvent.identifiedBy(row.getUUID("id")).product();
    }
}
