package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserDatasource implements UserDAO {

    protected static Connection connection;

    private final UserType userType;

    protected UserDatasource(UserType userType) {
        connection = JDBCConnection.getConnection();
        this.userType = userType;
    }

    @Override
    public User get(Long id) {
        String query = "select * from " + userType.toString() + " where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return buildUser(resultSet);
            }
        } catch (SQLException ignored) {
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        String query = "delete from " + userType.toString() + " where id = ?";
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

    private User buildUser(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String email = resultSet.getString("email");
        String phone = resultSet.getString("phone");
        String password = resultSet.getString("password");
        switch (userType) {
            case internships_manager:
                return InternshipsManager.builder()
                        .id(id)
                        .email(email)
                        .phone(phone)
                        .password(password)
                        .name(resultSet.getString("name"))
                        .build();
            case company:
                return Company.builder()
                        .id(id)
                        .email(email)
                        .phone(phone)
                        .password(password)
                        .name(resultSet.getString("name"))
                        .description(resultSet.getString("description"))
                        .city(resultSet.getString("city"))
                        .country(resultSet.getString("country"))
                        .address(resultSet.getString("address"))
                        .internshipsManagerId(resultSet.getLong("internships_manager_id"))
                        .build();
            case school:
                return School.builder()
                        .id(id)
                        .email(email)
                        .phone(phone)
                        .password(password)
                        .name(resultSet.getString("name"))
                        .internshipsManagerId(resultSet.getLong("internships_manager_id"))
                        .build();
            case representative:
                return Representative.builder()
                        .id(id)
                        .email(email)
                        .phone(phone)
                        .password(password)
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .companyId(resultSet.getLong("company_id"))
                        .build();
            case student:
                return Student.builder()
                        .id(id)
                        .email(email)
                        .phone(phone)
                        .password(password)
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .city(resultSet.getString("city"))
                        .country(resultSet.getString("country"))
                        .address(resultSet.getString("address"))
                        .status(resultSet.getBoolean("status"))
                        .schoolYear(resultSet.getLong("school_year"))
                        .major(resultSet.getString("major"))
                        .schoolId(resultSet.getLong("school_id"))
                        .build();
            default:
                throw new RuntimeException();
        }
    }
}
