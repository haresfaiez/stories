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
        return other.hasEditor(editor)
                && other.isAt(time)
                && other.isFor(event);
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

    private boolean isFor(Event otherEvent) {
        return event.equals(otherEvent);
    }

    private boolean isAt(LocalDateTime otherTime) {
        return time.equals(otherTime);
    }

    private Boolean hasEditor(Attendee otherEditor) {
        return editor.equals(otherEditor);
    }
}
