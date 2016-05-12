package cli;

import org.junit.Test;
import persistence.cassandra.CassandraEventRepository;
import persistence.neo4j.PersonNeo4jRepository;
import stories.event.BuildEvent;
import stories.event.Event;
import stories.person.Person;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static persistence.neo4j.PersonFixture.bill;
import static persistence.neo4j.PersonFixture.followedByBill;


public class ServiceTest {
    UUID targetUUID = UUID.fromString("32b0a8e0-0a3d-11e6-8cf0-2d237e461979");
    Event expectedEvent = BuildEvent.identified(targetUUID).product();
    Person expectedPerson = new Person(targetUUID, "_");

    @Test
    public void retrieveEventFromRepository() {
        CassandraEventRepository repository = mock(CassandraEventRepository.class);
        when(repository.eventWithId(targetUUID)).thenReturn(expectedEvent);
        Service service = new Service(repository, null);

        assertEquals(expectedEvent, service.of(targetUUID));
        verify(repository).eventWithId(targetUUID);
    }

    @Test
    public void retrievePersonFromRepository() {
        PersonNeo4jRepository repository = mock(PersonNeo4jRepository.class);
        when(repository.personWithId(targetUUID)).thenReturn(expectedPerson);
        Service service = new Service(mock(CassandraEventRepository.class), repository);

        assertEquals(expectedPerson, service.person(targetUUID));
        verify(repository).personWithId(targetUUID);
    }

    @Test
    public void retrieveFollowedPersonsFromRepository() {
        PersonNeo4jRepository repository = mock(PersonNeo4jRepository.class);
        when(repository.personsFollowedBy(bill())).thenReturn(followedByBill());
        Service service = new Service(mock(CassandraEventRepository.class), repository);

        assertEquals(followedByBill(), service.followedBy(bill()));
        verify(repository).personsFollowedBy(bill());
    }

    @Test
    public void askForPerson() {
        Service service = mock(Service.class);
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
