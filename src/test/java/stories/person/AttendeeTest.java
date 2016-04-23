package stories.person;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AttendeeTest {

    @Test
    public void equalityWithDifferentPerson() {
        Attendee anAttendee         = Attendee.from(personWithId(1L));
        Attendee aDifferentAttendee = Attendee.from(personWithId(892L));

        assertNotEquals(anAttendee, aDifferentAttendee);
    }

    @Test
    public void equalityWithSamePerson() {
        Attendee anAttendee      = Attendee.from(personWithId(1L));
        Attendee theSameAttendee = Attendee.from(personWithId(1L));

        assertEquals(anAttendee, theSameAttendee);
    }

    @Test
    public void equalityTowardNullAndObject() {
        Attendee anAttendee = Attendee.from(personWithId(1L));

        assertNotEquals(anAttendee, null);
        assertNotEquals(anAttendee, new Object());
    }

    private Person personWithId(Long id) {
        return new Person(id, "A person of that person");
    }
}