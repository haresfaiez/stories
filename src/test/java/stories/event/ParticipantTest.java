package stories.event;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static stories.event.EventFactory.anEvent;
import static stories.event.PersonFactory.emma;

public class ParticipantTest {

    @Test
    public void equality() {
        String emma = emma();
        Event event = anEvent();

        Participant actual = new Participant(emma, event);
        Participant expected = new Participant(emma, event);

        assertEquals(expected, actual);
    }
}
