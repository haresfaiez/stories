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
    Long            targetEventId = 1L;
    EventRepository repository;
    List<Event>     events;
    Integer         targetEventIndex;

    @Before
    public void setUp() throws Exception {
        JavaSparkContext context = localContext();
        targetEventIndex = 0;
        events = Arrays.asList(eventWithId(targetEventId),
                               eventWithId(2L),
                               eventWithId(3L),
                               eventWithId(4L));
        JavaRDD<Event> eventsRDD = context.parallelize(events);
        repository = new EventRepository(eventsRDD);
    }

    @Test
    public void retrieveEventById() {
        assertEquals(theEvent(),
                     repository.eventWithId(targetEventId));
    }

    private JavaSparkContext localContext() {
        SparkConf sparkConfiguration = new SparkConf();
        sparkConfiguration.setAppName("Event repository test");
        sparkConfiguration.setMaster("local");
        return new JavaSparkContext(sparkConfiguration);
    }

    private Event theEvent() {
        return events.get(targetEventIndex);
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
