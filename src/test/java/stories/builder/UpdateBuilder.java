package stories.builder;

import stories.event.EventUpdate;
import stories.event.PersonUpdate;

import static java.time.LocalDateTime.now;
import static stories.builder.PersonBuilder.bill;

public class UpdateBuilder {
    public static PersonUpdate somePersonUpdate() {
       return PersonUpdate.at(now(), "Some message");
    }

    public static EventUpdate someEventUpdate() {
        return new EventUpdate(bill(1L), somePersonUpdate());
    }
}
