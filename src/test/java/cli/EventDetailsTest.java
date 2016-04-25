package cli;

import org.junit.Test;
import stories.event.BuildEvent;
import stories.event.Event;
import stories.event.EventRepository;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class EventDetailsTest {
    UUID targetUUID = UUID.fromString("32b0a8e0-0a3d-11e6-8cf0-2d237e461979");
    Event expectedEvent = BuildEvent.identifiedBy(targetUUID).product();

    @Test
    public void retrieveEventFromRepository() {
        EventRepository repository = mock(EventRepository.class);
        when(repository.eventWithId(targetUUID)).thenReturn(expectedEvent);
        EventDetails eventDetails = new EventDetails(repository);

        assertEquals(expectedEvent, eventDetails.of(targetUUID));
        verify(repository).eventWithId(targetUUID);
    }

    @Test
    public void requestAsksForTargetEvent() {
        EventDetails eventDetails = mock(EventDetails.class);
        String[] arguments = { "-request", "event",
                               "-identity", "32b0a8e0-0a3d-11e6-8cf0-2d237e461979"};
        Request request = new Request(eventDetails, arguments);
        request.response();
        verify(eventDetails).of(targetUUID);
    }
}
