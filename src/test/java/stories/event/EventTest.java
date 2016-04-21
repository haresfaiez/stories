package stories.event;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EventTest {

    @Test
    public void eventEquality() {
        Event concert      = new Event(1L, "Concert title", someTime());
        Event sameConcert  = new Event(1L, "Concert title", someTime());
        Event otherConcert = new Event(2L, "Concert title", someTime());

        assertEquals   (sameConcert,  concert);
        assertNotEquals(otherConcert, concert);
        assertNotEquals(concert,      null);
        assertNotEquals(concert,      new Object());
    }

    @Test
    public void anEventHasATitleAndATime() {
        Event concert = new Event(1L, "Concert title", someTime());

        assertEquals("Concert title", concert.title());
        assertEquals(someTime(),      concert.time());
    }

    private LocalDateTime someTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }
}
