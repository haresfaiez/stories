package stories.event;

import stories.person.Attendee;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class Attendees {
    private final Set collection;

    protected Attendees(Set collection) {
        this.collection = collection;
    }

    public Attendees with(Attendee newAttendee) {
        Set resultSet = new HashSet(collection);
        resultSet.add(newAttendee);
        return new Attendees(resultSet);
    }

    public Boolean contains(Attendee potentialAttendee) {
        return collection.contains(potentialAttendee);
    }

    @Override
    public boolean equals(Object o) {
        if (null == o)                 return Boolean.FALSE;
        if (!(o instanceof Attendees)) return Boolean.FALSE;
        Attendees other = (Attendees) o;
        return other.hasAttendees(collection);
    }

    private Boolean hasAttendees(Set otherAttendees) {
        return collection.equals(otherAttendees);
    }

    @Override
    public int hashCode() {
        return collection.hashCode();
    }

    @Override
    public String toString() {
        return collection.toString();
    }

    public static Attendees noOne() {
        return new Attendees(Collections.emptySet());
    }
}
