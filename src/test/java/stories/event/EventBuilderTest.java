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
        BuildEvent build = BuildEvent.identified(id())
                                     .at(time())
                                     .entitled(title())
                                     .attendedBy(attendees())
                                     .withUpdates(updates());

        Event actual = build.product();

        assertEquals(expected, actual);
        assertEquals(expected.updates, actual.updates);
        assertEquals(expected.specification, actual.specification);
    }

    private Event expectedProduct() {
        return new Event(id(),
                         new EventSpecification(new EventStatement(time(), title()),
                                                attendees()),
                                                updates());
    }

    private Updates updates() {
        return Updates.singleton(aFrozenEventUpdate());
    }

    private Attendees attendees() {
        return Attendees.singleton(Attendee.from(new Person(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461979"), "Mee")));
    }

    private String title() {
        return "Event title";
    }

    private LocalDateTime time() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }

    private UUID id() {
        return UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461979");
    }
}
