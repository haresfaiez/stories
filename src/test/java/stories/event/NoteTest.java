package stories.event;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class NoteTest {

    @Test
    public void equalityWithSameMessage() {
        assertEquals(Note.from("Some message"),
                     Note.from("Some message"));
    }

    @Test
    public void equalityWithDifferentMessage() {
        assertNotEquals(Note.from("Some message"),
                        Note.from("An other message"));
    }

    @Test
    public void equality() {
        assertNotEquals(Note.from("Some message"), null);
        assertNotEquals(Note.from("Some message"), new Object());
    }
}
