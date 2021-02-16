package com.mhamed.ProjetJEE.model;


import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
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

    public Student() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        if (!super.equals(o)) return false;

        Student student = (Student) o;

        if (!Objects.equals(firstName, student.firstName)) return false;
        if (!Objects.equals(lastName, student.lastName)) return false;
        if (!Objects.equals(city, student.city)) return false;
        if (!Objects.equals(country, student.country)) return false;
        if (!Objects.equals(address, student.address)) return false;
        if (!Objects.equals(status, student.status)) return false;
        if (!Objects.equals(schoolYear, student.schoolYear)) return false;
        if (!Objects.equals(major, student.major)) return false;
        return Objects.equals(schoolId, student.schoolId);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (schoolYear != null ? schoolYear.hashCode() : 0);
        result = 31 * result + (major != null ? major.hashCode() : 0);
        result = 31 * result + (schoolId != null ? schoolId.hashCode() : 0);
        return result;
    }
}
