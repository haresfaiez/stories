package stories.event;

import org.joda.time.DateTime;

public class EventFactory {

    private static EventBuilder builder = new EventBuilder();

    protected static Event from(DateTime time
                              , String   location) {
        return builder.at(time).in(location).event();
    }

    protected static Event anEvent() {
        return builder.event();
    }
}
