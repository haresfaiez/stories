package stories.event;

import org.junit.Before;
import org.junit.Test;
import stories.person.Attendee;
import stories.person.Person;
import stories.stream.PersonStream;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static stories.event.Note.from;
import static stories.event.Event.identified;
import static stories.event.EventSpecification.statedBy;
import static stories.event.EventStatement.at;
import static stories.event.Updates.singleton;

public class GeneratePersonStreamTest {

    private Person emma;
    private Event event;
    private Attendee bill;

    @Before
    public void setUp() {
        emma = new Person(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461979"), "Emma");
        bill = anAttendee();
        event = anEvent();
    }

    @Test
    public void personStreamForAnEventWithOneUpdate() {
        event.update(bill, from("Best evening"), billUpdateTime());

        PersonStream actual = event.streamOf(emma);

        PersonStream expected = PersonStream.of(emma, singleton(billEventUpdate()));
        assertEquals(expected, actual);
    }

    @Test
    public void personStreamForAnEventWithoutUpdates() {
        PersonStream actual = event.streamOf(emma);

        assertEquals(PersonStream.empty(emma), actual);
    }

    private EventUpdate billEventUpdate() {
        return new EventUpdate(bill,
                         from("Best evening"),
                         billUpdateTime(),
                         event);
    }

    private Attendee anAttendee() {
        return Attendee.from(new Person(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461000"), "Bill"));
    }

    private Event anEvent() {
        return identified(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461970"),
                             statedBy(at(eventTime(), "Event title")));
    }

    private LocalDateTime eventTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }

    private LocalDateTime billUpdateTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }
}
