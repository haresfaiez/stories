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

    public LocalDateTime time() {
        return time;
    }

    public String title() {
        return title;
    }

    public long id() {
        return id;
    }
}
