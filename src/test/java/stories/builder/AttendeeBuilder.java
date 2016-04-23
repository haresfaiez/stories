package stories.builder;

import stories.person.Attendee;
import stories.person.Person;

public class AttendeeBuilder {
    public static Attendee someAttendee(Long id) {
        return new Attendee(new Person(id, "Bob"));
    }

    public static Attendee mikeAttendee(Long id) {
        return new Attendee(mike(id));
    }

    public static Person mike(Long id) {
        return new Person(id, "Mike");
    }

    public static Attendee billAttendee(Long id) {
        return new Attendee(bill(id));
    }

    public static Person bill(Long id) {
        return new Person(id, "Bill");
    }
}
