package stories.event;

import org.junit.Test;
import stories.person.Attendee;
import stories.person.Person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static stories.event.EventUpdate.by;

public class EventUpdateTest {

    @Test
    public void equality() {
        assertEquals   (by(new Attendee(new Person(1L, "Bill")), AttendeeUpdate.from("Some message")),
                        by(new Attendee(new Person(1L, "Bill")), AttendeeUpdate.from("Some message")));
        assertNotEquals(by(new Attendee(new Person(1L, "Bill")), AttendeeUpdate.from("Some message")), null);
        assertNotEquals(AttendeeUpdate.from("Some message"), new Object());
    }
}
