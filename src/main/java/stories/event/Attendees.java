package stories.event;

import stories.person.Attendee;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

class Attendees {
    private final Collection collection;

    protected Attendees(Collection collection) {
        this.collection = collection;
    }

    public Attendees with(Attendee newAttendee) {
        Collection resultCollection = new LinkedList(collection);
        resultCollection.add(newAttendee);
        return new Attendees(resultCollection);
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

    private Boolean hasAttendees(Collection otherAttendees) {
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
        return new Attendees(Collections.EMPTY_LIST);
    }
}
