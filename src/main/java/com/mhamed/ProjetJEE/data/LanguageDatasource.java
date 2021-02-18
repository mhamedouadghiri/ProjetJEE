package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.Language;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LanguageDatasource extends BaseDatasource<Language> implements LanguageDAO {

    @Override
    public Language get(Long id) {
        return null;
    }

    @Override
    public Long save(Language entity) {
        String query = "insert into language values (null, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getLevel());
            ps.setLong(3, entity.getStudentId());
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
    public List<Language> getLanguagesByStudentId(Long studentId) {
        List<Language> languages = new ArrayList<>();
        String query = "select * from language where student_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, studentId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                languages.add(
                        new Language(
                                resultSet.getLong("id"),
                                resultSet.getString("name"),
                                resultSet.getString("level"),
                                studentId
                        )
                );
            }
        } catch (SQLException ignored) {
        }
        return languages;
    }
}
