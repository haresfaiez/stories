package stories.event;

import org.joda.time.DateTime;

class EventRepository {

    public Event event(DateTime targetTime
                     , String   targetLocation) {
        return Event.entitled("",targetTime, targetLocation);
    }
}
