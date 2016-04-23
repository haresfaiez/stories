package stories.event;

import java.time.LocalDateTime;

public class AttendeeUpdate {
    private final LocalDateTime time;
    private final String        message;

    private AttendeeUpdate(LocalDateTime time,
                           String message) {
        this.time    = time;
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o)                    return Boolean.FALSE;
        if (!(o instanceof AttendeeUpdate)) return Boolean.FALSE;
        AttendeeUpdate other = (AttendeeUpdate) o;
        return other.hasTime(time) && other.hasMessage(message);
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s, %s", time, message);
    }

    public static AttendeeUpdate at(LocalDateTime time,
                                    String message) {
        return new AttendeeUpdate(time, message);
    }

    private Boolean hasTime(LocalDateTime otherTime) {
        return time.equals(otherTime);
    }

    private Boolean hasMessage(String otherMessage) {
        return message.equals(otherMessage);
    }
}
