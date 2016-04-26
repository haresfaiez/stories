package cli;

import stories.event.Event;
import stories.event.CassandraEventRepository;

import java.util.UUID;

public class EventDetails {
    private CassandraEventRepository repository;

    public EventDetails(CassandraEventRepository repository) {
        this.repository = repository;
    }

    public Event of(UUID targetId) {
        return repository.eventWithId(targetId);
    }
}
