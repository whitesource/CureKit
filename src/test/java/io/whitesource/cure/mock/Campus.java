package io.whitesource.cure.mock;

import java.io.Serializable;
import java.util.Objects;

public class Campus implements Serializable {
    private String name;
    private String address;

    public Campus(String campusName, String campusAddress) {
        this.name = campusName;
        this.address = campusAddress;
    }

    @Override
    public String toString() {
        return "academy.Campus [name=" + name + ", address=" + address + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Campus) {
            return name.equals(((Campus) obj).name)
                    && address.equals(((Campus) obj).address);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }
}
