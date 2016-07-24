package stories.event;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static stories.event.DateTimeFactory.aTime;
import static stories.event.EventFactory.anEvent;
import static stories.event.PersonFactory.emma;

public class UpdateTest {

    @Test
    public void participantUpdate() {
        Event bigParty     = anEvent();
        Participant emma   = of(bigParty);
        String niceEvening = aMessage();
        DateTime now       = aTime();

        bigParty.update(emma, niceEvening, now);

        assertTrue(bigParty.stream().contains(from(emma, niceEvening, now)));
    }

    @Test
    public void equality() {
        Participant emma  = aParticipant();
        String herMessage = aMessage();
        DateTime at       = aTime();

        Update expected   = new Update(emma, herMessage, at);
        Update actual     = new Update(emma, herMessage, at);

        assertEquals(expected, actual);
    }

    private Update from(Participant author
                      , String      message
                      , DateTime    time) {
        return new Update(author, message, time);
    }

    private Participant of(Event destination) {
        return new Participant(emma(), destination);
    }

    private Participant aParticipant() {
        return of(anEvent());
    }

    private String aMessage() {
        return "Some update";
    }
}
