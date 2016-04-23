package stories.event;

import org.junit.Test;
import stories.person.Attendee;
import stories.person.Person;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static stories.event.Event.withNoUpdates;
import static stories.event.EventStatement.at;

public class EventAttendeeTest {

    @Test
    public void manyPersonsCanAttendAnEvent() {
        Attendee attendee      = new Attendee(new Person(1L, "Bill"));
        Attendee otherAttendee = new Attendee(new Person(122L, "Mike"));
        Attendee notAttendee   = new Attendee(new Person(828L, "Bob"));
        Event concert        = withNoUpdates(8L, EventSpecification.withNoAttendees(at(LocalDateTime.of(2015, Month.APRIL, 19, 20, 30), "Concert title")));

        attendee     .attend(concert);
        otherAttendee.attend(concert);

        assertTrue (concert.specification.attendees.contains(attendee));
        assertTrue (concert.specification.attendees.contains(otherAttendee));
        assertFalse(concert.specification.attendees.contains(notAttendee));
    }

    @Test
    public void aPersonCanAttendAnEvent() {
        Attendee bill    = new Attendee(new Person(1L, "Bill"));
        Event concert  = withNoUpdates(1L, EventSpecification.withNoAttendees(at(LocalDateTime.of(2015, Month.APRIL, 19, 20, 30), "Concert title")));

        bill.attend(concert);

        assertTrue(concert.specification.attendees.contains(bill));
    }

}
