package stories.event;

import org.junit.Test;
import stories.person.Person;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

import static java.time.LocalDateTime.of;
import static org.junit.Assert.assertTrue;
import static stories.event.Event.withNoUpdates;
import static stories.event.EventSpecification.withNoAttendees;
import static stories.event.EventStatement.at;
import static stories.person.Attendee.from;

public class EventAttendeeTest {

    @Test
    public void manyPersonsCanAttendAnEvent() {
        Person anAttendee      = personWithId(1L);
        Person anOtherAttendee = personWithId(122L);
        Event  aConcert        = someEvent();

        anAttendee.attend(aConcert);
        anOtherAttendee.attend(aConcert);

        assertTrue(aConcert.specification.hasAttendee(from(anAttendee)));
        assertTrue(aConcert.specification.hasAttendee(from(anOtherAttendee)));
    }

    private Event someEvent() {
        return withNoUpdates(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461979"),
                             withNoAttendees(at(someDate(), "Event title")));
    }

    private LocalDateTime someDate() {
        return of(2015, Month.APRIL, 19, 20, 30);
    }

    private Person personWithId(Long id) {
        return new Person(id, "Name of that person");
    }
}
