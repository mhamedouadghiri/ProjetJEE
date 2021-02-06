package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.InternshipsManager;
import com.mhamed.ProjetJEE.model.UserType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InternshipsManagerDatasource extends UserDatasource implements InternshipsManagerDAO {

    public InternshipsManagerDatasource() {
        super(UserType.internships_manager);
    }

    @Override
    public Long save(InternshipsManager entity) {
        String query = "insert into internships_manager values (null, ?, ?, ?, ?)";
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
}
