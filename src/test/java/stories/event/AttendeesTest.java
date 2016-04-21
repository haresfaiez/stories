package stories.event;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AttendeesTest {
    @Test
    public void equality() {
        assertEquals   (new Attendees(asList()), new Attendees(asList()));
        assertNotEquals(new Attendees(asList()), null);
        assertNotEquals(new Attendees(asList()), new Object());
    }
}
