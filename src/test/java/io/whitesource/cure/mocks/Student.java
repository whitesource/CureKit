package io.whitesource.cure.mocks;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Student implements Serializable {
    private float age;
    private String firstName;
    private String lastName;
    private String degreeName;
}
