package io.whitesource.cure.mock;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable {
    private float age;
    private String firstName;
    private String lastName;
    private String degreeName;

    public Student(float age, String firstName, String lastName, String degreeName) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.degreeName = degreeName;
    }

    @Override
    public String toString() {
        return "academy.Student [age=" + age + ", firstName=" + firstName + ", lastName=" + lastName + ", degreeName=" + degreeName + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            return age == ((Student) obj).age
                    && firstName.equals(((Student) obj).firstName)
                    && lastName.equals(((Student) obj).lastName)
                    && degreeName.equals(((Student) obj).degreeName);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, firstName, lastName, degreeName);
    }
}
