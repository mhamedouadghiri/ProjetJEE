package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.Experience;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExperienceDatasource extends BaseDatasource<Experience> implements ExperienceDAO {

    @Override
    public Experience get(Long id) {
        return null;
    }

    @Override
    public Long save(Experience entity) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public List<Experience> getExperiencesByStudentId(Long studentId) {
        List<Experience> experiences = new ArrayList<>();
        String query = "select * from experience where student_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, studentId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                experiences.add(
                        new Experience(
                                resultSet.getLong("id"),
                                resultSet.getDate("start_date").toLocalDate(),
                                resultSet.getDate("end_date").toLocalDate(),
                                resultSet.getString("description"),
                                studentId
                        )
                );
            }
        } catch (SQLException ignored) {
        }
        return experiences;
    }
}
