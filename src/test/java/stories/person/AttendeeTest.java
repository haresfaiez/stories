package stories.person;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AttendeeTest {

    @Test
    public void equalityWithDifferentPerson() {
        Attendee anAttendee         = Attendee.from(personWithId(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461979")));
        Attendee aDifferentAttendee = Attendee.from(personWithId(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d2370000079")));

        assertNotEquals(anAttendee, aDifferentAttendee);
    }

    @Test
    public void equalityWithSamePerson() {
        Attendee anAttendee      = Attendee.from(personWithId(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461979")));
        Attendee theSameAttendee = Attendee.from(personWithId(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461979")));

        assertEquals(anAttendee, theSameAttendee);
    }

    @Test
    public void equalityTowardNullAndObject() {
        Attendee anAttendee = Attendee.from(personWithId(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461979")));

        assertNotEquals(anAttendee, null);
        assertNotEquals(anAttendee, new Object());
    }

    private Person personWithId(UUID id) {
        return new Person(id, "A person of that person");
    }
}