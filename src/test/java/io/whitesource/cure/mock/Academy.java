package io.whitesource.cure.mock;

import io.whitesource.cure.mock.inner.Student;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Academy implements Serializable {
    private List<Student> students;
    private String academyName;
    private Set<Campus> campuses;


    public Academy(Set<Campus> campuses, String name, List<Student> students) {
        this.campuses = campuses;
        this.academyName = name;
        this.students = students;
    }

    @Override
    public String toString() {
        return "academy.Academy [campus=" + campuses + ", academyName=" + academyName + ", students=" + students + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Academy) {
            return campuses.equals(((Academy) obj).campuses)
                    && academyName.equals(((Academy) obj).academyName)
                    && students.equals(((Academy) obj).students);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(students, academyName, campuses);
    }
}
