package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.School;
import com.mhamed.ProjetJEE.model.UserType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SchoolDatasource extends UserDatasource implements SchoolDAO {

    public SchoolDatasource() {
        super(UserType.school);
    }

    @Override
    public Long save(School entity) {
        String query = "insert into school values (null, ?, ?, ?, ?, ?)";
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
}
