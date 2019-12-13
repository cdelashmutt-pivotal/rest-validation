package io.pivotal.pa.restvalidation;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class DataObject {

    @NotNull
    @NotEmpty
    private String name;

    @Pattern(regexp = "^[0-9]{3}-[0-9]{2}-[0-9]{4}$")
    private String ssn;

    public DataObject() {}

    private DataObject(String name, String ssn) {
        this.name = name;
        this.ssn = ssn;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the ssn
     */
    public String getSsn() {
        return ssn;
    }

    public static DataObject.Builder with() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String ssn;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder ssn(String ssn) {
            this.ssn = ssn;
            return this;
        }

        public DataObject build() {
            return new DataObject(name, ssn);
        }
    }
}