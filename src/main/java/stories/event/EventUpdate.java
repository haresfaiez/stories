package stories.event;

import stories.person.Attendee;

import java.time.LocalDateTime;

class EventUpdate {
    protected Attendee       editor;
    protected Event          event;
    protected LocalDateTime  time;
    protected AttendeeUpdate update;

    protected EventUpdate(Attendee       editor,
                          AttendeeUpdate update,
                          LocalDateTime  time,
                          Event          event) {
        this.editor = editor;
        this.update = update;
        this.event  = event;
        this.time   = time;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o)                   return Boolean.FALSE;
        if (!(o instanceof EventUpdate)) return Boolean.FALSE;
        EventUpdate other = (EventUpdate) o;
        return other.isEditedBy(editor)
                && other.isAt(time)
                && other.isIn(event);
    }

    @Override
    public int hashCode() {
        return editor.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", editor,
                                               event,
                                               time,
                                               update);
    }

    private boolean isIn(Event otherEvent) {
        return event.equals(otherEvent);
    }

    private boolean isAt(LocalDateTime otherTime) {
        return time.equals(otherTime);
    }

    private Boolean isEditedBy(Attendee otherEditor) {
        return editor.equals(otherEditor);
    }
}
