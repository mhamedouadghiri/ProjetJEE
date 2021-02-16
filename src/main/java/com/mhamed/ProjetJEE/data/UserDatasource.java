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

    @Override
    public User getByEmail(String email) {
        String query = "select * from " + userType.toString() + " where email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return buildUser(resultSet);
            }
        } catch (SQLException ignored) {
        }
        return null;
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String email = resultSet.getString("email");
        String phone = resultSet.getString("phone");
        String password = resultSet.getString("password");
        switch (userType) {
            case company:
                return new Company(id,
                        email,
                        password,
                        phone,
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("city"),
                        resultSet.getString("country"),
                        resultSet.getString("address"));
            case school:
                return new School(id,
                        email,
                        password,
                        phone,
                        resultSet.getString("name"));
            case student:
                return new Student(id,
                        email,
                        password,
                        phone,
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("city"),
                        resultSet.getString("country"),
                        resultSet.getString("address"),
                        resultSet.getObject("status", Boolean.class),
                        resultSet.getObject("school_year", Long.class),
                        resultSet.getString("major"),
                        resultSet.getLong("school_id"));
            default:
                throw new RuntimeException();
        }
    }
}
