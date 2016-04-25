package cli;

import stories.event.Event;
import stories.event.EventRepository;

import java.util.UUID;

public class EventDetails {
    private EventRepository repository;

    public EventDetails(EventRepository repository) {
        this.repository = repository;
    }

    public Event of(UUID targetId) {
        return repository.eventWithId(targetId);
    }
}
