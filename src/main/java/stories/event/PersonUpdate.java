package stories.event;

import java.time.LocalDateTime;

public class PersonUpdate {
    protected final LocalDateTime time;
    protected final String        message;

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
        return other.time.equals(time) && other.message.equals(message);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static PersonUpdate at(LocalDateTime time,
                                  String message) {
        return new PersonUpdate(time, message);
    }
}
