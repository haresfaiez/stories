package stories.event;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class EventTest {

    @Test
    public void eventEquality() {
        Event concert      = Event.withNoAttendees(1L, someSpecification());
        Event sameConcert  = Event.withNoAttendees(1L, someSpecification());
        Event otherConcert = Event.withNoAttendees(2L, someSpecification());

        assertEquals   (sameConcert,  concert);
        assertNotEquals(otherConcert, concert);
        assertNotEquals(concert,      null);
        assertNotEquals(concert,      new Object());
    }

    @Test
    public void anEventHasATitleAndATime() {
        EventSpecification specification
                = new EventSpecification("Concert title", someTime());
        Event concert = Event.withNoAttendees(1L, specification);

        assertTrue(concert.specification.hasTitle("Concert title"));
        assertTrue(concert.specification.isAt    (someTime()));
    }

    private EventSpecification someSpecification() {
        return new EventSpecification("Concert title", someTime());
    }

    private LocalDateTime someTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }
}
