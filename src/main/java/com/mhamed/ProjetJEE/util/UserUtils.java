package com.mhamed.ProjetJEE.util;

import com.mhamed.ProjetJEE.model.Company;
import com.mhamed.ProjetJEE.model.School;
import com.mhamed.ProjetJEE.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserUtils {
    public static Company buildCompanyFromResultSet(ResultSet resultSet) throws SQLException {
        return new Company(
                resultSet.getLong("id"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getString("phone"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getString("city"),
                resultSet.getString("country"),
                resultSet.getString("address")
        );
    }

    public static School buildSchoolFromResultSet(ResultSet resultSet) throws SQLException {
        return new School(
                resultSet.getLong("id"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getString("phone"),
                resultSet.getString("name")
        );
    }

    public static Student buildStudentFromResultSet(ResultSet resultSet) throws SQLException {
        return new Student(
                resultSet.getLong("id"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getString("phone"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("city"),
                resultSet.getString("country"),
                resultSet.getString("address"),
                resultSet.getObject("status", Boolean.class),
                resultSet.getObject("school_year", Long.class),
                resultSet.getString("major"),
                resultSet.getLong("school_id")
        );
    }
}
