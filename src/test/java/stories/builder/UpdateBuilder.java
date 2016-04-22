package stories.builder;

import stories.event.EventUpdate;
import stories.event.PersonUpdate;

import java.time.LocalDateTime;
import java.time.Month;

import static stories.builder.PersonBuilder.bill;

public class UpdateBuilder {
    public static PersonUpdate somePersonUpdate() {
       return PersonUpdate.at(someTime(), "Some message");
    }

    public static EventUpdate someEventUpdate() {
        return new EventUpdate(bill(1L), somePersonUpdate());
    }

    public static LocalDateTime someTime() {
        return LocalDateTime.of(2015, Month.APRIL, 19, 20, 30);
    }
}
