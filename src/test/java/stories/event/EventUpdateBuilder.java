package stories.event;

import stories.person.Attendee;
import stories.person.Person;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

import static stories.event.Note.from;
import static stories.event.EventSpecification.statedBy;

public class EventUpdateBuilder {

    protected static EventUpdate aFrozenEventUpdate() {
        return new EventUpdate(attendeeOfPersonWithId(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461979")),
                from("Some message"),
                someTime(),
                eventWithId(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461970")));
    }

    protected static EventUpdate eventUpdateWith(Note message) {
        return new EventUpdate(attendeeOfPersonWithId(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461979")), message, someTime(), eventWithId(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461970")));
    }

    protected static EventUpdate eventUpdateAt(LocalDateTime time) {
        return new EventUpdate(attendeeOfPersonWithId(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461979")), someMessage(), time, eventWithId(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461970")));
    }

    protected static Note someMessage() {
        return from("Some message");
    }

    protected static EventUpdate eventUpdateFor(Event event) {
        return new EventUpdate(attendeeOfPersonWithId(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461979")), someMessage(), someTime(), event);
    }

    protected static EventUpdate someEventUpdate() {
        return new EventUpdate(attendeeOfPersonWithId(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461979")),
                               from("Some message"),
                               someTime(),
                               eventWithId(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461970")));
    }

    protected static Event eventWithId(UUID id) {
        return Event.identified(id, someSpecification());
    }

    protected static EventSpecification someSpecification() {
        return statedBy(EventStatement.at(someTime(), "Some Concert"));
    }

    protected static LocalDateTime someTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }

    protected static Attendee attendeeOfPersonWithId(UUID id) {
        return new Attendee(new Person(id, "Bill"));
    }
}
