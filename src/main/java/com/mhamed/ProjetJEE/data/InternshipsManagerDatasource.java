package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.InternshipsManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InternshipsManagerDatasource extends BaseDatasource<InternshipsManager> implements InternshipsManagerDAO {

    @Override
    public InternshipsManager get(Long id) {
        String query = "select * from internships_manager where id = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return InternshipsManager.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .email(resultSet.getString("email"))
                        .phone(resultSet.getString("phone"))
                        .password(resultSet.getString("password"))
                        .build();
            }
        } catch (SQLException ignored) {
        }
        return null;
    }

    @Override
    public Long save(InternshipsManager entity) {
        String query = "insert into internships_manager values (null, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getEmail());
            ps.setString(3, entity.getPhone());
            ps.setString(4, entity.getPassword());
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
        String query = "delete from internships_manager where id = ?";
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
