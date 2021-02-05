package com.mhamed.ProjetJEE.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Company extends User {
    private Long id;
    private String name;
    private String description;
    private String city;
    private String country;
    private String address;
    private String phone;
    private String email;
    private String password;
    private Long internshipsManagerId;
}
