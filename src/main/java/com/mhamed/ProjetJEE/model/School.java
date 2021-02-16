package com.mhamed.ProjetJEE.model;


import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class School extends User {
    private String name;

    public School() {
    }

    public School(Long id, String email, String password, String phone, String name) {
        super(id, email, password, phone);
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof School)) return false;
        if (!super.equals(o)) return false;

        School school = (School) o;

        return Objects.equals(name, school.name);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
