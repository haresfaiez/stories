package persistence.cassandra;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import stories.event.Attendees;
import stories.event.BuildEvent;
import stories.event.Event;
import stories.event.Updates;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

import static org.junit.Assert.assertTrue;


public class CassandraDriverTest {
    final String fixturePath        = "cassandra/events.cql";
    final UUID anEventIdFromFixture = UUID.fromString("32b0a8e0-0a3d-11e6-8cf0-2d237e461979");

    CassandraDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = CassandraDriver.embedded();
        driver.setUp();
        driver.install(fixturePath);
    }

    @Test
    @Ignore
    public void fixtureInstalledSuccessfully() {
        assertTrue(driver.hasInstalled(anEventFromTheFixture()));
    }

    @After
    public void tearDown() {
        driver.tearDown();
    }

    private Event anEventFromTheFixture() {
        return BuildEvent.identified(anEventIdFromFixture)
                         .at(LocalDateTime.of(2016, Month.APRIL, 24, 20, 30))
                         .entitled("An event from the fixture")
                         .attendedBy(Attendees.none())
                         .withUpdates(Updates.none())
                         .product();
    }
}
