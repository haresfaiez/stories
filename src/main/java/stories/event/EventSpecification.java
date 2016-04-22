package stories.event;

import stories.person.Person;

public class EventSpecification {
    protected final EventStatement statement;
    protected final Attendees      attendees;

    public EventSpecification(EventStatement statement, Attendees attendees) {
        this.statement = statement;
        this.attendees = attendees;
    }

    protected EventSpecification withAttendee(Person newAttendee) {
        return new EventSpecification(statement, attendees.with(newAttendee));
    }

    @Override
    public String toString() {
        return String.format("%s, %s", statement, attendees);
    }

    public static EventSpecification withNoAttendees(EventStatement specification) {
        return new EventSpecification(specification, Attendees.noOne());
    }
}
