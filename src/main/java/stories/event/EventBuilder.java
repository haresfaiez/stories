package stories.event;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

class EventBuilder {
    public static final DateTime NULL_TIME   = DateTime.parse("0000-01-01");
    public static final String NULL_TITLE    = "";
    public static final String NULL_LOCATION = "";
    public static final List NULL_STREAM     = new ArrayList<>();

    private Event product;

    protected EventBuilder() {
        product = new Event(NULL_TITLE
                          , NULL_TIME
                          , NULL_LOCATION
                          , NULL_STREAM);

    }

    protected EventBuilder at(DateTime time) {
        product.time = time;
        return this;
    }

    protected EventBuilder in(String location) {
        product.location = location;
        return this;
    }

    protected EventBuilder withUpdates(List updates) {
        product.stream = updates;
        return this;
    }

    protected Event event() {
        return product;
    }
}
