package cli;

import persistence.CassandraEventRepository;
import stories.event.Event;

import java.util.UUID;

public class EventDetails {
    private CassandraEventRepository repository;

    public EventDetails(CassandraEventRepository repository) {
        this.repository = repository;
    }

    public Event of(UUID target) {
        return repository.eventWithId(target);
    }
}
