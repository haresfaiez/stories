package stories.event;

import org.junit.Test;
import stories.person.Person;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static stories.event.Event.withNoAttendees;
import static stories.event.EventSpecification.at;

public class EventAttendeeTest {

    @Test
    public void manyPersonsCanAttendAnEvent() {
        Person attendee      = new Person(  1L, "Bill");
        Person otherAttendee = new Person(122L, "Mike");
        Person notAttendee   = new Person(828L, "Bob");
        Event concert        = withNoAttendees(  8L, at(someTime(), "Concert title"));

        attendee     .attend(concert);
        otherAttendee.attend(concert);

        assertTrue (concert.attendees.contains(attendee));
        assertTrue (concert.attendees.contains(otherAttendee));
        assertFalse(concert.attendees.contains(notAttendee));
    }

    @Test
    public void aPersonCanAttendAnEvent() {
        Person bill    = new Person(1L, "Bill");
        Event concert  = withNoAttendees(1L, at(someTime(), "Concert title"));

        bill.attend(concert);

        assertTrue(concert.attendees.contains(bill));
    }

    private LocalDateTime someTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }
}
