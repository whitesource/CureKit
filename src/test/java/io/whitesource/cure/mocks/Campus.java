package io.whitesource.cure.mocks;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Campus implements Serializable {
    private String name;
    private String address;
}
