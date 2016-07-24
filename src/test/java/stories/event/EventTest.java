package stories.event;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static stories.event.DateTimeFactory.aTime;

public class EventTest {

    @Test
    public void equality() {
        String tunis     = "Tunis";
        DateTime theTime = aTime();

        Event expected = Event.entitled("Big party",       theTime, tunis);
        Event actual   = Event.entitled("Other big party", theTime, tunis);

        assertEquals(expected, actual);
    }
}
