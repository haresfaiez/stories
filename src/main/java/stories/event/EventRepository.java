package stories.event;

import org.joda.time.DateTime;

public class EventRepository {
    private EventBuilder builder;

    public EventRepository(EventBuilder builder) {
        this.builder = builder;
    }

    public Event event(DateTime targetTime
                     , String   targetLocation) {
        return builder.at(targetTime).in(targetLocation).event();
    }
}
