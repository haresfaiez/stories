package stories.event;

import org.junit.Test;
import stories.person.Person;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

import static java.time.LocalDateTime.of;
import static org.junit.Assert.assertTrue;
import static stories.event.Event.identified;
import static stories.event.EventSpecification.statedBy;
import static stories.event.EventStatement.at;
import static stories.person.Attendee.from;

public class EventAttendeeTest {

    @Test
    public void manyPersonsCanAttendAnEvent() {
        Person anAttendee      = personWithId(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461979"));
        Person anOtherAttendee = personWithId(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d2070000009"));
        Event  aConcert        = someEvent();

        anAttendee.attend(aConcert);
        anOtherAttendee.attend(aConcert);

        assertTrue(aConcert.specification.isAttendedBy(from(anAttendee)));
        assertTrue(aConcert.specification.isAttendedBy(from(anOtherAttendee)));
    }

    private Event someEvent() {
        return identified(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461979"),
                             statedBy(at(someDate(), "Event title")));
    }

    private LocalDateTime someDate() {
        return of(2015, Month.APRIL, 19, 20, 30);
    }

    private Person personWithId(UUID id) {
        return new Person(id, "Name of that person");
    }
}
