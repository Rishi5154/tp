package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Tasker;
import seedu.address.model.consultation.Consultation;



/**
 * A utility class containing a list of {@code Consultation} objects to be used in tests.
 */
public class TypicalConsults {

    public static final Consultation PERSONAL = new ConsultationBuilder().withName("Alice Pauline")
            .withDay("10/10/2020").withTime("18:00").withLocation("Tembusu College").withType("personal").build();
    public static final Consultation GROUP = new ConsultationBuilder().withName("Benson Meier")
            .withDay("12/10/2020").withTime("10:00").withLocation("Kent Ridge Drive").withType("group").build();

    // Manually added - Consultation's details found in {@code CommandTestUtil}
    public static final Consultation PERSONAL_CONSULT = new ConsultationBuilder().withName(VALID_NAME_AMY)
            .withDay(VALID_DAY_AMY).withTime(VALID_TIME_AMY).withLocation(VALID_LOCATION_AMY)
            .withType(VALID_TYPE_AMY).build();
    public static final Consultation GROUP_CONSULT = new ConsultationBuilder().withName(VALID_NAME_BOB)
            .withDay(VALID_DAY_BOB).withTime(VALID_TIME_BOB).withLocation(VALID_LOCATION_BOB)
            .withType(VALID_TYPE_BOB).build();

    private TypicalConsults() {
    } // prevents instantiation

    /**
     * Returns an {@code Tasker} with all the typical consultations.
     */
    public static Tasker getTypicalAddressBook() {
        Tasker ab = new Tasker();
        for (Consultation consultation : getTypicalPersons()) {
            ab.addConsultation(consultation);
        }
        return ab;
    }

    public static List<Consultation> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(PERSONAL, GROUP));
    }
}
