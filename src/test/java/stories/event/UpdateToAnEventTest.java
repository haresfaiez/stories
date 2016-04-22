package stories.event;

import org.junit.Ignore;
import org.junit.Test;
import stories.person.Person;

import static stories.builder.ConcertBuilder.someConcert;
import static stories.builder.PersonBuilder.bill;
import static stories.builder.UpdateBuilder.somePersonUpdate;

public class UpdateToAnEventTest {

    @Test
    @Ignore
    public void personCanUpdateToAnEvent() {
        Event concert = someConcert();
        Person bill = bill(1L);
        PersonUpdate billUpdate = somePersonUpdate();

        bill.attend(concert);
        bill.update(concert, billUpdate);

//        assertTrue(concert.updates.contains(new EventUpdate(bill, billUpdate)));

    }
}
