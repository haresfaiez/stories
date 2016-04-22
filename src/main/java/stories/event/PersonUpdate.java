package stories.event;

import java.time.LocalDateTime;

public class PersonUpdate {
    protected LocalDateTime time;
    protected String        message;

    public PersonUpdate(LocalDateTime time,
                        String message) {
        this.time    = time;
        this.message = message;
    }
}
