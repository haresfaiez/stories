package stories.event;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Event {
    private String   title;
    private DateTime time;
    private String   location;
    private List     stream;

    public Event(String   title
               , DateTime time
               , String   location
               , List     stream) {
        this.title    = title;
        this.time     = time;
        this.location = location;
        this.stream   = stream;
    }

    public List stream() {
        return unmodifiableList(stream);
    }

    public void update(Update input) {
        stream.add(input);
    }

    @Override
    public boolean equals(Object o) {
        Event other = (Event) o;
        return time.equals(other.time)
                && location.equals(other.location);
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }

    public static Event entitled(String   title
                               , DateTime time
                               , String   location) {
        return new Event(title, time, location, new ArrayList<>());
    }
}
