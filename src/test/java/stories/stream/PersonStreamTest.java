package stories.stream;

import org.junit.Test;

import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static stories.builder.AttendeeBuilder.billAttendee;

public class PersonStreamTest {
    Set empty = Collections.emptySet();

    @Test
    public void equality() {
        assertEquals(new PersonStream(billAttendee(1L), empty),
                     new PersonStream(billAttendee(1L), empty));
        assertNotEquals(new PersonStream(billAttendee(1L), empty), null);
        assertNotEquals(new PersonStream(billAttendee(1L), empty), new Object());
    }

    @Test
    public void emptyStreamHasNoUpdates() {
        PersonStream billStream = PersonStream.empty(billAttendee(1L));

        assertEquals(empty, billStream.updates);
    }
}