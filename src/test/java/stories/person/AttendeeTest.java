package stories.person;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AttendeeTest {

    @Test
    public void equality() {
        Attendee bill        = new Attendee( 1L, "Bill");
        Attendee sameBill    = new Attendee( 1L, "Bill");
        Attendee anotherBill = new Attendee(82L, "Bill");

        assertEquals   (sameBill,    bill);
        assertNotEquals(anotherBill, bill);
        assertNotEquals(bill,        null);
        assertNotEquals(bill,        new Object());
    }

    @Test
    public void hasAName() {
        Attendee bill = new Attendee(1L, "Bill");

        assertEquals("Bill", bill.name());
    }
}