package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.Education;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EducationDatasource extends BaseDatasource<Education> implements EducationDAO {

    @Override
    public Education get(Long id) {
        return null;
    }

    @Override
    public Long save(Education entity) {
        String query = "insert into education values (null, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            if (entity.getStartDate() == null) {
                ps.setNull(1, Types.DATE);
            } else {
                ps.setDate(1, Date.valueOf(entity.getStartDate()));
            }
            if (entity.getEndDate() == null) {
                ps.setNull(2, Types.DATE);
            } else {
                ps.setDate(2, Date.valueOf(entity.getEndDate()));
            }
            ps.setString(3, entity.getName());
            ps.setString(4, entity.getLevel());
            ps.setLong(5, entity.getStudentId());
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
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public List<Education> getEducationsByStudentId(Long studentId) {
        List<Education> educations = new ArrayList<>();
        String query = "select * from education where student_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, studentId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                educations.add(
                        new Education(
                                resultSet.getLong("id"),
                                resultSet.getDate("start_date").toLocalDate(),
                                resultSet.getDate("end_date").toLocalDate(),
                                resultSet.getString("name"),
                                resultSet.getString("level"),
                                studentId
                        )
                );
            }
        } catch (SQLException ignored) {
        }
        return educations;
    }
}
