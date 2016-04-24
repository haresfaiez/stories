package stories.event;

import stories.person.Attendee;
import stories.person.Person;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

import static stories.event.AttendeeUpdate.from;
import static stories.event.EventSpecification.withNoAttendees;

public class EventUpdateBuilder {

    protected static EventUpdate aFrozenEventUpdate() {
        return new EventUpdate(attendeeOfPersonWithId(1L),
                from("Some message"),
                someTime(),
                eventWithId(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461970")));
    }

    protected static EventUpdate eventUpdateWith(AttendeeUpdate message) {
        return new EventUpdate(attendeeOfPersonWithId(1L), message, someTime(), eventWithId(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461970")));
    }

    protected static EventUpdate eventUpdateAt(LocalDateTime time) {
        return new EventUpdate(attendeeOfPersonWithId(1L), someMessage(), time, eventWithId(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461970")));
    }

    protected static AttendeeUpdate someMessage() {
        return from("Some message");
    }

    protected static EventUpdate eventUpdateFor(Event event) {
        return new EventUpdate(attendeeOfPersonWithId(1L), someMessage(), someTime(), event);
    }

    protected static EventUpdate someEventUpdate() {
        return new EventUpdate(attendeeOfPersonWithId(1L),
                               from("Some message"),
                               someTime(),
                               eventWithId(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461970")));
    }

    protected static Event eventWithId(UUID id) {
        return Event.withNoUpdates(id, someSpecification());
    }

    protected static EventSpecification someSpecification() {
        return withNoAttendees(EventStatement.at(someTime(), "Some Concert"));
    }

    protected static LocalDateTime someTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }

    protected static Attendee attendeeOfPersonWithId(Long id) {
        return new Attendee(new Person(id, "Bill"));
    }
}
