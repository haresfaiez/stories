package stories.event;

import stories.person.Attendee;
import stories.person.Person;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class BuildEvent {
    private Event product;

    private BuildEvent(UUID identity) {
        product = new Event(identity,
                            EventSpecification.identity(),
                            Updates.none());
    }

    public BuildEvent entitled(String title) {
        product.specification = product.specification.entitled(title);
        return this;
    }

    public BuildEvent at(LocalDateTime time) {
        product.specification = product.specification.at(time);
        return this;
    }

    public BuildEvent attendedBy(Attendees attendees) {
        product.specification = product.specification.attendedBy(attendees);
        return this;
    }

    public BuildEvent withUpdates(Updates updates) {
        product.updates = updates;
        return this;
    }

    public Event product() {
        return product;
    }

    public static BuildEvent identified(UUID identity) {
        return new BuildEvent(identity);
    }

    public BuildEvent attendedBy(Set<Object> attendees) {
        attendedBy(new Attendees(attendees.stream()
                            .map(String::valueOf)
                            .map(UUID::fromString)
                            .map(uuid -> new Person(uuid, ""))
                            .map(Attendee::from)
                            .collect(Collectors.toSet())));
        return this;
    }

    public BuildEvent withUpdates(Set<Object> updates) {
        return this;
    }
}
