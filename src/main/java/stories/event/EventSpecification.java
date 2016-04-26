package stories.event;

import stories.person.Attendee;

import java.io.Serializable;
import java.time.LocalDateTime;

class EventSpecification implements Serializable {
    protected final EventStatement statement;
    protected final Attendees      attendees;

    protected EventSpecification(EventStatement statement,
                                 Attendees      attendees) {
        this.statement = statement;
        this.attendees = attendees;
    }

    protected EventSpecification withAttendee(Attendee newAttendee) {
        return new EventSpecification(statement, attendees.with(newAttendee));
    }

    protected Boolean isAttendedBy(Attendee potential) {
        return attendees.include(potential);
    }

    @Override
    public boolean equals(Object o) {
        if (null == o)                          return Boolean.FALSE;
        if (!(o instanceof EventSpecification)) return Boolean.FALSE;
        EventSpecification other = (EventSpecification) o;
        return other.isStated(statement)
                && other.isAttendedBy(attendees);
    }

    private Boolean isStated(EventStatement other) {
        return statement.equals(other);
    }

    private Boolean isAttendedBy(Attendees other) {
        return attendees.equals(other);
    }

    protected EventSpecification at(LocalDateTime time) {
        return new EventSpecification(statement.withTime(time), attendees);
    }

    protected EventSpecification entitled(String title) {
        return new EventSpecification(statement.withTitle(title), attendees);
    }

    protected EventSpecification attendedBy(Attendees attendees) {
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

    public static EventSpecification statedBy(EventStatement statement) {
        return new EventSpecification(statement, Attendees.none());
    }

    protected static EventSpecification identity() {
        return statedBy(EventStatement.identity());
    }
}
