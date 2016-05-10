package persistence;

import com.datastax.spark.connector.japi.CassandraRow;
import com.datastax.spark.connector.japi.SparkContextJavaFunctions;
import com.datastax.spark.connector.japi.rdd.CassandraTableScanJavaRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import stories.event.BuildEvent;
import stories.event.Event;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.javaFunctions;
import static java.time.Instant.ofEpochSecond;
import static java.time.LocalDateTime.ofInstant;
import static java.util.TimeZone.getDefault;

public class CassandraEventRepository {
    private CassandraTableScanJavaRDD<CassandraRow> table;

    private CassandraEventRepository(CassandraTableScanJavaRDD<CassandraRow> table) {
        this.table = table;
    }

    public Set<Event> allEvents() {
        return new HashSet<>(eventsRDD().collect());
    }

    public Event eventWithId(UUID target) {
        return eventFrom(eventsRDD(), target);
    }

    public Event eventFrom(JavaRDD<Event> input,
                           UUID           target) {
        Event targetEvent = BuildEvent.identified(target).product();
        return input.filter(targetEvent::equals).first();
    }

    private JavaRDD<Event> eventsRDD() {
        return table.map(row -> BuildEvent
                         .identified(row.getUUID("id"))
                         .entitled(row.getString("title"))
                         .at(ofInstant(ofEpochSecond(row.getDate("time").getTime()),
                                       getDefault().toZoneId()))
                         .product());
    }

    public static CassandraEventRepository from(JavaSparkContext spark,
                                                String           keyspace,
                                                String           table) {
        SparkContextJavaFunctions util = javaFunctions(spark);
        return new CassandraEventRepository(util.cassandraTable(keyspace,  table));
    }
}
