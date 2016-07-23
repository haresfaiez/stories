package stories.event;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UpdateTest {

    @Test
    public void participantUpdate() {
        Event event      = Event.entitled("Some title");
        Participant emma = of(event);
        String message   = "Some update";

        Update expected  = Update.from(emma, message);

        event.update(emma, message);

        assertTrue(event.stream().contains(expected));
    }

    @Test
    public void equality() {
        Participant author = aParticipant();
        Update expected    = Update.from(author, "A message");
        Update actual      = Update.from(author, "A message");
        assertEquals(expected, actual);
    }

    private Participant of(Event event) {
        return Participant.named("Emma", event);
    }

    private Participant aParticipant() {
        return of(Event.entitled("An event"));
    }
}
