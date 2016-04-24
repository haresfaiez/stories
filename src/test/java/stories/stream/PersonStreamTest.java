package stories.stream;

import org.junit.Before;
import org.junit.Test;
import stories.event.Updates;
import stories.person.Person;

import static org.junit.Assert.*;

public class PersonStreamTest {
    Updates empty = Updates.noOne();
    Person aPerson;

    @Before
    public void setUp() throws Exception {
        aPerson = new Person(1L, "Bill");
    }

    @Test
    public void equalityWithSamePersonAndUpdates() {
        assertEquals(PersonStream.of(aPerson, empty),
                     PersonStream.of(aPerson, empty));
    }

    @Test
    public void equalityTowardNullAndObject() {
        assertNotEquals(somePersonStream(), null);
        assertNotEquals(somePersonStream(), new Object());
    }

    @Test
    public void emptyStreamHasNoUpdates() {
        PersonStream billStream = PersonStream.empty(aPerson);

        assertTrue(billStream.isEmpty());
    }

    private PersonStream somePersonStream() {
        return PersonStream.of(aPerson, empty);
    }
}