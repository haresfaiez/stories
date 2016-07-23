package stories.event;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Event {
    private String title;
    private List   stream;

    public Event(String title
               , List   stream) {
        this.title  = title;
        this.stream = stream;
    }

    public List stream() {
        return unmodifiableList(stream);
    }

    public void update(Participant emma
                     , String      message) {
        stream.add(Update.from(emma, message));
    }

    public static Event entitled(String title) {
        return new Event(title, new ArrayList<>());
    }
}
