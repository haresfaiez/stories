package stories.event;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UpdateTest {

    @Test
    public void participantUpdate() {
        Event bigParty     = anEvent();
        Participant emma   = of(bigParty);
        String niceEvening = aMessage();
        DateTime now       = aTime();

        bigParty.update(emma, niceEvening, now);

        assertTrue(bigParty.stream().contains(Update.from(emma, niceEvening, aTime())));
    }

    private DateTime aTime() {
        return new DateTime(2016, 7, 1, 21, 0);
    }

    @Test
    public void equality() {
        Participant emma  = aParticipant();
        String herMessage = aMessage();
        DateTime time     = aTime();
        Update expected   = Update.from(emma, herMessage, aTime());
        Update actual     = Update.from(emma, herMessage, aTime());
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
