package stories.stream;

import stories.person.Person;

import java.util.Collections;
import java.util.Set;

public class PersonStream {
    protected Person director;
    protected Set    updates;

    protected PersonStream(Person director,
                           Set updates) {
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
        return other.updates.equals(updates)
                && other.director.equals(director);
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
        return new PersonStream(director, Collections.emptySet());
    }
}
