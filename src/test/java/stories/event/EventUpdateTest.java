package stories.event;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static stories.builder.PersonBuilder.bill;
import static stories.builder.UpdateBuilder.someEventUpdate;
import static stories.builder.UpdateBuilder.somePersonUpdate;
import static stories.event.EventUpdate.by;

public class EventUpdateTest {

    @Test
    public void equality() {
        assertEquals   (by(bill(1L), somePersonUpdate()),
                        by(bill(1L), somePersonUpdate()));
        assertNotEquals(someEventUpdate(), null);
        assertNotEquals(somePersonUpdate(), new Object());
    }
}
