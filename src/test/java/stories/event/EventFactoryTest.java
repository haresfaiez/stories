package stories.event;

import org.joda.time.DateTime;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static stories.event.DateTimeFactory.aTime;
import static stories.event.EventFactory.from;

public class EventFactoryTest {

    @Test
    public void buildEvent() {
        DateTime time = aTime();

        EventBuilder builder = new EventBuilder();
        Event actual         = builder.at(time)
                                      .in("Tunis")
                                      .withUpdates(new ArrayList<>())
                                      .event();

        Event expected = from(time, "Tunis");

        assertEquals(expected, actual);
    }
}
