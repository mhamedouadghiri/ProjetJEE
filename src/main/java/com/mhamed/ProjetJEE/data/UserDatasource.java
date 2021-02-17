package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.*;
import com.mhamed.ProjetJEE.util.UserUtils;

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
        switch (userType) {
            case company:
                return UserUtils.buildCompanyFromResultSet(resultSet);
            case school:
                return UserUtils.buildSchoolFromResultSet(resultSet);
            case student:
                return UserUtils.buildStudentFromResultSet(resultSet);
            default:
                throw new RuntimeException();
        }
    }
}
