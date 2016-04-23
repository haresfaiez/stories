package stories.event;

import stories.person.Attendee;

class EventUpdate {
    protected final Attendee editor;
    protected final AttendeeUpdate update;

    private EventUpdate(Attendee editor,
                        AttendeeUpdate update) {
        this.editor = editor;
        this.update = update;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o)                   return Boolean.FALSE;
        if (!(o instanceof EventUpdate)) return Boolean.FALSE;
        EventUpdate other = (EventUpdate) o;
        return other.hasEditor(editor) && other.hasUpdate(update);
    }

    @Override
    public int hashCode() {
        return update.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s, %s", editor, update);
    }

    private Boolean hasEditor(Attendee otherEditor) {
        return editor.equals(otherEditor);
    }

    private Boolean hasUpdate(AttendeeUpdate otherUpdate) {
        return update.equals(otherUpdate);
    }

    public static EventUpdate by(Attendee editor,
                                 AttendeeUpdate update) {
        return new EventUpdate(editor, update);
    }
}
