package stories.event;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AttendeeUpdateTest {

    @Test
    public void equality() {
        assertEquals   (AttendeeUpdate.at(someTime(), "Some message"),
                        AttendeeUpdate.at(someTime(), "Some message"));
        assertNotEquals(AttendeeUpdate.at(LocalDateTime.of(2015, Month.APRIL, 19, 20, 30), "Some message"), null);
        assertNotEquals(AttendeeUpdate.at(LocalDateTime.of(2015, Month.APRIL, 19, 20, 30), "Some message"), new Object());
    }

    private LocalDateTime someTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }
}
