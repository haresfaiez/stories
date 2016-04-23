package stories.event;

import stories.person.Attendee;

import java.time.LocalDateTime;

class EventUpdate {
    protected final Attendee       editor;
    protected final Event          event;
    protected final LocalDateTime  time;
    protected final AttendeeUpdate update;

    private EventUpdate(Attendee editor,
                        AttendeeUpdate update,
                        LocalDateTime time) {
        this.editor = editor;
        this.update = update;
        event = null;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o)                   return Boolean.FALSE;
        if (!(o instanceof EventUpdate)) return Boolean.FALSE;
        EventUpdate other = (EventUpdate) o;
        return other.hasEditor(editor)
                && other.hasUpdate(update)
                && other.hasTime(time);
    }

    private boolean hasTime(LocalDateTime otherTime) {
        return time.equals(otherTime);
    }

    @Override
    public int hashCode() {
        return update.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s",
                             editor,
                             event,
                             time,
                             update);
    }

    private Boolean hasEditor(Attendee otherEditor) {
        return editor.equals(otherEditor);
    }

    private Boolean hasUpdate(AttendeeUpdate otherUpdate) {
        return update.equals(otherUpdate);
    }

    public static EventUpdate by(Attendee editor,
                                 Event concert,
                                 LocalDateTime time,
                                 AttendeeUpdate update) {
        return new EventUpdate(editor, update, time);
    }

    public static EventUpdate by(Attendee editor,
                                 LocalDateTime time,
                                 AttendeeUpdate update) {
        return new EventUpdate(editor, update, time);
    }
}
