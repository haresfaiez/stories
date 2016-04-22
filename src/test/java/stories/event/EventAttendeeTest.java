package stories.event;

import org.junit.Test;
import stories.person.Person;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EventAttendeeTest {

    @Test
    public void manyPersonsCanAttendAnEvent() {
        Person attendee      = new Person(  1L, "Bill");
        Person otherAttendee = new Person(122L, "Mike");
        Person notAttendee   = new Person(828L, "Bob");
        Event concert        = Event.withNoAttendees(  8L, new EventSpecification("Concert title", someTime()));

        attendee     .attend(concert);
        otherAttendee.attend(concert);

        assertTrue (concert.attendees.contains(attendee));
        assertTrue (concert.attendees.contains(otherAttendee));
        assertFalse(concert.attendees.contains(notAttendee));
    }

    @Test
    public void aPersonCanAttendAEvent() {
        Person bill    = new Person(1L, "Bill");
        Event concert  = Event.withNoAttendees(1L, new EventSpecification("Concert title", someTime()));

        bill.attend(concert);

        assertTrue(concert.attendees.contains(bill));
    }

    private LocalDateTime someTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }
}
