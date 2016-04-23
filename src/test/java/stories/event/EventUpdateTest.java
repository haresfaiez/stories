package stories.event;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static stories.builder.AttendeeBuilder.billAttendee;
import static stories.builder.UpdateBuilder.someEventUpdate;
import static stories.builder.UpdateBuilder.someAttendeeUpdate;
import static stories.event.EventUpdate.by;

public class EventUpdateTest {

    @Test
    public void equality() {
        assertEquals   (by(billAttendee(1L), someAttendeeUpdate()),
                        by(billAttendee(1L), someAttendeeUpdate()));
        assertNotEquals(someEventUpdate(), null);
        assertNotEquals(someAttendeeUpdate(), new Object());
    }
}
