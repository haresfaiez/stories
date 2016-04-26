package stories.event;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static stories.event.Event.identified;
import static stories.event.EventSpecification.statedBy;
import static stories.event.EventStatement.at;

public class EventTest {

    @Test
    public void equalityWithSameEvent() {
        Event anEvent      = eventWithId(anEventId());
        Event theSameEvent = eventWithId(anEventId());

        assertEquals(theSameEvent, anEvent);
    }

    @Test
    public void equalityWithDifferentEvent() {
        Event anEvent      = eventWithId(anEventId());
        Event anOtherEvent = eventWithId(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461970"));

        assertNotEquals(anOtherEvent, anEvent);
    }

    @Test
    public void equalityTowardNullAndObject() {
        Event anEvent = eventWithId(anEventId());

        assertNotEquals(anEvent, new Object());
    }

    @Test
    public void statementHasATitleAndATime() {
        EventStatement statement = at(someTime(), "Concert title");

        assertEquals("Concert title", statement.title);
        assertEquals(someTime(),      statement.time);
    }

    private UUID anEventId() {
        return UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461979");
    }

    private Event eventWithId(UUID id) {
        return identified(id, statedBy(someStatement()));
    }

    private EventStatement someStatement() {
        return at(someTime(), "Concert title");
    }

    private LocalDateTime someTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }
}
