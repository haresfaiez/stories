package stories.event;

import java.time.LocalDateTime;

public class EventSpecification {
    protected String        title;
    protected LocalDateTime time;

    public EventSpecification(LocalDateTime time,
                              String title) {
        this.time  = time;
        this.title = title;
    }

    public static EventSpecification at(LocalDateTime time,
                                        String title) {
        return new EventSpecification(time, title);
    }

}
