package stories.event;

import org.junit.Test;
import stories.person.Attendee;
import stories.person.Person;
import stories.stream.PersonStream;
import stories.stream.StreamRepository;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static stories.event.AttendeeUpdate.from;
import static stories.event.Event.withNoUpdates;
import static stories.event.EventSpecification.withNoAttendees;
import static stories.event.EventStatement.at;

public class GeneratePersonStreamTest {

    @Test
    public void streamOfAPersonForAnEvent() {
        Person emma = new Person(1L, "Emma");
        StreamRepository repository = new StreamRepository();

        PersonStream actualStream = repository.streamOf(emma);

        assertEquals(PersonStream.empty(emma), actualStream);
    }

    protected EventUpdate someEventUpdate() {
        return new EventUpdate(attendeeOfPersonWithId(1L),
                from("Some message"),
                someTime(),
                eventWithId(1L));
    }

    protected static Attendee attendeeOfPersonWithId(Long id) {
        return new Attendee(new Person(id, "Bill"));
    }

    private Event eventWithId(Long id) {
        return withNoUpdates(id, withNoAttendees(someStatement()));
    }

    private EventStatement someStatement() {
        return at(someTime(), "Concert title");
    }

    private LocalDateTime someTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }
}
