package stories.event;

import org.junit.Test;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AttendeesTest {

    @Test
    public void noOneAttendeeFactory() {
        assertEquals(new Attendees(emptyCollection()), Attendees.noOne());
    }

    @Test
    public void equality() {
        assertEquals(new Attendees(emptyCollection()), new Attendees(emptyCollection()));
        assertNotEquals(new Attendees(emptyCollection()), null);
        assertNotEquals(new Attendees(emptyCollection()), new Object());
    }

    private Collection emptyCollection() {
        return asList();
    }
}
