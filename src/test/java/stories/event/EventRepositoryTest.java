package stories.event;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static stories.event.DateTimeFactory.aTime;
import static stories.event.EventFactory.from;

public class EventRepositoryTest {

    @Test
    public void loadEvent() {
        DateTime time = aTime();
        EventRepository repository = new EventRepository(builder());

        Event actual   = repository.event(time, "BigParty");
        Event expected = from(time, "BigParty");

        assertEquals(expected, actual);
    }

    private EventBuilder builder() {
        return new EventBuilder();
    }
}
