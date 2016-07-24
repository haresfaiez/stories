package stories.event;

import org.joda.time.DateTime;

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
                     , String      message
                     , DateTime    time) {
        stream.add(Update.from(emma, message, time));
    }

    public static Event entitled(String title) {
        return new Event(title, new ArrayList<>());
    }
}
