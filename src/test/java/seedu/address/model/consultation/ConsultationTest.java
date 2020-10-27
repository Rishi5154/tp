package seedu.address.model.consultation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BOB;
import static seedu.address.testutil.TypicalConsults.GROUP;
import static seedu.address.testutil.TypicalConsults.PERSONAL;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ConsultationBuilder;

public class ConsultationTest {
    @Test
    public void isSameConsultation() {
        // same object -> returns true
        assertTrue(PERSONAL.isSameConsultation(PERSONAL));

        // null -> returns false
        assertFalse(PERSONAL.isSameConsultation(null));

        // different time and date -> returns false
        Consultation editedConsult = new ConsultationBuilder(PERSONAL).withDay(VALID_ATTENDANCE_BOB)
                .withTime(VALID_TIME_BOB).build();
        assertFalse(PERSONAL.isSameConsultation(editedConsult));

        // different name -> returns false
        editedConsult = new ConsultationBuilder(PERSONAL).withName(VALID_NAME_BOB).build();
        assertFalse(PERSONAL.isSameConsultation(editedConsult));

        // same name, same time, same day, different attributes -> returns true
        editedConsult = new ConsultationBuilder(PERSONAL).withLocation(VALID_LOCATION_BOB)
                .withType(VALID_TYPE_BOB).build();
        assertTrue(PERSONAL.isSameConsultation(editedConsult));

    }

    @Test
    public void equals() {
        // same values -> returns true
        Consultation personalCopy = new ConsultationBuilder(PERSONAL).build();
        assertTrue(PERSONAL.equals(personalCopy));

        // same object -> returns true
        assertTrue(PERSONAL.equals(PERSONAL));

        // null -> returns false
        assertFalse(PERSONAL.equals(null));

        // different type -> returns false
        assertFalse(PERSONAL.equals(5));

        // different consultation -> returns false
        assertFalse(PERSONAL.equals(GROUP));

        // different name -> returns false
        Consultation editedConsult = new ConsultationBuilder(PERSONAL).withName(VALID_NAME_BOB).build();
        assertFalse(PERSONAL.equals(editedConsult));

        // different day -> returns false
        editedConsult = new ConsultationBuilder(PERSONAL).withDay(VALID_ATTENDANCE_BOB).build();
        assertFalse(PERSONAL.equals(editedConsult));

        // different time -> returns false
        editedConsult = new ConsultationBuilder(PERSONAL).withTime(VALID_TIME_BOB).build();
        assertFalse(PERSONAL.equals(editedConsult));

        // different location -> returns false
        editedConsult = new ConsultationBuilder(PERSONAL).withLocation(VALID_LOCATION_BOB).build();
        assertFalse(PERSONAL.equals(editedConsult));
    }
}
