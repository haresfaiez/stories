package stories.event;

import org.junit.Before;
import org.junit.Test;
import stories.person.Attendee;
import stories.person.Person;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

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
        BuildEvent build = BuildEvent.identified(theId())
                                     .at(theTime())
                                     .entitled(theTitle())
                                     .attendedBy(theAttendees())
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
        return Attendees.singleton(Attendee.from(new Person(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461979"), "Mee")));
    }

    private String theTitle() {
        return "Event title";
    }

    private LocalDateTime theTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }

    private UUID theId() {
        return UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461979");
    }
}
