package stories.event;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UpdateTest {

    @Test
    public void participantUpdate() {
        Event bigParty     = anEvent();
        Participant emma   = of(bigParty);
        String niceEvening = aMessage();

        bigParty.update(emma, niceEvening);

        assertTrue(bigParty.stream().contains(Update.from(emma, niceEvening)));
    }

    @Test
    public void equality() {
        Participant emma  = aParticipant();
        String herMessage = aMessage();
        Update expected   = Update.from(emma, herMessage);
        Update actual     = Update.from(emma, herMessage);
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

    private String aMessage() {
        return "Some update";
    }
}
