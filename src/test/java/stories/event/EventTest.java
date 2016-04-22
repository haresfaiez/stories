package stories.event;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static stories.event.Event.withNoAttendees;
import static stories.event.EventSpecification.at;

public class EventTest {

    @Test
    public void equality() {
        Event concert      = withNoAttendees(1L, someSpecification());
        Event sameConcert  = withNoAttendees(1L, someSpecification());
        Event otherConcert = withNoAttendees(2L, someSpecification());

        assertEquals   (sameConcert,  concert);
        assertNotEquals(otherConcert, concert);
        assertNotEquals(concert,      null);
        assertNotEquals(concert,      new Object());
    }

    @Test
    public void hasATitleAndATime() {
        Event concert = withNoAttendees(1L, at(someTime(), "Concert title"));

        assertEquals("Concert title", concert.specification.title);
        assertEquals(someTime(),      concert.specification.time);
    }

    private EventSpecification someSpecification() {
        return at(someTime(), "Concert title");
    }

    private LocalDateTime someTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }
}
