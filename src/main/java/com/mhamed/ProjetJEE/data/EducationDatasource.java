package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.Education;

import java.sql.*;
import java.time.LocalDate;
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
            ps.setObject(1, entity.getStartDate(), Types.DATE);
            ps.setObject(2, entity.getEndDate(), Types.DATE);
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
                                resultSet.getObject("start_date", LocalDate.class),
                                resultSet.getObject("end_date", LocalDate.class),
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
