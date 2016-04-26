package stories.person;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class PersonTest {

    @Test
    public void equality() {
        assertEquals(new Person(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461979"), "Bill"),
                     new Person(UUID.fromString("b0a8e0-0a3d-11e6-8cf0-2d237e461979"), "Bill"));
    }

}
