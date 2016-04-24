package stories.event;

import java.io.Serializable;
import java.time.LocalDateTime;

class EventStatement implements Serializable{
    protected final String        title;
    protected final LocalDateTime time;

    protected EventStatement(LocalDateTime time,
                             String        title) {
        this.time  = time;
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o)                      return Boolean.FALSE;
        if (!(o instanceof EventStatement)) return Boolean.FALSE;
        EventStatement other = (EventStatement) o;
        return other.isAt(time)
                && other.isEntitled(title);
    }

    private Boolean isEntitled(String otherTitle) {
        return title.equals(otherTitle);
    }

    private Boolean isAt(LocalDateTime otherTime) {
        return time.equals(otherTime);
    }

    protected EventStatement withTime(LocalDateTime time) {
        return new EventStatement(time, title);
    }

    protected EventStatement withTitle(String title) {
        return new EventStatement(time, title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s, %s", title, time);
    }

    public static EventStatement at(LocalDateTime time,
                                    String        title) {
        return new EventStatement(time, title);
    }

    protected static EventStatement _identityElement() {
        return at(LocalDateTime.MIN, "");
    }
}
