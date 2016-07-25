package stories.event;

import org.joda.time.DateTime;

import static stories.event.DateTimeFactory.aTime;

public class EventFactory {

    protected static Event from(DateTime time
                              , String   location) {
        return Event.entitled("Some title", time, location);
    }

    protected static Event anEvent() {
        return Event.entitled("Some title", aTime(), "Some location");
    }
}
