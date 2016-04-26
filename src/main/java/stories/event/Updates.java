package stories.event;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Updates implements Serializable {
    private final Set<EventUpdate> elements;

    private Updates(Set<EventUpdate> elements) {
        this.elements = elements;
    }

    protected Updates with(EventUpdate newUpdate) {
        Set<EventUpdate> resultElements = new HashSet<>(elements);
        resultElements.add(newUpdate);
        return from(resultElements);
    }

    protected Boolean includes(EventUpdate potential) {
        return elements.contains(potential);
    }

    public Boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (null == o)               return Boolean.FALSE;
        if (!(o instanceof Updates)) return Boolean.FALSE;
        Updates other = (Updates) o;
        return other.are(elements);
    }

    private Boolean are(Set other) {
        return elements.equals(other);
    }

    @Override
    public int hashCode() {
        return elements.hashCode();
    }

    @Override
    public String toString() {
        return elements.toString();
    }

    public static Updates none() {
        return from(Collections.emptySet());
    }

    protected static Updates from(Set<EventUpdate> collection) {
        return new Updates(collection);
    }

    protected static Updates singleton(EventUpdate element) {
        HashSet<EventUpdate> elements = new HashSet<>();
        elements.add(element);
        return from(elements);
    }
}
