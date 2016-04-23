package stories.event;

import org.junit.Test;
import stories.person.Attendee;
import stories.person.Person;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AttendeesTest {

    @Test
    public void noOneAttendeeEqualsEmptyCollection() {
        assertEquals(new Attendees(anEmptySet()), Attendees.noOne());
    }

    @Test
    public void equalityWithEqualCollections() {
        assertEquals(new Attendees(anEmptySet()), new Attendees(anEmptySet()));
    }

    @Test
    public void equalityWithDifferentCollections() {
        assertNotEquals(new Attendees(anEmptySet()),
                        new Attendees(notAnEmptySet()));
    }

    @Test
    public void equalityTowardNullAndObject() {
        assertNotEquals(new Attendees(anEmptySet()), null);
        assertNotEquals(new Attendees(anEmptySet()), new Object());
    }

    private Set notAnEmptySet() {
        Set result = new HashSet();
        result.add(Attendee.from(arbitraryPerson()));
        return result;
    }

    private Person arbitraryPerson() {
        return new Person(1L, "Some name");
    }

    private Set anEmptySet() {
        return Collections.emptySet();
    }
}
