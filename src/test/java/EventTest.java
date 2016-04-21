import org.junit.Test;
import stories.event.Event;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;

public class EventTest {
    @Test
    public void construction() {
        Event concert = new Event(1L, "Concert title", someTime());
        assertEquals(1L,              concert.id());
        assertEquals("Concert title", concert.title());
        assertEquals(someTime(),      concert.time());
    }

    private LocalDateTime someTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }
}
