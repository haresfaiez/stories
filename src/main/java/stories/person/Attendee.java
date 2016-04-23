package stories.person;

import stories.event.Event;
import stories.event.AttendeeUpdate;

public class Attendee {
    private Person person;

    public Attendee(Person person) {
        this.person = person;
    }

    public void update(Event target,
                       AttendeeUpdate update) {
        target.updateBy(this, update);
    }

    @Override
    public boolean equals(Object o) {
        if (null == o)                return Boolean.FALSE;
        if (!(o instanceof Attendee)) return Boolean.FALSE;
        Attendee other = (Attendee) o;
        return other.person.equals(person);
    }

    @Override
    public int hashCode() {
        return person.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s", person);
    }
}
