package stories.event;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

class Updates {
    private final Collection collection;

    private Updates(Collection collection) {
        this.collection = collection;
    }

    protected Updates with(EventUpdate update) {
        Collection resultCollection = new LinkedList(collection);
        resultCollection.add(update);
        return new Updates(resultCollection);
    }

    protected Boolean contains(EventUpdate update) {
        return collection.contains(update);
    }

    public static Updates noOne() {
        return new Updates(Collections.EMPTY_LIST);
    }
}
