package stories.event;

import org.junit.Test;

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
        assertNotEquals(eventUpdateFor(eventWithId(1L)),
                        eventUpdateFor(eventWithId(813L)));
    }

    @Test
    public void equalityTowardNullAndObject() {
        assertNotEquals(someEventUpdate(), null);
        assertNotEquals(someEventUpdate(), new Object());
    }

}
