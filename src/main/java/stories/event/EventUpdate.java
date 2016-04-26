package stories.event;

import stories.person.Attendee;

import java.time.LocalDateTime;

class EventUpdate {
    protected Attendee       editor;
    protected Event          target;
    protected LocalDateTime  time;
    protected Note           update;

    protected EventUpdate(Attendee       editor,
                          Note           note,
                          LocalDateTime  time,
                          Event          target) {
        this.editor  = editor;
        this.update  = note;
        this.time    = time;
        this.target  = target;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o)                   return Boolean.FALSE;
        if (!(o instanceof EventUpdate)) return Boolean.FALSE;
        EventUpdate other = (EventUpdate) o;
        return other.isBy(editor)
                && other.isAt(time)
                && other.isIn(target);
    }

    private boolean isIn(Event other) {
        return target.equals(other);
    }

    private boolean isAt(LocalDateTime other) {
        return time.equals(other);
    }

    private Boolean isBy(Attendee other) {
        return editor.equals(other);
    }

    @Override
    public int hashCode() {
        return editor.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", editor,
                                               target,
                                               time,
                                               update);
    }
}
