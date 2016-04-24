package stories.event;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EventRepositoryTest {
    Long theEventId;
    EventRepository repository;

    @Before
    public void setUp() throws Exception {
        theEventId = 1L;
        SparkConf sparkConfiguration = new SparkConf();
        sparkConfiguration.setAppName("Java API demo");
        sparkConfiguration.setMaster("local");

        JavaSparkContext context = new JavaSparkContext(sparkConfiguration);
        List<Event> eventsSet = Arrays.asList(
                eventWithId(2L),
                eventWithId(3L),
                eventWithId(1L),
                eventWithId(4L));
        JavaRDD<Event> events = context.parallelize(eventsSet);
        repository = new EventRepository(events);
    }

    @Test
    public void retrieveEventById() {
        assertEquals(theEvent(),
                     repository.eventWithId(theEventId));
    }

    private Event theEvent() {
        return BuildEvent.identifiedBy(theEventId)
                .at(LocalDateTime.MAX)
                .entitled("The title")
                .withAttendees(Attendees.none())
                .withUpdates(Updates.none())
                .product();
    }

    private Event eventWithId(Long id) {
        return BuildEvent.identifiedBy(id)
                .at(LocalDateTime.MAX)
                .entitled("The title")
                .withAttendees(Attendees.none())
                .withUpdates(Updates.none())
                .product();
    }
}
