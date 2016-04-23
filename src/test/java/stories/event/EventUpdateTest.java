package stories.event;

import org.junit.Test;
import stories.person.Attendee;
import stories.person.Person;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static stories.event.EventSpecification.withNoAttendees;
import static stories.event.EventUpdate.by;

public class EventUpdateTest {

    @Test
    public void equality() {
        assertEquals   (by(bill(), concert(), someTime(), billUpdate()),
                        by(bill(), concert(), someTime(), billUpdate()));

        assertNotEquals(by(bill(), concert(), someTime().plusDays(1), billUpdate()),
                        by(bill(), concert(), someTime(), billUpdate()));

        assertNotEquals(by(bill(), concert(), someTime(), billUpdate()), null);
        assertNotEquals(billUpdate(), new Object());
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

    private AttendeeUpdate billUpdate() {
        return AttendeeUpdate.from("Some message");
    }

    private Attendee bill() {
        return new Attendee(new Person(1L, "Bill"));
    }
}
