package stories.event;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EventStatementEquality {

    @Test
    public void withEqualTimeAndTitle() {
        assertEquals(new EventStatement(aTime(), aTitle()),
                     new EventStatement(aTime(), aTitle()));
    }

    @Test
    public void towardNullAndObject() {
        assertNotEquals(new EventStatement(aTime(), aTitle()), null);
        assertNotEquals(new EventStatement(aTime(), aTitle()), new Object());
    }

    private String aTitle() {
        return "Event title";
    }

    private LocalDateTime aTime() {
        return LocalDateTime.MAX;
    }
}
