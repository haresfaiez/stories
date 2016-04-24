package stories.event;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EventSpecificationTest {

    @Test
    public void withEqualStatementAndSameAttendees() {
        assertEquals(new EventSpecification(aStatement(), theAttendees()),
                     new EventSpecification(aStatement(), theAttendees()));
    }

    @Test
    public void towardNullAndObject() {
        assertNotEquals(new EventSpecification(aStatement(), theAttendees()), null);
        assertNotEquals(new EventSpecification(aStatement(), theAttendees()),
                        new Object());
    }

    private Attendees theAttendees() {
        return Attendees.none();
    }

    private EventStatement aStatement() {
        return new EventStatement(LocalDateTime.MAX, "Event title");
    }
}
