package io.whitesource.cure.mock;

import io.whitesource.cure.mock.inner.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class Academy implements Serializable {
    private List<Student> students;
    private String academyName;
    private Set<Campus> campuses;
}
