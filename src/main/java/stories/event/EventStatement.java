package stories.event;

import java.time.LocalDateTime;

class EventStatement {
    protected String        title;
    protected LocalDateTime time;

    protected EventStatement(LocalDateTime time,
                          String title) {
        this.time  = time;
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", title, time);
    }

    public static EventStatement at(LocalDateTime time,
                                    String title) {
        return new EventStatement(time, title);
    }
}
