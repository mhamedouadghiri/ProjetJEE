package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.Student;
import com.mhamed.ProjetJEE.model.UserType;

import java.sql.*;

public class StudentDatasource extends UserDatasource implements StudentDAO {

    public StudentDatasource() {
        super(UserType.student);
    }

    @Override
    public Long save(Student entity) {
        String query = "insert into student values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setString(3, entity.getCity());
            ps.setString(4, entity.getCountry());
            ps.setString(5, entity.getAddress());
            ps.setBoolean(6, entity.getStatus());
            ps.setString(7, entity.getEmail());
            ps.setString(8, entity.getPhone());
            if (entity.getSchoolYear() == null) {
                ps.setNull(9, Types.BIGINT);
            } else {
                ps.setLong(9, entity.getSchoolYear());
            }
            ps.setString(10, entity.getMajor());
            ps.setString(11, entity.getPassword());
            ps.setLong(12, entity.getSchoolId());
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
}
