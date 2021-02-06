package com.mhamed.ProjetJEE.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Student extends User {
    private String firstName;
    private String lastName;
    private String city;
    private String country;
    private String address;
    private Boolean status;
    private Long schoolYear;
    private String major;
    private Long schoolId;

    @Builder
    public Student(Long id, String email, String password, String phone, String firstName, String lastName, String city, String country, String address, Boolean status, Long schoolYear, String major, Long schoolId) {
        super(id, email, password, phone);
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.country = country;
        this.address = address;
        this.status = status;
        this.schoolYear = schoolYear;
        this.major = major;
        this.schoolId = schoolId;
    }
}
