package stories.event;

import org.joda.time.DateTime;

public class DateTimeFactory {

    protected static DateTime aTime() {
        return new DateTime(2016, 7, 1, 21, 0);
    }
}
