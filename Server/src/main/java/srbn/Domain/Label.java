package srbn.Domain;

public class Label {

        private String value;

        public Label(String value) {

            this.value = value;
        }

        public Label() {
            this.value = "";
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
}
