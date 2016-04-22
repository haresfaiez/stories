package stories.event;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static stories.event.Event.withNoAttendees;
import static stories.event.EventStatement.at;

public class EventTest {

    @Test
    public void equality() {
        Event concert      = withNoAttendees(1L, someStatement());
        Event sameConcert  = withNoAttendees(1L, someStatement());
        Event otherConcert = withNoAttendees(2L, someStatement());

        assertEquals   (sameConcert,  concert);
        assertNotEquals(otherConcert, concert);
        assertNotEquals(concert,      null);
        assertNotEquals(concert,      new Object());
    }

    @Test
    public void hasATitleAndATime() {
        Event concert = withNoAttendees(1L, at(someTime(), "Concert title"));

        assertEquals("Concert title", concert.statement.title);
        assertEquals(someTime(),      concert.statement.time);
    }

    private EventStatement someStatement() {
        return at(someTime(), "Concert title");
    }

    private LocalDateTime someTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }
}
