package stories.event;

import org.junit.Test;
import stories.person.Attendee;
import stories.person.Person;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertTrue;
import static stories.event.Event.withNoUpdates;
import static stories.event.EventStatement.at;

public class EventAttendeeTest {

    @Test
    public void manyPersonsCanAttendAnEvent() {
        Person attendee = new Person(1L, "Bill");
        Person otherAttendee = new Person(122L, "Mike");
        Event concert = someEvent();

        attendee.attend(concert);
        otherAttendee.attend(concert);

        assertTrue(concert.specification.attendees.contains(new Attendee(attendee)));
        assertTrue(concert.specification.attendees.contains(new Attendee(otherAttendee)));
    }

    @Test
    public void aPersonCanAttendAnEvent() {
        Person bill = new Person(1L, "Bill");
        Event concert = someEvent();

        bill.attend(concert);

        assertTrue(concert.specification.attendees.contains(new Attendee(bill)));
    }

    private Event someEvent() {
        return withNoUpdates(8L, EventSpecification.withNoAttendees(at(LocalDateTime.of(2015, Month.APRIL, 19, 20, 30), "Concert title")));
    }
}
