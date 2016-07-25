package stories.event;

import org.joda.time.DateTime;

public interface EventRepository {
    Event event(DateTime targetTime
              , String   targetLocation);
}
