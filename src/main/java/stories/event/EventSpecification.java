package stories.event;

import java.time.LocalDateTime;

public class EventSpecification {
    protected String        title;
    protected LocalDateTime time;

    private EventSpecification(String title,
                               LocalDateTime time) {
        this.title = title;
        this.time  = time;
    }

    protected Boolean hasTitle(String otherTitle) {
        return title.equals(otherTitle);
    }

    protected Boolean isAt(LocalDateTime otherTime) {
        return time.equals(otherTime);
    }

    public static EventSpecification with(String title,
                                          LocalDateTime time) {
        return new EventSpecification(title, time);
    }
}
