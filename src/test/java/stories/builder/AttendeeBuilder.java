package stories.builder;

import stories.person.Attendee;

public class AttendeeBuilder {
    public static Attendee someAttendee(Long id) {
        return new Attendee(id, "Bob");
    }

    public static Attendee mike(Long id) {
        return new Attendee(id, "Mike");
    }

    public static Attendee bill(Long id) {
        return new Attendee(id, "Bill");
    }
}
