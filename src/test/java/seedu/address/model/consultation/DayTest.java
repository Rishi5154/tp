package seedu.address.model.consultation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class DayTest {
    @Test
    public void fromDateString() {
        // null attendance
        assertThrows(NullPointerException.class, () -> Day.fromDateString(null));

        // invalid attendances
        assertThrows(IllegalArgumentException.class, () -> Day.fromDateString(""));
        assertThrows(IllegalArgumentException.class, () -> Day.fromDateString("hello"));

        // valid attendances
        assertEquals(Day.fromDateString("13/01/2020").toString(), "13/01/2020");
    }
}
