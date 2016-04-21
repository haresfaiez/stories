package stories.event;

import org.junit.Test;
import stories.person.Person;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class EventTest {

    @Test
    public void eventHasOneAttendee() {
        Person bill    = new Person(1L, "Bill");
        Event concert  = new Event (1L, "Concert title", someTime());

        concert.attendee(bill);

        assertTrue(bill.isAttending(concert));
    }

    @Test
    public void eventEquality() {
        Event concert = new Event(1L, "Concert title", someTime());
        Event sameConcert = new Event(1L, "Concert title", someTime());
        Event otherConcert = new Event(2L, "Concert title", someTime());

        assertEquals(sameConcert, concert);
        assertNotEquals(otherConcert, concert);
        assertNotEquals(concert, null);
        assertNotEquals(concert, new Object());
    }

    @Test
    public void anEventHasATitleAndATime() {
        Event concert = new Event(1L, "Concert title", someTime());

        assertEquals("Concert title", concert.title());
        assertEquals(someTime(), concert.time());
    }

    private LocalDateTime someTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }
}
