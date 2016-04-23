package stories.person;

import stories.event.AttendeeUpdate;
import stories.event.Event;

import java.time.LocalDateTime;

public class Attendee {
    public Person person;

    public Attendee(Person person) {
        this.person = person;
    }

    public void update(Event          target,
                       AttendeeUpdate update,
                       LocalDateTime  time) {
        target.updateBy(this, update, time);
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

    public static Attendee from(Person person) {
        return new Attendee(person);
    }
}
