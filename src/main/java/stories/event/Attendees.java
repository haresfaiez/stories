package stories.event;

import stories.person.Person;

import java.util.Collection;
import java.util.LinkedList;

public class Attendees {
    protected Collection attendees;

    protected Attendees(Collection attendees) {
        this.attendees = attendees;
    }

    Attendees with(Person attendee) {
        Collection result = new LinkedList(attendees);
        result.add(attendee);
        return new Attendees(result);
    }

    boolean contains(Person potentialAttendee) {
        return attendees.contains(potentialAttendee);
    }

    @Override
    public boolean equals(Object o) {
        if (null == o)                 return Boolean.FALSE;
        if (!(o instanceof Attendees)) return Boolean.FALSE;
        Attendees other = (Attendees) o;
        return other.hasAttendees(attendees);
    }

    private boolean hasAttendees(Collection otherAttendees) {
        return attendees.equals(otherAttendees);
    }

    @Override
    public int hashCode() {
        return attendees.hashCode();
    }

    @Override
    public String toString() {
        return attendees.toString();
    }

    public static Attendees noOne() {
        return new Attendees(new LinkedList());
    }
}
