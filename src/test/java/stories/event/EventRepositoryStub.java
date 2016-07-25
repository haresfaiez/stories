package stories.event;

import org.joda.time.DateTime;

public class EventRepositoryStub implements EventRepository {
    private EventBuilder builder;

    public EventRepositoryStub(EventBuilder builder) {
        this.builder = builder;
    }

    @Override
    public Event event(DateTime targetTime
            , String targetLocation) {
        return builder.at(targetTime).in(targetLocation).event();
    }
}
