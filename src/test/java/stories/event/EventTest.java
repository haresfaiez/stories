package stories.event;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static stories.event.Event.withNoAttendees;
import static stories.event.EventSpecification.with;

public class EventTest {

    @Test
    public void eventEquality() {
        Event concert      = withNoAttendees(1L, someSpecification());
        Event sameConcert  = withNoAttendees(1L, someSpecification());
        Event otherConcert = withNoAttendees(2L, someSpecification());

        assertEquals   (sameConcert,  concert);
        assertNotEquals(otherConcert, concert);
        assertNotEquals(concert,      null);
        assertNotEquals(concert,      new Object());
    }

    @Test
    public void anEventHasATitleAndATime() {
        Event concert = withNoAttendees(1L, with("Concert title", someTime()));

        assertTrue(concert.specification.hasTitle("Concert title"));
        assertTrue(concert.specification.isAt    (someTime()));
    }

    private EventSpecification someSpecification() {
        return with("Concert title", someTime());
    }

    private LocalDateTime someTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }
}
