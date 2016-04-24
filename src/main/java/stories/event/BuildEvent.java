package stories.event;

import java.time.LocalDateTime;
import java.util.UUID;

public class BuildEvent {
    private Event product;

    private BuildEvent(UUID identity) {
        product = new Event(identity,
                            EventSpecification.identityElement(),
                            Updates.none());
    }

    public BuildEvent entitled(String title) {
        product.specification = product.specification.withTitle(title);
        return this;
    }

    public BuildEvent at(LocalDateTime newTime) {
        product.specification = product.specification.withTime(newTime);
        return this;
    }

    public BuildEvent withAttendees(Attendees attendees) {
        product.specification = product.specification.withAttendees(attendees);
        return this;
    }

    public BuildEvent withUpdates(Updates updates) {
        product.updates = updates;
        return this;
    }

    public Event product() {
        return product;
    }

    public static BuildEvent identifiedBy(UUID identity) {
        return new BuildEvent(identity);
    }
}
