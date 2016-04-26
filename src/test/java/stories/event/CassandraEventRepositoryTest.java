package stories.event;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import persistence.CassandraEventRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class CassandraEventRepositoryTest {
    UUID                     targetEventId = UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461979");
    CassandraEventRepository repository;
    List<Event>              events;
    Integer                  targetEventIndex;
    private JavaSparkContext context;

    @Before
    public void setUp() throws Exception {
        context = localContext();
        targetEventIndex = 0;
        events = Arrays.asList(eventWithId(targetEventId),
                               eventWithId(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e222279")),
                               eventWithId(UUID.fromString("22a8e0-0a3d-11e6-8cf0-2d237e222279")));
        repository = CassandraEventRepository.from(context, "stories", "event");
    }

    @Test
    @Ignore
    public void retrieveEventById() {
        JavaRDD<Event> eventsRDD = context.parallelize(events);
        assertEquals(theEvent(),
                     repository.eventFrom(eventsRDD, targetEventId));
    }

    private JavaSparkContext localContext() {
        SparkConf sparkConfiguration = new SparkConf();
        sparkConfiguration.setAppName("Event repository test");
        sparkConfiguration.setMaster("local");
        sparkConfiguration.set("spark.driver.allowMultipleContexts", "true");
        return new JavaSparkContext(sparkConfiguration);
    }

    private Event theEvent() {
        return events.get(targetEventIndex);
    }

    private Event eventWithId(UUID id) {
        return BuildEvent.identified(id)
                         .at(LocalDateTime.MAX)
                         .entitled("The title")
                         .attendedBy(Attendees.none())
                         .withUpdates(Updates.none())
                         .product();
    }
}
