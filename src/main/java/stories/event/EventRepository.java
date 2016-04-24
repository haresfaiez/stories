package stories.event;

import org.apache.spark.api.java.JavaRDD;

public class EventRepository {
    private JavaRDD<Event> input;

    public EventRepository(JavaRDD<Event> input) {
        this.input = input;
    }

    public Event eventWithId(Long target) {
        return input.filter(each -> target.equals(each.id)).first();
    }
}
