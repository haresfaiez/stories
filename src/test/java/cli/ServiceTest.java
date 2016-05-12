package cli;

import org.junit.Test;
import stories.event.BuildEvent;
import stories.event.Event;
import persistence.cassandra.CassandraEventRepository;
import stories.person.Person;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class ServiceTest {
    UUID targetUUID = UUID.fromString("32b0a8e0-0a3d-11e6-8cf0-2d237e461979");
    Event expectedEvent = BuildEvent.identified(targetUUID).product();

    @Test
    public void retrieveEventFromRepository() {
        CassandraEventRepository repository = mock(CassandraEventRepository.class);
        when(repository.eventWithId(targetUUID)).thenReturn(expectedEvent);
        Service service = new Service(repository);

        assertEquals(expectedEvent, service.of(targetUUID));
        verify(repository).eventWithId(targetUUID);
    }

    @Test
    public void askForPerson() {
        Service service = mock(Service.class);
        Person expectedPerson = new Person(targetUUID, "_");
        when(service.person(targetUUID)).thenReturn(expectedPerson);
        String[] arguments = { "-request", "person",
                "-identity", "32b0a8e0-0a3d-11e6-8cf0-2d237e461979"};
        Request request = new Request(service, arguments);
        assertEquals(expectedPerson.toString(), request.response());
        verify(service).person(targetUUID);
    }

    @Test
    public void askForEvent() {
        Service service = mock(Service.class);
        when(service.of(targetUUID)).thenReturn(expectedEvent);
        String[] arguments = { "-request", "event",
                               "-identity", "32b0a8e0-0a3d-11e6-8cf0-2d237e461979"};
        Request request = new Request(service, arguments);
        assertEquals(expectedEvent.toString(), request.response());
        verify(service).of(targetUUID);
    }
}
