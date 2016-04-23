package stories.stream;

import org.junit.Test;
import stories.person.Attendee;
import stories.person.Person;

import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PersonStreamTest {
    Set empty = Collections.emptySet();

    @Test
    public void equality() {
        assertEquals   (new PersonStream(new Attendee(new Person(1L, "Bill")), empty),
                        new PersonStream(new Attendee(new Person(1L, "Bill")), empty));
        assertNotEquals(new PersonStream(new Attendee(new Person(1L, "Bill")), empty), null);
        assertNotEquals(new PersonStream(new Attendee(new Person(1L, "Bill")), empty), new Object());
    }

    @Test
    public void emptyStreamHasNoUpdates() {
        PersonStream billStream = PersonStream.empty(new Attendee(new Person(1L, "Bill")));

        assertEquals(empty, billStream.updates);
    }
}