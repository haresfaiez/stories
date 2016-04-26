package stories.person;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AttendeeTest {

    @Test
    public void equalityWithDifferentPerson() {
        assertNotEquals(Attendee.from(personWithId(FIRST_ID)),
                        Attendee.from(personWithId(SECOND_ID)));
    }

    @Test
    public void equalityWithSamePerson() {
        assertEquals(Attendee.from(personWithId(FIRST_ID)),
                     Attendee.from(personWithId(FIRST_ID)));
    }

    @Test
    public void equalityTowardNullAndObject() {
        assertNotEquals(Attendee.from(personWithId(FIRST_ID)), null);
        assertNotEquals(Attendee.from(personWithId(FIRST_ID)), new Object());
    }

    private Person personWithId(UUID id) {
        return new Person(id, "A name of that person");
    }

   static final UUID FIRST_ID  = UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461979");
   static final UUID SECOND_ID = UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d2370000079");
}