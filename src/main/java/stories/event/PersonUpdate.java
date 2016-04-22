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

    public static PersonUpdate at(LocalDateTime time,
                                  String message) {
        return new PersonUpdate(time, message);
    }
}
