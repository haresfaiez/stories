package stories.event;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;

public class PersonUpdateTest {

    @Test
    public void hasATimeAndAMessage() {
        PersonUpdate update = PersonUpdate.at(someTime(), "Some message");
        assertEquals(someTime(),     update.time);
        assertEquals("Some message", update.message);
    }

    private LocalDateTime someTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }
}
