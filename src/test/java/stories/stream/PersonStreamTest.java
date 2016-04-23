package stories.stream;

import org.junit.Test;
import stories.person.Attendee;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static stories.builder.AttendeeBuilder.mike;
import static stories.builder.AttendeeBuilder.bill;
import static stories.builder.UpdateBuilder.someAttendeeUpdate;

public class PersonStreamTest {

    @Test
    public void hasAnAttendeeAndAnEventUpdates() {
        Set eventUpdates = new HashSet();
        eventUpdates.add(new StreamUpdate(mike(2L), someAttendeeUpdate()));

        Attendee bill = new Attendee(bill(1L));
        PersonStream billStream = new PersonStream(bill, eventUpdates);

        assertEquals(bill, billStream.director);
        assertEquals(eventUpdates, billStream.updates);
    }
}