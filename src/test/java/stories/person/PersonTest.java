package stories.person;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonTest {

    @Test
    public void equality() {
        assertEquals(new Person(1L, "Bill"), new Person(1L, "Bill"));
    }

}
