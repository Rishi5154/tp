package seedu.address.model.consultation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalConsults.GROUP;
import static seedu.address.testutil.TypicalConsults.PERSONAL;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.consultation.exceptions.ConsultationNotFoundException;
import seedu.address.model.consultation.exceptions.DuplicateConsultationException;
import seedu.address.testutil.ConsultationBuilder;


public class UniqueConsultationListTest {
    private final UniqueConsultationList uniqueConsultList = new UniqueConsultationList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueConsultList.contains(null));
    }

    @Test
    public void contains_consultNotInList_returnsFalse() {
        assertFalse(uniqueConsultList.contains(PERSONAL));
    }

    @Test
    public void contains_consultInList_returnsTrue() {
        uniqueConsultList.add(PERSONAL);
        assertTrue(uniqueConsultList.contains(PERSONAL));
    }

    @Test
    public void contains_consultWithSameIdentityFieldsInList_returnsTrue() {
        uniqueConsultList.add(PERSONAL);
        Consultation editedConsult = new ConsultationBuilder(PERSONAL).withType(VALID_TYPE_BOB)
                .build();
        assertTrue(uniqueConsultList.contains(editedConsult));
    }

    @Test
    public void add_nullConsult_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueConsultList.add(null));
    }

    @Test
    public void add_duplicateConsult_throwsDuplicateConsultationException() {
        uniqueConsultList.add(PERSONAL);
        assertThrows(DuplicateConsultationException.class, () -> uniqueConsultList.add(PERSONAL));
    }

    @Test
    public void setConsult_nullConsultation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueConsultList.setConsultation(null, PERSONAL));
    }

    @Test
    public void setConsult_nullEditedConsult_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueConsultList
                .setConsultation(PERSONAL, null));
    }

    @Test
    public void setConsult_targetConsultNotInList_throwsConsultationNotFoundException() {
        assertThrows(ConsultationNotFoundException.class, () -> uniqueConsultList.setConsultation(PERSONAL, PERSONAL));
    }

    @Test
    public void setConsult_editedConsultIsSameConsult_success() {
        uniqueConsultList.add(PERSONAL);
        uniqueConsultList.setConsultation(PERSONAL, PERSONAL);
        UniqueConsultationList uniqueConsultationList = new UniqueConsultationList();
        uniqueConsultationList.add(PERSONAL);
        assertEquals(uniqueConsultationList, uniqueConsultList);
    }

    @Test
    public void setConsult_editedConsultHasSameIdentity_success() {
        uniqueConsultList.add(PERSONAL);
        Consultation editedConsult = new ConsultationBuilder(PERSONAL).withType(VALID_TYPE_BOB)
                .build();
        uniqueConsultList.setConsultation(PERSONAL, editedConsult);
        UniqueConsultationList expectedUniqueConsultationList = new UniqueConsultationList();
        expectedUniqueConsultationList.add(editedConsult);
        assertEquals(expectedUniqueConsultationList, uniqueConsultList);
    }

    @Test
    public void setConsult_editedConsultHasDifferentIdentity_success() {
        uniqueConsultList.add(PERSONAL);
        uniqueConsultList.setConsultation(PERSONAL, GROUP);
        UniqueConsultationList expectedUniquePersonList = new UniqueConsultationList();
        expectedUniquePersonList.add(GROUP);
        assertEquals(expectedUniquePersonList, uniqueConsultList);
    }

    @Test
    public void setConsult_editedConsultHasNonUniqueIdentity_throwsDuplicateConsultationException() {
        uniqueConsultList.add(PERSONAL);
        uniqueConsultList.add(GROUP);
        assertThrows(DuplicateConsultationException.class, () -> uniqueConsultList.setConsultation(PERSONAL, GROUP));
    }

    @Test
    public void remove_nullConsult_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueConsultList.remove(null));
    }

    @Test
    public void remove_consultDoesNotExist_throwsConsultationNotFoundException() {
        assertThrows(ConsultationNotFoundException.class, () -> uniqueConsultList.remove(PERSONAL));
    }

    @Test
    public void remove_existingConsult_removesConsult() {
        uniqueConsultList.add(PERSONAL);
        uniqueConsultList.remove(PERSONAL);
        UniqueConsultationList expectedUniqueConsultationList = new UniqueConsultationList();
        assertEquals(expectedUniqueConsultationList, uniqueConsultList);
    }

    @Test
    public void setConsults_nullUniqueConsultationList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueConsultList
                .setConsultations((UniqueConsultationList) null));
    }

    @Test
    public void setConsults_uniqueConsultationList_replacesOwnListWithProvidedUniqueConsultationList() {
        uniqueConsultList.add(PERSONAL);
        UniqueConsultationList expectedUniqueConsultationList = new UniqueConsultationList();
        expectedUniqueConsultationList.add(PERSONAL);
        uniqueConsultList.setConsultations(expectedUniqueConsultationList);
        assertEquals(expectedUniqueConsultationList, uniqueConsultList);
    }

    @Test
    public void setConsultations_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueConsultList.setConsultations((List<Consultation>) null));
    }

    @Test
    public void setConsultations_list_replacesOwnListWithProvidedList() {
        uniqueConsultList.add(PERSONAL);
        List<Consultation> consultationList = Collections.singletonList(GROUP);
        uniqueConsultList.setConsultations(consultationList);
        UniqueConsultationList expectedUniqueConsultationList = new UniqueConsultationList();
        expectedUniqueConsultationList.add(GROUP);
        assertEquals(expectedUniqueConsultationList, uniqueConsultList);
    }

    @Test
    public void setConsultations_listWithDuplicateConsultations_throwsDuplicateConsultationException() {
        List<Consultation> listWithDuplicateConsultations = Arrays.asList(PERSONAL, PERSONAL);
        assertThrows(DuplicateConsultationException.class, () -> uniqueConsultList
                .setConsultations(listWithDuplicateConsultations));
    }

}
