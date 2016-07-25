package stories.event;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class EventBuilder {

    private Event product;

    public EventBuilder() {
        product = new Event("", DateTime.parse("0000-01-01"), "", new ArrayList<>());
    }

    public EventBuilder at(DateTime time) {
        product.time = time;
        return this;
    }

    public EventBuilder in(String location) {
        product.location = location;
        return this;
    }

    public EventBuilder withUpdates(List updates) {
        product.stream.addAll(updates);
        return this;
    }

    public Event event() {
        return product;
    }
}
