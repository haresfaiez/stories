package stories.event;

import stories.person.Attendee;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Attendees implements Serializable {
    public final Set<Attendee> members;

    protected Attendees(Set<Attendee> members) {
        this.members = members;
    }

    public Attendees with(Attendee newMember) {
        Set<Attendee> resultMembers = new HashSet<>(members);
        resultMembers.add(newMember);
        return new Attendees(resultMembers);
    }

    public Boolean include(Attendee potential) {
        return members.contains(potential);
    }

    @Override
    public boolean equals(Object o) {
        if (null == o)                 return Boolean.FALSE;
        if (!(o instanceof Attendees)) return Boolean.FALSE;
        Attendees others = (Attendees) o;
        return others.are(members);
    }

    private Boolean are(Set otherMembers) {
        return members.equals(otherMembers);
    }

    @Override
    public int hashCode() {
        return members.hashCode();
    }

    @Override
    public String toString() {
        return members.toString();
    }

    public static Attendees none() {
        return new Attendees(Collections.emptySet());
    }

    public static Attendees singleton(Attendee member) {
        return none().with(member);
    }
}
