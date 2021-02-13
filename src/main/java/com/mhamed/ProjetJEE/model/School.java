package com.mhamed.ProjetJEE.model;


import lombok.Getter;
import lombok.Setter;

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
}
