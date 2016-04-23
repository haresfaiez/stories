package stories.stream;

import org.junit.Before;
import org.junit.Test;
import stories.person.Person;

import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class PersonStreamTest {
    Set empty = Collections.emptySet();
    Person aPerson;

    @Before
    public void setUp() throws Exception {
        aPerson = new Person(1L, "Bill");
    }

    @Test
    public void equalityWithSamePersonAndUpdates() {
        assertEquals(new PersonStream(aPerson, empty),
                     new PersonStream(aPerson, empty));
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
        return new PersonStream(aPerson, empty);
    }
}