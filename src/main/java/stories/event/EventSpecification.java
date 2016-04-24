package stories.event;

import stories.person.Attendee;

class EventSpecification {
    protected final EventStatement statement;
    protected final Attendees      attendees;

    private EventSpecification(EventStatement statement,
                               Attendees      attendees) {
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
    public String toString() {
        return String.format("%s, %s", statement, attendees);
    }

    public static EventSpecification withNoAttendees(EventStatement statement) {
        return new EventSpecification(statement, Attendees.none());
    }
}
