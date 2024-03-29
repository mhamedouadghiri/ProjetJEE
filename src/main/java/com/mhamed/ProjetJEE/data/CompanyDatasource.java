package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.Company;
import com.mhamed.ProjetJEE.model.UserType;
import com.mhamed.ProjetJEE.util.UserUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyDatasource extends UserDatasource implements CompanyDAO {

    public CompanyDatasource() {
        super(UserType.company);
    }

    @Override
    public Long save(Company entity) {
        String query = "insert into company values (null, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            if (ps.executeUpdate() == 1) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
            }
        } catch (SQLException ignored) {
        }
        return null;
    }

    @Override
    public List<Company> getAll() {
        List<Company> companies = new ArrayList<>();
        String query = "select * from company";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                companies.add(UserUtils.buildCompanyFromResultSet(resultSet));
            }
        } catch (SQLException ignored) {
        }
        return companies;
    }
}
