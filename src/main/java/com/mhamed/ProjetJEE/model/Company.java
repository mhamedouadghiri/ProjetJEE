package com.mhamed.ProjetJEE.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Company extends User {
    private String name;
    private String description;
    private String city;
    private String country;
    private String address;

    public Company() {
    }

    public Company(Long id, String email, String password, String phone, String name, String description, String city, String country, String address) {
        super(id, email, password, phone);
        this.name = name;
        this.description = description;
        this.city = city;
        this.country = country;
        this.address = address;
    }
}
