package seedu.address.model.consultation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.consultation.exceptions.ConsultationNotFoundException;
import seedu.address.model.consultation.exceptions.DuplicateConsultationException;


/**
 * A list of consultations that enforces uniqueness between its elements and does not allow nulls.
 * A consultation is considered unique by comparing using {@code Consultation#isSameConsultation(Consultation)}.
 * As such, adding and updating of consultations uses Consultation#isSameConsultation(Consultation) for equality so as
 * to ensure that the consultation being added or updated is unique in terms of identity in the UniqueConsultationList.
 * However, the removal of a consultation uses Consultation#equals(Object) so
 * as to ensure that the consultation with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Consultation#isSameConsultation(Consultation)
 */
public class UniqueConsultationList implements Iterable<Consultation> {
    private final ObservableList<Consultation> internalList = FXCollections.observableArrayList();
    private final ObservableList<Consultation> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent consultation as the given argument.
     */
    public boolean contains(Consultation toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameConsultation);
    }

    /**
     * Adds a consultation to the list.
     * The consultation must not already exist in the list.
     */
    public void add(Consultation toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateConsultationException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the consultation {@code target} in the list with {@code editedConsultation}.
     * {@code target} must exist in the list.
     * The consultation identity of {@code editedConsultation} must not be the same as
     * another existing consultation in the list.
     */
    public void setConsultation(Consultation target, Consultation editedConsultation) {
        requireAllNonNull(target, editedConsultation);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ConsultationNotFoundException();
        }

        if (!target.isSameConsultation(editedConsultation) && contains(editedConsultation)) {
            throw new DuplicateConsultationException();
        }

        internalList.set(index, editedConsultation);
    }

    public void setConsultations(UniqueConsultationList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setConsultations(List<Consultation> consultations) {
        requireAllNonNull(consultations);
        if (!consultsAreUnique(consultations)) {
            throw new DuplicateConsultationException();
        }

        internalList.setAll(consultations);
    }

    /**
     * Removes the equivalent consultation from the list.
     * The consultation must exist in the list.
     */
    public void remove(Consultation toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ConsultationNotFoundException();
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Consultation> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Consultation> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueConsultationList // instanceof handles nulls
                && internalList.equals(((UniqueConsultationList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code consultations} contains only unique consultations.
     */
    private boolean consultsAreUnique(List<Consultation> consultations) {
        for (int i = 0; i < consultations.size() - 1; i++) {
            for (int j = i + 1; j < consultations.size(); j++) {
                if (consultations.get(i).isSameConsultation(consultations.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
