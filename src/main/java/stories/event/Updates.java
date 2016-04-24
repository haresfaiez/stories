package stories.event;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

public class Updates {
    private final Set collection;

    private Updates(Set collection) {
        this.collection = collection;
    }

    protected Updates with(EventUpdate update) {
        Set resultCollection = new HashSet(collection);
        resultCollection.add(update);
        return from(resultCollection);
    }

    protected Boolean contains(EventUpdate update) {
        return collection.contains(update);
    }

    public Boolean isEmpty() {
        return collection.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (null == o)               return Boolean.FALSE;
        if (!(o instanceof Updates)) return Boolean.FALSE;
        Updates other = (Updates) o;
        return other.hasCollection(collection);
    }

    private Boolean hasCollection(Set otherCollection) {
        return collection.equals(otherCollection);
    }

    @Override
    public int hashCode() {
        return collection.hashCode();
    }

    @Override
    public String toString() {
        return collection.toString();
    }

    public static Updates noOne() {
        return from(Collections.emptySet());
    }

    protected static Updates from(Set collection) {
        return new Updates(collection);
    }

    static Updates singleton(EventUpdate update) {
        return from(new HashSet<>(asList(update)));
    }
}
