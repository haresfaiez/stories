package stories.event;

import stories.person.Attendee;
import stories.person.Person;
import stories.stream.PersonStream;

import java.time.LocalDateTime;

public class Event {
    protected Long               id;
    protected EventSpecification specification;
    protected Updates            updates;

    protected Event(Long               id,
                  EventSpecification specification,
                  Updates            updates) {
        this.id            = id;
        this.specification = specification;
        this.updates       = updates;
    }

    public void updateBy(Attendee       editor,
                         AttendeeUpdate update,
                         LocalDateTime  time) {
        updates = updates.with(new EventUpdate(editor, update, time, this));
    }

    public void attendee(Person attendee) {
        specification = specification.withAttendee(Attendee.from(attendee));
    }

    public PersonStream streamOf(Person director) {
        return PersonStream.of(director, updates);
    }

    @Override
    public boolean equals(Object o) {
        if (null == o)             return Boolean.FALSE;
        if (!(o instanceof Event)) return Boolean.FALSE;
        Event other = (Event) o;
        return other.hasId(id);
    }

    private Boolean hasId(Long otherId) {
        return id.equals(otherId);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s, %s", id, specification);
    }

    public static Event withNoUpdates(Long               id,
                                      EventSpecification specification) {
        return new Event(id, specification, Updates.none());
    }
}
