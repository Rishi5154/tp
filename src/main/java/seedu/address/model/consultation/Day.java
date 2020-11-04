package seedu.address.model.consultation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


/**
 * Represents a Consultation's date in classes.
 */
public class Day implements Comparable<Day> {
    public static final String MESSAGE_CONSTRAINTS = "Accepted date format: dd/MM/yyyy (e.g. 27/03/1998)";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public final Date date;

    /**
     * Users should only use fromDateString to safely generate the day of consultation
     * @param date Date.
     */
    private Day(Date date) {
        assert date != null;
        this.date = date;
    }

    /**
     * Creates a new {@code Day} using a date string that will be parsed.
     *
     * @param input date string in dd/MM/yyyy.
     * @return {@code Day} that corresponds to the input date.
     */
    public static final Day fromDateString(String input) {
        requireNonNull(input);
        Date date;
        try {
            date = DATE_FORMAT.parse(input);
        } catch (ParseException e) {
            checkArgument(false, MESSAGE_CONSTRAINTS);
            return null; // Never triggers as the above will throw an invalid argument exception
        }
        return new Day(date);
    }

    /**
     * Returns true if a given string is a valid name.
     *
     * @param test Input date.
     * @return Presence of valid day.
     */
    public static boolean isValidDay(String test) {
        try {
            Date date = DATE_FORMAT.parse(test);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns string representation of the date.
     *
     * @return date in string.
     */
    @Override
    public String toString() {
        return DATE_FORMAT.format(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Day that = (Day) o;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    @Override
    public int compareTo(Day o) {
        assert(this.date != null);
        return this.date.compareTo(o.date);
    }
}
