package stories.event;

import org.junit.Test;

import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AttendeesTest {

    @Test
    public void noOneAttendeeFactory() {
        assertEquals(new Attendees(empty()), Attendees.noOne());
    }

    @Test
    public void equality() {
        assertEquals   (new Attendees(empty()), new Attendees(empty()));
        assertNotEquals(new Attendees(empty()), null);
        assertNotEquals(new Attendees(empty()), new Object());
    }

    private Collection empty() {
        return Collections.EMPTY_LIST;
    }
}
