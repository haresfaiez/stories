package stories.event;

import org.joda.time.DateTime;

public class EventRepository {

    public Event event(DateTime targetTime
                     , String   targetLocation) {
        return Event.entitled("",targetTime, targetLocation);
    }
}
