package com.mhamed.ProjetJEE.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Company extends User {
    private String name;
    private String description;
    private String city;
    private String country;
    private String address;
    private Long internshipsManagerId;

    @Builder
    public Company(Long id, String email, String password, String phone, String name, String description, String city, String country, String address, Long internshipsManagerId) {
        super(id, email, password, phone);
        this.name = name;
        this.description = description;
        this.city = city;
        this.country = country;
        this.address = address;
        this.internshipsManagerId = internshipsManagerId;
    }
}
