package stories.event;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UpdateTest {

    private Event theEvent             = new Event();
    private String theMessage          = "A message";
    private Participant theParticipant = new Participant();

    @Test
    @Ignore
    public void participantUpdate() {
        Event event      = Event.entitled("Some title");
        Participant emma = Participant.named("Emma");
        Update update    = Update.from(emma, "Some update", event);
        assertTrue(event.stream().contains(update));
    }

    @Test
    public void equality() {
        Update expected = Update.from(theParticipant, theMessage, theEvent);
        Update actual   = Update.from(theParticipant, theMessage, theEvent);
        assertEquals(expected, actual);
    }
}
