package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDatasource extends BaseDatasource<Student> implements StudentDAO {

    @Override
    public Student get(Long id) {
        String query = "select * from student where id = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return Student.builder()
                        .id(resultSet.getLong("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .city(resultSet.getString("city"))
                        .country(resultSet.getString("country"))
                        .address(resultSet.getString("address"))
                        .status(resultSet.getBoolean("status"))
                        .email(resultSet.getString("email"))
                        .phone(resultSet.getString("phone"))
                        .schoolYear(resultSet.getLong("school_year"))
                        .major(resultSet.getString("major"))
                        .password(resultSet.getString("password"))
                        .schoolId(resultSet.getLong("school_id"))
                        .build();
            }
        } catch (SQLException ignored) {
        }
        return null;
    }

    @Override
    public Long save(Student entity) {
        String query = "insert into student values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setString(3, entity.getCity());
            ps.setString(4, entity.getCountry());
            ps.setString(5, entity.getAddress());
            ps.setBoolean(6, entity.getStatus());
            ps.setString(7, entity.getEmail());
            ps.setString(8, entity.getPhone());
            ps.setLong(9, entity.getSchoolYear());
            ps.setString(10, entity.getMajor());
            ps.setString(11, entity.getPassword());
            ps.setLong(12, entity.getSchoolId());
            if (ps.executeUpdate() == 1) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getLong("id");
                }
            }
        } catch (SQLException ignored) {
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        String query = "delete from student where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException ignored) {
        }
        return false;
    }
}
