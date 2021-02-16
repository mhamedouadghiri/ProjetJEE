package com.mhamed.ProjetJEE.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        if (!super.equals(o)) return false;

        Company company = (Company) o;

        if (!Objects.equals(name, company.name)) return false;
        if (!Objects.equals(description, company.description)) return false;
        if (!Objects.equals(city, company.city)) return false;
        if (!Objects.equals(country, company.country)) return false;
        return Objects.equals(address, company.address);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
