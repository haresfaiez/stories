package stories.builder;

import stories.event.Event;

import java.time.LocalDateTime;
import java.time.Month;

import static stories.event.Event.withNoAttendees;
import static stories.event.EventStatement.at;

public class ConcertBuilder {
    public static Event someConcert() {
        return withNoAttendees(8L, at(someTime(), "Concert title"));
    }

    public static LocalDateTime someTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }
}
