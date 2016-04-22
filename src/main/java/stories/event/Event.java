package stories.event;

import stories.person.Person;

public class Event {
    protected Long               id;
    protected EventSpecification specification;
    protected Attendees          attendees;
    protected Updates            updates;

    private Event(Long id,
                  EventSpecification specification,
                  Attendees attendees,
                  Updates updates) {
        this.id            = id;
        this.specification = specification;
        this.attendees     = attendees;
        this.updates       = updates;
    }

    public void updateBy(Person editor,
                         PersonUpdate update) {
        updates = updates.with(EventUpdate.by(editor, update));
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

    public static Event withNoAttendees(Long id,
                                        EventSpecification specification) {
        return new Event(id, specification, Attendees.noOne(), Updates.noOne());
    }

}
