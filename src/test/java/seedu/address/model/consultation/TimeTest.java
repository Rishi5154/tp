package seedu.address.model.consultation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Time.fromTimeString(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> Time.fromTimeString(invalidTime));
    }

    @Test
    public void isValidTime() {
        // null name
        assertThrows(NullPointerException.class, () -> Time.isValidTime(null));

        // invalid time
        assertFalse(Time.isValidTime("")); // empty string
        assertFalse(Time.isValidTime(" ")); // spaces only
        assertFalse(Time.isValidTime("4 pm")); // incorrect format
        assertFalse(Time.isValidTime("1200")); // missing colon characters
        assertFalse(Time.isValidTime("1270")); // incorrect input

        // valid time
        assertTrue(Time.isValidTime("12:00"));
        assertTrue(Time.isValidTime("23:59"));
        assertTrue(Time.isValidTime("00:00"));
    }
}
