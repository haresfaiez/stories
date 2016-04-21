package stories.person;

import org.junit.Test;
import stories.event.Event;

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
        Event concert        = new Event (  8L, "Concert title", someTime());

        attendee     .attend(concert);
        otherAttendee.attend(concert);

        assertTrue (attendee     .isAttending(concert));
        assertTrue (otherAttendee.isAttending(concert));
        assertFalse(notAttendee  .isAttending(concert));
    }

    @Test
    public void aPersonCanAttendAConcert() {
        Person bill    = new Person(1L, "Bill");
        Event concert  = new Event (1L, "Concert title", someTime());

        bill.attend(concert);

        assertTrue(bill.isAttending(concert));
    }

    private LocalDateTime someTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }
}
