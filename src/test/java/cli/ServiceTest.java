package cli;

import org.junit.Test;
import persistence.cassandra.CassandraEventRepository;
import persistence.neo4j.PersonNeo4jRepository;
import stories.event.Attendees;
import stories.event.BuildEvent;
import stories.event.Event;
import stories.person.Attendee;
import stories.person.Person;

import java.util.Collections;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static persistence.neo4j.PersonFixture.bill;
import static persistence.neo4j.PersonFixture.followedByBill;


public class ServiceTest {
    UUID targetUUID = UUID.fromString("32b0a8e0-0a3d-11e6-8cf0-2d237e461979");
    Event expectedEvent = BuildEvent.identified(targetUUID).attendedBy
            (Attendees.singleton(Attendee.from(bill())))
            .product();
    Person expectedPerson = new Person(targetUUID, "_");

    @Test
    public void retrieveEventFromRepository() {
        CassandraEventRepository repository = mock(CassandraEventRepository.class);
        when(repository.eventWithId(targetUUID)).thenReturn(expectedEvent);
        Service service = new Service(repository, null);

        assertEquals(expectedEvent, service.event(targetUUID));
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
        when(service.person(targetUUID)).thenReturn(bill());
        when(service.followedBy(bill())).thenReturn(followedByBill());
        String[] arguments = { "-request", "person",
                "-identity", "32b0a8e0-0a3d-11e6-8cf0-2d237e461979"};
        Request request = new Request(service, arguments);

        assertEquals(new PersonView(bill(), followedByBill()).output(),
                     request.response());
        verify(service).person(targetUUID);
    }

    @Test
    public void askForEvent() {
        Service service = mock(Service.class);
        when(service.event(targetUUID)).thenReturn(expectedEvent);
        String[] arguments = { "-request", "event",
                               "-identity", "32b0a8e0-0a3d-11e6-8cf0-2d237e461979"};
        Request request = new Request(service, arguments);

        assertEquals(eventView(expectedEvent), request.response());
        verify(service).event(targetUUID);
    }

    private String eventView(Event subject) {
        StringBuilder expected = new StringBuilder();
        expected.append(subject);
        expected.append("\n");
        expected.append("Attendees:");
        expected.append("\n");
        for (Attendee eachAttendee : subject.specification.attendees.members)
            expected.append(new PersonView(eachAttendee.person, Collections.emptySet()).output());
        return expected.toString();
    }
}
