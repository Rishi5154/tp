package seedu.address.model.consultation;

public enum ConsultationType {
    PERSONAL {
        @Override
        public String toString() {
            return "Personal";
        }
    },
    GROUP {
        @Override
        public String toString() {
            return "Group";
        }
    };
}
