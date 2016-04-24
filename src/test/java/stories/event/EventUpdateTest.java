package stories.event;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static stories.event.AttendeeUpdate.from;
import static stories.event.EventUpdateBuilder.*;

public class EventUpdateTest {

    @Test
    public void equalityWithSameIdentity() {
        assertEquals(aFrozenEventUpdate(),
                     aFrozenEventUpdate());
    }

    @Test
    public void equalityWithDifferentMessage() {
        assertEquals(eventUpdateWith(from("First message")),
                     eventUpdateWith(from("An other message")));
    }

    @Test
    public void equalityWithDifferentTime() {
        assertNotEquals(eventUpdateAt(someTime()),
                        eventUpdateAt(someTime().plusDays(1)));
    }

    @Test
    public void equalityWithDifferentEvent() {
        assertNotEquals(eventUpdateFor(eventWithId(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461970"))),
                        eventUpdateFor(eventWithId(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461911"))));
    }

    @Test
    public void equalityTowardNullAndObject() {
        assertNotEquals(someEventUpdate(), null);
        assertNotEquals(someEventUpdate(), new Object());
    }

}
