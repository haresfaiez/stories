package stories.event.cassandra;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stories.event.*;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertTrue;


public class CassandraDriverTest {
    final String fixturePath        = "cassandra/events.cql";
    final Long anEventIdFromFixture = 1L;

    CassandraDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = CassandraDriver.embedded();
        driver.setUp();
        driver.install(fixturePath);
    }

    @Test
    public void fixtureInstalledSuccessfully() {
        assertTrue(driver.hasInstalled(anEventFromTheFixture()));
    }

    @After
    public void tearDown() {
        driver.tearDown();
    }

    private Event anEventFromTheFixture() {
        return BuildEvent.identifiedBy(anEventIdFromFixture)
                         .at(LocalDateTime.of(2016, Month.APRIL, 24, 20, 30))
                         .entitled("An event from the fixture")
                         .withAttendees(Attendees.none())
                         .withUpdates(Updates.none())
                         .product();
    }
}
