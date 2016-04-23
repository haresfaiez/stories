package stories.builder;

import stories.event.EventUpdate;
import stories.event.AttendeeUpdate;

import java.time.LocalDateTime;
import java.time.Month;

import static stories.builder.AttendeeBuilder.billAttendee;

public class UpdateBuilder {
    public static AttendeeUpdate someAttendeeUpdate() {
       return AttendeeUpdate.at(someTime(), "Some message");
    }

    public static EventUpdate someEventUpdate() {
        return EventUpdate.by(billAttendee(1L), someAttendeeUpdate());
    }

    public static LocalDateTime someTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }
}
