package stories.event;

import java.util.*;

class Updates {
    private final Set collection;

    private Updates(Set collection) {
        this.collection = collection;
    }

    protected Updates with(EventUpdate update) {
        Set resultCollection = new HashSet(collection);
        resultCollection.add(update);
        return new Updates(resultCollection);
    }

    protected Boolean contains(EventUpdate update) {
        return collection.contains(update);
    }

    public static Updates noOne() {
        return new Updates(Collections.emptySet());
    }
}
