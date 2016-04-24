package stories.event;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UpdatesEqualityTest {

    @Test
    public void withEqualCollections() {
        Set aSet       = new HashSet();
        Set theSameSet = new HashSet();

        assertEquals(Updates.from(aSet),
                Updates.from(theSameSet));
    }

    @Test
    public void towardNullAndObject() {
        assertNotEquals(Updates.none(), null);
        assertNotEquals(Updates.none(), new Object());
    }
}
