package stories.event;

import java.time.LocalDateTime;

class BuildEvent {
    private Event product;

    private BuildEvent(Long identity) {
        product = new Event(identity,
                            EventSpecification.identityElement(),
                            Updates.none());
    }

    protected BuildEvent entitled(String title) {
        product.specification = product.specification.withTitle(title);
        return this;
    }

    protected BuildEvent at(LocalDateTime newTime) {
        product.specification = product.specification.withTime(newTime);
        return this;
    }

    protected BuildEvent withAttendees(Attendees attendees) {
        product.specification = product.specification.withAttendees(attendees);
        return this;
    }

    protected BuildEvent withUpdates(Updates updates) {
        product.updates = updates;
        return this;
    }

    protected Event product() {
        return product;
    }

    protected static BuildEvent identifiedBy(Long identity) {
        return new BuildEvent(identity);
    }
}
