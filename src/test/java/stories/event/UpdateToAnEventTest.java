package stories.event;

import org.junit.Test;
import stories.person.Attendee;

import static org.junit.Assert.assertTrue;
import static stories.builder.ConcertBuilder.someConcert;
import static stories.builder.AttendeeBuilder.bill;
import static stories.builder.UpdateBuilder.someAttendeeUpdate;

public class UpdateToAnEventTest {

    @Test
    public void personCanUpdateToAnEvent() {
        Event concert = someConcert();
        Attendee bill = bill(1L);
        AttendeeUpdate billUpdate = someAttendeeUpdate();

        bill.attend(concert);
        bill.update(concert, billUpdate);

        EventUpdate billEventUpdate = EventUpdate.by(bill, billUpdate);
        assertTrue(concert.updates.contains(billEventUpdate));
    }

}
