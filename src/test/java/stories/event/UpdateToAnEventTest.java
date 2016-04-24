package stories.event;

import org.junit.Before;
import org.junit.Test;
import stories.person.Attendee;
import stories.person.Person;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

import static org.junit.Assert.assertTrue;
import static stories.event.Event.withNoUpdates;
import static stories.event.EventSpecification.withNoAttendees;
import static stories.event.EventStatement.at;

public class UpdateToAnEventTest {

    private Event aConcert;
    private Attendee billAsAttendee;

    @Before
    public void setUp() {
        aConcert = withNoUpdates(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461970"),
                                 someSpecification());
        Person billAsPerson = new Person(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461979"), "Bill");
        billAsPerson.attend(aConcert);
        billAsAttendee = new Attendee(billAsPerson);
    }

    @Test
    public void attendeeCanUpdateToAnEvent() {
        billAsAttendee.update(aConcert, billUpdate(), billUpdateTime());

        EventUpdate expectedEventUpdate = new EventUpdate(billAsAttendee,
                                                          billUpdate(),
                                                          billUpdateTime(),
                                                          aConcert);
        assertTrue(aConcert.updates.contains(expectedEventUpdate));
    }

    private AttendeeUpdate billUpdate() {
        return AttendeeUpdate.from("Bill message");
    }

    private EventSpecification someSpecification() {
        return withNoAttendees(at(eventTime(), "Concert title"));
    }

    private LocalDateTime billUpdateTime() {
        return eventTime().plusHours(1);
    }

    private LocalDateTime eventTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }
}
