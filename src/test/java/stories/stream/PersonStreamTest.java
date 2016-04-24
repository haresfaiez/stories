package stories.stream;

import org.junit.Before;
import org.junit.Test;
import stories.event.Updates;
import stories.person.Person;

import java.util.UUID;

import static org.junit.Assert.*;

public class PersonStreamTest {
    Updates empty = Updates.none();
    Person aPerson;

    @Before
    public void setUp() throws Exception {
        aPerson = new Person(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461979"), "Bill");
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