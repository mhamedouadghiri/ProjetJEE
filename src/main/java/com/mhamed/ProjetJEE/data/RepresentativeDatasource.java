package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.Representative;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RepresentativeDatasource extends BaseDatasource<Representative> implements RepresentativeDAO {

    @Override
    public Representative get(Long id) {
        String query = "select * from representative where id = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return Representative.builder()
                        .id(resultSet.getLong("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .email(resultSet.getString("email"))
                        .phone(resultSet.getString("phone"))
                        .password(resultSet.getString("password"))
                        .companyId(resultSet.getLong("company_id"))
                        .build();
            }
        } catch (SQLException ignored) {
        }
        return null;
    }

    @Override
    public Long save(Representative entity) {
        String query = "insert into representative values (null, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getPhone());
            ps.setString(5, entity.getPassword());
            ps.setLong(6, entity.getCompanyId());
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
        String query = "delete from representative where id = ?";
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
