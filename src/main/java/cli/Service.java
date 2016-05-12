package cli;

import persistence.cassandra.CassandraEventRepository;
import persistence.neo4j.PersonNeo4jRepository;
import stories.event.Event;
import stories.person.Person;

import java.util.Set;
import java.util.UUID;

public class Service {
    private CassandraEventRepository eventRepository;
    private PersonNeo4jRepository personRepository;

    public Service(CassandraEventRepository eventRepository,
                   PersonNeo4jRepository personRepository) {

        this.eventRepository = eventRepository;
        this.personRepository = personRepository;
    }

    public Event of(UUID target) {
        return eventRepository.eventWithId(target);
    }

    public Person person(UUID target) {
        return personRepository.personWithId(target);
    }

    public Set<Person> followedBy(Person origin) {
        return personRepository.personsFollowedBy(origin);
    }
}
