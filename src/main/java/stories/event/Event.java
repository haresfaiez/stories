package stories.event;

import stories.person.Person;

import java.time.LocalDateTime;

public class Event {
    protected Long               id;
    protected EventSpecification specification;
    protected Attendees          attendees;

    public Event(Long id, String title, LocalDateTime time) {
        this.id            = id;
        this.specification = new EventSpecification(title, time);
        this.attendees     = Attendees.noOne();
    }

    public void attendee(Person newAttendee) {
        attendees = attendees.with(newAttendee);
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
        return String.format("%s, %s, %s", id, specification.title, specification.time);
    }

}
