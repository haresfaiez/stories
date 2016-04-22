package stories.event;

import java.time.LocalDateTime;

public class PersonUpdate {
    private final LocalDateTime time;
    private final String        message;

    private PersonUpdate(LocalDateTime time,
                         String message) {
        this.time    = time;
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o)                    return Boolean.FALSE;
        if (!(o instanceof PersonUpdate)) return Boolean.FALSE;
        PersonUpdate other = (PersonUpdate) o;
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

    public static PersonUpdate at(LocalDateTime time,
                                  String message) {
        return new PersonUpdate(time, message);
    }

    private Boolean hasTime(LocalDateTime otherTime) {
        return time.equals(otherTime);
    }

    private Boolean hasMessage(String otherMessage) {
        return message.equals(otherMessage);
    }
}
