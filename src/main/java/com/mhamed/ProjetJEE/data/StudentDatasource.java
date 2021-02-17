package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.Student;
import com.mhamed.ProjetJEE.model.UserType;
import com.mhamed.ProjetJEE.util.UserUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Student> getStudentsBySchoolId(Long schoolId) {
        List<Student> students = new ArrayList<>();
        String query = "select * from student where school_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, schoolId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                students.add(UserUtils.buildStudentFromResultSet(resultSet));
            }
        } catch (SQLException ignored) {
        }
        return students;
    }
}
