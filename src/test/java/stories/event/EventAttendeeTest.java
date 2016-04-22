package stories.event;

import org.junit.Test;
import stories.person.Person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static stories.builder.ConcertBuilder.someConcert;
import static stories.builder.ConcertBuilder.someTime;
import static stories.builder.PersonBuilder.*;
import static stories.event.Event.withNoAttendees;
import static stories.event.EventSpecification.at;

public class EventAttendeeTest {

    @Test
    public void manyPersonsCanAttendAnEvent() {
        Person attendee      = bill(1L);
        Person otherAttendee = mike(122L);
        Person notAttendee   = somePerson(828L);
        Event concert        = someConcert();

        attendee     .attend(concert);
        otherAttendee.attend(concert);

        assertTrue (concert.attendees.contains(attendee));
        assertTrue (concert.attendees.contains(otherAttendee));
        assertFalse(concert.attendees.contains(notAttendee));
    }

    @Test
    public void aPersonCanAttendAnEvent() {
        Person bill    = bill(1L);
        Event concert  = withNoAttendees(1L, at(someTime(), "Concert title"));

        bill.attend(concert);

        assertTrue(concert.attendees.contains(bill));
    }

}
