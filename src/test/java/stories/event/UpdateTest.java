package stories.event;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UpdateTest {

    @Test
    public void participantUpdate() {
        Event event      = anEvent();
        Participant emma = of(event);
        String message   = "Some update";

        event.update(emma, message);

        assertTrue(event.stream().contains(Update.from(emma, message)));
    }

    @Test
    public void equality() {
        Participant author = aParticipant();
        Update expected    = Update.from(author, "A message");
        Update actual      = Update.from(author, "A message");
        assertEquals(expected, actual);
    }

    private Participant of(Event destination) {
        return Participant.named("Emma", destination);
    }

    private Participant aParticipant() {
        return of(anEvent());
    }

    private Event anEvent() {
        return Event.entitled("Some title");
    }
}
