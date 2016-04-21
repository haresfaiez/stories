package stories.event;

import java.time.LocalDateTime;

public class Event {
    private Long id;
    private String title;
    private LocalDateTime time;

    public Event(Long id, String title, LocalDateTime time) {
        this.id = id;
        this.title = title;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o)             return Boolean.FALSE;
        if (!(o instanceof Event)) return Boolean.FALSE;
        Event other = (Event) o;
        return other.hasId(id);
    }

    private Boolean hasId(Long otherId) {
        return id.equals(otherId);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        throw new RuntimeException("toString not implemented on Event");
    }

    public LocalDateTime time() {
        return time;
    }

    public String title() {
        return title;
    }
}
