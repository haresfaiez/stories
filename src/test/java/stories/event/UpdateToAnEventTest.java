package stories.event;

import org.junit.Before;
import org.junit.Test;
import stories.person.Attendee;
import stories.person.Person;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertTrue;
import static stories.event.Event.withNoUpdates;
import static stories.event.EventStatement.at;

public class UpdateToAnEventTest {

    private Event concert;
    private Person bill;
    private Attendee billAttendee;

    @Before
    public void setUp() {
        concert = someConcert();
        bill = new Person(1L, "Bill");
        billAttendee = new Attendee(bill);
    }

    @Test
    public void personCanUpdateToAnEvent() {
        bill.attend(concert);
        billAttendee.update(concert, billUpdate());

        EventUpdate billEventUpdate = EventUpdate.by(billAttendee, billUpdate());
        assertTrue(concert.updates.contains(billEventUpdate));
    }

    private Event someConcert() {
        return withNoUpdates(8L, EventSpecification.withNoAttendees(at(LocalDateTime.of(2015, Month.APRIL, 19, 20, 30), "Concert title")));
    }

    private AttendeeUpdate billUpdate() {
        return AttendeeUpdate.from("Some message");
    }

}
