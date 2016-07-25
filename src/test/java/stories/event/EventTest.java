package stories.event;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static stories.event.DateTimeFactory.aTime;

public class EventTest {

    EventBuilder builder = new EventBuilder();

    @Test
    public void equality() {
        String tunis     = "Tunis";
        DateTime theTime = aTime();

        Event expected = builder.at(theTime).in(tunis).event();
        Event actual   = builder.at(theTime).in(tunis).event();

        assertEquals(expected, actual);
    }
}
