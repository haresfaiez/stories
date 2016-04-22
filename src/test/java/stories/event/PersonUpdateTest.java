package stories.event;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static stories.builder.UpdateBuilder.somePersonUpdate;

public class PersonUpdateTest {

    @Test
    public void equality() {
        assertEquals   (PersonUpdate.at(someTime(), "Some message"),
                        PersonUpdate.at(someTime(), "Some message"));
        assertNotEquals(somePersonUpdate(), null);
        assertNotEquals(somePersonUpdate(), new Object());
    }

    private LocalDateTime someTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }
}
