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
    public void equalityWithSameEvent() {
        Event anEvent      = eventWithId(1L);
        Event theSameEvent = eventWithId(1L);

        assertEquals(theSameEvent, anEvent);
    }

    @Test
    public void equalityWithDifferentEvent() {
        Event anEvent      = eventWithId(1L);
        Event anOtherEvent = eventWithId(219L);

        assertNotEquals(anOtherEvent, anEvent);
    }

    @Test
    public void equalityTowardNullAndObject() {
        Event anEvent = eventWithId(1L);

        assertNotEquals(anEvent, new Object());
    }

    @Test
    public void statementHasATitleAndATime() {
        EventStatement statement = at(someTime(), "Concert title");

        assertEquals("Concert title", statement.title);
        assertEquals(someTime(),      statement.time);
    }

    private Event eventWithId(Long id) {
        return withNoUpdates(id, withNoAttendees(someStatement()));
    }

    private EventStatement someStatement() {
        return at(someTime(), "Concert title");
    }

    private LocalDateTime someTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }
}
