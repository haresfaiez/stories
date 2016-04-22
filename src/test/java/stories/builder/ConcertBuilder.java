package stories.builder;

import stories.event.Event;
import stories.event.EventSpecification;

import java.time.LocalDateTime;
import java.time.Month;

import static stories.event.Event.withNoUpdates;
import static stories.event.EventStatement.at;

public class ConcertBuilder {
    public static Event someConcert() {
        return withNoUpdates(8L, someEventSpecification());
    }

    private static EventSpecification someEventSpecification() {
        return EventSpecification.withNoAttendees(at(someTime(), "Concert title"));
    }

    public static LocalDateTime someTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }
}
