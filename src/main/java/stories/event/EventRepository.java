package stories.event;

import com.datastax.spark.connector.japi.CassandraRow;
import com.datastax.spark.connector.japi.SparkContextJavaFunctions;
import com.datastax.spark.connector.japi.rdd.CassandraTableScanJavaRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import java.util.UUID;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.javaFunctions;

public class EventRepository {
    public static final String KEYSPACE = "stories";
    public static final String TABLE = "event";

    private JavaSparkContext spark;

    public EventRepository(JavaSparkContext spark) {
        this.spark = spark;
    }

    public Event eventWithId(UUID target) {
        SparkContextJavaFunctions utils = javaFunctions(spark);
        CassandraTableScanJavaRDD<CassandraRow> eventRows = utils.cassandraTable(KEYSPACE, TABLE);
        JavaRDD<Event> events = eventRows.map(eventFromCassandraRow());
        return eventWithIdFrom(events, target);
    }

    protected Event eventWithIdFrom(JavaRDD<Event> input, UUID target) {
        return input.filter(each -> target.equals(each.id)).first();
    }

    public static Function<CassandraRow, Event> eventFromCassandraRow() {
        return row -> BuildEvent.identifiedBy(row.getUUID("id")).product();
    }
}
