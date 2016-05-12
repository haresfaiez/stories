package cli;

import persistence.cassandra.CassandraEventRepository;
import stories.event.Event;
import stories.person.Person;

import java.util.UUID;

public class Service {
    private CassandraEventRepository repository;

    public Service(CassandraEventRepository repository) {
        this.repository = repository;
    }

    public Event of(UUID target) {
        return repository.eventWithId(target);
    }

    public Person person(UUID target) {
        return new Person(target, "");
    }
}
