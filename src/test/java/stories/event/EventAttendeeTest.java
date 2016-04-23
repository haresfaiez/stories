package stories.event;

import org.junit.Test;
import stories.person.Attendee;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static stories.builder.ConcertBuilder.someConcert;
import static stories.builder.ConcertBuilder.someTime;
import static stories.builder.AttendeeBuilder.*;
import static stories.event.Event.withNoUpdates;
import static stories.event.EventStatement.at;

public class EventAttendeeTest {

    @Test
    public void manyPersonsCanAttendAnEvent() {
        Attendee attendee      = bill(1L);
        Attendee otherAttendee = mike(122L);
        Attendee notAttendee   = someAttendee(828L);
        Event concert        = someConcert();

        attendee     .attend(concert);
        otherAttendee.attend(concert);

        assertTrue (concert.specification.attendees.contains(attendee));
        assertTrue (concert.specification.attendees.contains(otherAttendee));
        assertFalse(concert.specification.attendees.contains(notAttendee));
    }

    @Test
    public void aPersonCanAttendAnEvent() {
        Attendee bill    = bill(1L);
        Event concert  = withNoUpdates(1L, EventSpecification.withNoAttendees(at(someTime(), "Concert title")));

        bill.attend(concert);

        assertTrue(concert.specification.attendees.contains(bill));
    }

}
