package stories.event;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static stories.event.DateTimeFactory.aTime;

public class EventTest {

    @Test
    public void equality() {
        DateTime thisEvening = aTime();
        String tunis         = "Tunis";

        Event expected = Event.entitled("Big party",       thisEvening, tunis);
        Event actual   = Event.entitled("Other big party", thisEvening, tunis);

        assertEquals(expected, actual);
    }
}
