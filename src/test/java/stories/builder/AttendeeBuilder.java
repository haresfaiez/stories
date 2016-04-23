package stories.builder;

import stories.person.Attendee;
import stories.person.Person;

public class AttendeeBuilder {
    public static Attendee someAttendee(Long id) {
        return new Attendee(new Person(id, "Bob"));
    }

    public static Attendee mike(Long id) {
        return new Attendee(new Person(id, "Mike"));
    }

    public static Attendee bill(Long id) {
        return new Attendee(new Person(id, "Bill"));
    }
}
