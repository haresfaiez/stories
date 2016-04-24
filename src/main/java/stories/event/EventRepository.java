package stories.event;

import org.apache.spark.api.java.JavaRDD;

import java.util.UUID;

public class EventRepository {
    private JavaRDD<Event> input;

    public EventRepository(JavaRDD<Event> input) {
        this.input = input;
    }

    public Event eventWithId(UUID target) {
        return input.filter(each -> target.equals(each.id)).first();
    }
}
