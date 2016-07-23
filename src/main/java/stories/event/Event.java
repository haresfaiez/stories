package stories.event;

import java.util.List;

import static java.util.Arrays.asList;

public class Event {
    private String title;
    private Update result;

    public Event(String title) {
        this.title = title;
    }

    public List stream() {
        return asList(result);
    }

    public static Event entitled(String title) {
        return new Event(title);
    }

    public void update(Participant emma, String message) {
        result = Update.from(emma, message);
    }
}
