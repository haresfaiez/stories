package stories.event;

import stories.person.Attendee;

import java.time.LocalDateTime;

class EventSpecification {
    protected final EventStatement statement;
    protected final Attendees      attendees;

    protected EventSpecification(EventStatement statement,
                                 Attendees attendees) {
        this.statement = statement;
        this.attendees = attendees;
    }

    protected EventSpecification withAttendee(Attendee newAttendee) {
        return new EventSpecification(statement, attendees.with(newAttendee));
    }

    protected Boolean hasAttendee(Attendee potentialAttendee) {
        return attendees.contains(potentialAttendee);
    }

    @Override
    public boolean equals(Object o) {
        if (null == o)                     return Boolean.FALSE;
        if (!(o instanceof EventSpecification)) return Boolean.FALSE;
        EventSpecification other = (EventSpecification) o;
        return other.isStatedAs(statement)
                && other.isAttendedBy(attendees);
    }

    private Boolean isStatedAs(EventStatement otherStatement) {
        return statement.equals(otherStatement);
    }

    private Boolean isAttendedBy(Attendees otherAttendees) {
        return attendees.equals(otherAttendees);
    }

    protected EventSpecification withTime(LocalDateTime time) {
        return new EventSpecification(statement.withTime(time), attendees);
    }

    protected EventSpecification withTitle(String title) {
        return new EventSpecification(statement.withTitle(title), attendees);
    }

    protected EventSpecification withAttendees(Attendees attendees) {
        return new EventSpecification(statement, attendees);
    }

    @Override
    public int hashCode() {
        return statement.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s, %s", statement, attendees);
    }

    public static EventSpecification withNoAttendees(EventStatement statement) {
        return new EventSpecification(statement, Attendees.none());
    }

    protected static EventSpecification identityElement() {
        return withNoAttendees(EventStatement._identityElement());
    }
}
