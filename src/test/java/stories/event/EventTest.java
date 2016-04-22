package stories.event;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static stories.event.Event.withNoUpdates;
import static stories.event.EventSpecification.withNoAttendees;
import static stories.event.EventStatement.at;

public class EventTest {

    @Test
    public void equality() {
        Event concert      = withNoUpdates(1L, withNoAttendees(someStatement()));
        Event sameConcert  = withNoUpdates(1L, withNoAttendees(someStatement()));
        Event otherConcert = withNoUpdates(2L, withNoAttendees(someStatement()));

        assertEquals   (sameConcert,  concert);
        assertNotEquals(otherConcert, concert);
        assertNotEquals(concert,      null);
        assertNotEquals(concert,      new Object());
    }

    @Test
    public void hasATitleAndATime() {
        Event concert = withNoUpdates(1L, withNoAttendees(at(someTime(), "Concert title")));

        assertEquals("Concert title", concert.specification.statement.title);
        assertEquals(someTime(),      concert.specification.statement.time);
    }

    private EventStatement someStatement() {
        return at(someTime(), "Concert title");
    }

    private LocalDateTime someTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }
}
