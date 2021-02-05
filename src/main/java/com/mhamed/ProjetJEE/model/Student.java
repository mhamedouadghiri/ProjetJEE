package com.mhamed.ProjetJEE.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Student extends User {
    private Long id;
    private String firstName;
    private String lastName;
    private String city;
    private String country;
    private String address;
    private Boolean status;
    private String email;
    private String phone;
    private Long schoolYear;
    private String major;
    private String password;
    private Long schoolId;
}
