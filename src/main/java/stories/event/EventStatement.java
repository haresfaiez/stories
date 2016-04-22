package stories.event;

import java.time.LocalDateTime;

public class EventStatement {
    protected String        title;
    protected LocalDateTime time;

    public EventStatement(LocalDateTime time,
                          String title) {
        this.time  = time;
        this.title = title;
    }

    public static EventStatement at(LocalDateTime time,
                                    String title) {
        return new EventStatement(time, title);
    }

    @Override
    public String toString() {
        return String.format("%s, %s", title, time);
    }
}
