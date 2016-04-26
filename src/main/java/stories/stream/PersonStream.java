package stories.stream;

import stories.event.Updates;
import stories.person.Person;

public class PersonStream {
    private Person  director;
    private Updates updates;

    private PersonStream(Person  director,
                         Updates updates) {
        this.director = director;
        this.updates  = updates;
    }

    protected Boolean isEmpty() {
        return updates.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (null == o)                    return Boolean.FALSE;
        if (!(o instanceof PersonStream)) return Boolean.FALSE;
        PersonStream other = (PersonStream) o;
        return other.hasUpdates(updates)
                && other.isDirectedBy(director);
    }

    private boolean isDirectedBy(Person other) {
        return director.equals(other);
    }

    private boolean hasUpdates(Updates other) {
        return updates.equals(other);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static PersonStream empty(Person director) {
        return of(director, Updates.none());
    }

    public static PersonStream of(Person  director,
                                  Updates updates) {
        return new PersonStream(director, updates);
    }
}
