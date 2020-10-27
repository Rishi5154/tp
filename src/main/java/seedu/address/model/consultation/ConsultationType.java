package seedu.address.model.consultation;

public enum ConsultationType {
    PERSONAL {
        @Override
        public String toString() {
            return "personal";
        }
    },
    GROUP {
        @Override
        public String toString() {
            return "group";
        }
    };
}
