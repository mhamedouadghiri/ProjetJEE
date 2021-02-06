package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.Representative;
import com.mhamed.ProjetJEE.model.UserType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RepresentativeDatasource extends UserDatasource implements RepresentativeDAO {

    public RepresentativeDatasource() {
        super(UserType.representative);
    }

    @Override
    public Long save(Representative entity) {
        String query = "insert into representative values (null, ?, ?, ?, ?, ?, ?)";
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
}
