package stories.event;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AttendeeUpdateTest {

    @Test
    public void equalityWithSameMessage() {
        assertEquals(AttendeeUpdate.from("Some message"),
                     AttendeeUpdate.from("Some message"));
    }

    @Test
    public void equalityWithDifferentMessage() {
        assertNotEquals(AttendeeUpdate.from("Some message"),
                        AttendeeUpdate.from("An other message"));
    }

    @Test
    public void equality() {
        assertNotEquals(AttendeeUpdate.from("Some message"), null);
        assertNotEquals(AttendeeUpdate.from("Some message"), new Object());
    }
}
