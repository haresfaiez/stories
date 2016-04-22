package stories.event;

import stories.person.Person;

public class EventUpdate {
    protected final Person editor;
    protected final PersonUpdate update;

    public EventUpdate(Person editor, PersonUpdate update) {
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

    private Boolean hasEditor(Person otherEditor) {
        return editor.equals(otherEditor);
    }

    private Boolean hasUpdate(PersonUpdate otherUpdate) {
        return update.equals(otherUpdate);
    }
}
