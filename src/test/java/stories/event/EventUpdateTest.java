package stories.event;

import org.junit.Test;
import stories.person.Attendee;
import stories.person.Person;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static stories.event.EventUpdate.by;

public class EventUpdateTest {

    @Test
    public void equality() {
        assertEquals   (by(new Attendee(new Person(1L, "Bill")), AttendeeUpdate.at(LocalDateTime.of(2015, Month.APRIL, 19, 20, 30), "Some message")),
                        by(new Attendee(new Person(1L, "Bill")), AttendeeUpdate.at(LocalDateTime.of(2015, Month.APRIL, 19, 20, 30), "Some message")));
        assertNotEquals(by(new Attendee(new Person(1L, "Bill")), AttendeeUpdate.at(LocalDateTime.of(2015, Month.APRIL, 19, 20, 30), "Some message")), null);
        assertNotEquals(AttendeeUpdate.at(LocalDateTime.of(2015, Month.APRIL, 19, 20, 30), "Some message"), new Object());
    }
}
