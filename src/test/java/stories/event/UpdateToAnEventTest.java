package stories.event;

import org.junit.Test;
import stories.person.Attendee;
import stories.person.Person;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertTrue;
import static stories.event.Event.withNoUpdates;
import static stories.event.EventStatement.at;

public class UpdateToAnEventTest {

    @Test
    public void personCanUpdateToAnEvent() {
        Event concert = withNoUpdates(8L, EventSpecification.withNoAttendees(at(LocalDateTime.of(2015, Month.APRIL, 19, 20, 30), "Concert title")));
        Attendee bill = new Attendee(new Person(1L, "Bill"));
        AttendeeUpdate billUpdate = AttendeeUpdate.at(LocalDateTime.of(2015, Month.APRIL, 19, 20, 30), "Some message");

        bill.attend(concert);
        bill.update(concert, billUpdate);

        EventUpdate billEventUpdate = EventUpdate.by(bill, billUpdate);
        assertTrue(concert.updates.contains(billEventUpdate));
    }

}
