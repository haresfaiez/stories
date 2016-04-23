package stories.event;

import org.junit.Test;
import stories.person.Attendee;
import stories.person.Person;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static stories.event.AttendeeUpdate.from;
import static stories.event.EventSpecification.withNoAttendees;

public class EventUpdateTest {

    @Test
    public void equality() {
        assertEquals   (new EventUpdate(bill(), from("Some message"), someTime(), concert()),
                new EventUpdate(bill(), from("Some message"), someTime(), concert()));

        assertEquals   (new EventUpdate(bill(), from("Some message"), someTime(), concert()),
                new EventUpdate(bill(), from("Oth message"), someTime(), concert()));

        assertNotEquals(new EventUpdate(bill(), from("Some message"), someTime().plusDays(1), concert()),
                new EventUpdate(bill(), from("Some message"), someTime(), concert()));

        assertNotEquals(new EventUpdate(bill(), from("Some message"), someTime(), concert()),
                new EventUpdate(bill(), from("Some message"), someTime(), otherConcert()));

        assertNotEquals(new EventUpdate(bill(), from("Some message"), someTime(), concert()), null);
        assertNotEquals(from("Some message"), new Object());
    }

    private Event otherConcert() {
        return Event.withNoUpdates(3L, someSpecification());
    }

    private Event concert() {
        return Event.withNoUpdates(1L, someSpecification());
    }

    private EventSpecification someSpecification() {
        return withNoAttendees(new EventStatement(someTime(), "Some Concert"));
    }

    private LocalDateTime someTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }

    private Attendee bill() {
        return new Attendee(new Person(1L, "Bill"));
    }
}
