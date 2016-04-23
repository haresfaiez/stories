package stories.event;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static stories.builder.UpdateBuilder.someAttendeeUpdate;

public class AttendeeUpdateTest {

    @Test
    public void equality() {
        assertEquals   (AttendeeUpdate.at(someTime(), "Some message"),
                        AttendeeUpdate.at(someTime(), "Some message"));
        assertNotEquals(someAttendeeUpdate(), null);
        assertNotEquals(someAttendeeUpdate(), new Object());
    }

    private LocalDateTime someTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }
}
