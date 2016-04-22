package stories.event;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static stories.builder.PersonBuilder.bill;
import static stories.builder.UpdateBuilder.someEventUpdate;
import static stories.builder.UpdateBuilder.somePersonUpdate;

public class EventUpdateTest {

    @Test
    public void equality() {
        assertEquals   (new EventUpdate(bill(1L), somePersonUpdate()),
                        new EventUpdate(bill(1L), somePersonUpdate()));
        assertNotEquals(someEventUpdate(), null);
        assertNotEquals(somePersonUpdate(), new Object());
    }
}
