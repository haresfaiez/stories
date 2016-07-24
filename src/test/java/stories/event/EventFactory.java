package stories.event;

import static stories.event.DateTimeFactory.aTime;

public class EventFactory {

    protected static Event anEvent() {
        return Event.entitled("Some title", aTime(), "Some location");
    }
}
