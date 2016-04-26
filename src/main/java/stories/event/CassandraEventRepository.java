package stories.event;

import com.datastax.spark.connector.japi.CassandraRow;
import com.datastax.spark.connector.japi.SparkContextJavaFunctions;
import com.datastax.spark.connector.japi.rdd.CassandraTableScanJavaRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import java.util.UUID;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.javaFunctions;

public class CassandraEventRepository {
    private CassandraTableScanJavaRDD<CassandraRow> table;

    private CassandraEventRepository(CassandraTableScanJavaRDD<CassandraRow> table) {
        this.table = table;
    }

    public Event eventWithId(UUID target) {
        return eventFrom(eventsRDD(), target);
    }

    protected Event eventFrom(JavaRDD<Event> input,
                              UUID           target) {
        return input.filter(each -> target.equals(each.id)).first();
    }

    private JavaRDD<Event> eventsRDD() {
        return table.map(eventFromRow());
    }

    public static Function<CassandraRow, Event> eventFromRow() {
        return row -> BuildEvent.identified(row.getUUID("id")).product();
    }

    public static CassandraEventRepository in(JavaSparkContext spark,
                                              String           keyspace,
                                              String           table) {
        SparkContextJavaFunctions util = javaFunctions(spark);
        return new CassandraEventRepository(util.cassandraTable(keyspace,  table));
    }
}
