package stories.person;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PersonTest {

    @Test
    public void equality() {
        Person bill        = new Person( 1L, "Bill");
        Person sameBill    = new Person( 1L, "Bill");
        Person anotherBill = new Person(82L, "Bill");

        assertEquals   (sameBill,    bill);
        assertNotEquals(anotherBill, bill);
        assertNotEquals(bill,        null);
        assertNotEquals(bill,        new Object());
    }

    @Test
    public void hasAName() {
        Person bill = new Person(1L, "Bill");

        assertEquals("Bill", bill.name());
    }
}