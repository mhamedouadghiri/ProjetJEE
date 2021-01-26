package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.Company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompanyDatasource extends BaseDatasource<Company> implements CompanyDAO {

    @Override
    public Company get(Long id) {
        String query = "select * from company where id = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return Company.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .description(resultSet.getString("description"))
                        .city(resultSet.getString("city"))
                        .country(resultSet.getString("country"))
                        .address(resultSet.getString("address"))
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
    public Long save(Company entity) {
        String query = "insert into company values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getDescription());
            ps.setString(3, entity.getCity());
            ps.setString(4, entity.getCountry());
            ps.setString(5, entity.getAddress());
            ps.setString(6, entity.getPhone());
            ps.setString(7, entity.getEmail());
            ps.setString(8, entity.getPassword());
            ps.setLong(9, entity.getInternshipsManagerId());
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
        String query = "delete from company where id = ?";
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
