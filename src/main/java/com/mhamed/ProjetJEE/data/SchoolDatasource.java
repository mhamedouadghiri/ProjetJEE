package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.School;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SchoolDatasource extends BaseDatasource<School> implements SchoolDAO {

    @Override
    public School get(Long id) {
        String query = "select * from school where id = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return School.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .phone(resultSet.getString("phone"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .internshipsManagerId(resultSet.getLong("internships_manager_id"))
                        .build();
            }
        } catch (SQLException ignored) {
        }
        return null;
    }

    @Override
    public Long save(School entity) {
        String query = "insert into school values (null, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getPhone());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getPassword());
            ps.setLong(5, entity.getInternshipsManagerId());
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
        String query = "delete from school where id = ?";
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
