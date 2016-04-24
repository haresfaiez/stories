package stories.event;

import org.junit.Before;
import org.junit.Test;
import stories.person.Attendee;
import stories.person.Person;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static stories.event.EventUpdateBuilder.aFrozenEventUpdate;

public class EventBuilderTest {

    Event expected;
    @Before
    public void setUp(){
        expected = expectedProduct();
    }

    @Test
    public void buildEventApi() {
        BuildEvent build = BuildEvent.identifiedBy(theId())
                                     .at(theTime())
                                     .entitled(theTitle())
                                     .withAttendees(theAttendees())
                                     .withUpdates(theUpdates());

        Event actual = build.product();

        assertEquals(expected, actual);
        assertEquals(expected.updates, actual.updates);
        assertEquals(expected.specification, actual.specification);
    }

    private Event expectedProduct() {
        return new Event(theId(),
                         new EventSpecification(new EventStatement(theTime(),
                                                                   theTitle()),
                                                theAttendees()),
                         theUpdates());
    }

    private Updates theUpdates() {
        return Updates.singleton(aFrozenEventUpdate());
    }

    private Attendees theAttendees() {
        return Attendees.singleton(Attendee.from(new Person(1L, "Mee")));
    }

    private String theTitle() {
        return "Event title";
    }

    private LocalDateTime theTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }

    private Long theId() {
        return 1L;
    }
}
