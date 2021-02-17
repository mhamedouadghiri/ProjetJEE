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
