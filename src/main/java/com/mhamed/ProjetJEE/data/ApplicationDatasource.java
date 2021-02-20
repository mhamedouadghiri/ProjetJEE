package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.Application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDatasource implements ApplicationDAO {

    protected static Connection connection = JDBCConnection.getConnection();

    /**
     * @param entity candidate application
     * @return 951753852456L on success
     */
    @Override
    public Long save(Application entity) {
        String query = "insert into application values (?, ?, ?, null, null, null)";
        try {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, entity.getInternshipOfferId());
            ps.setLong(2, entity.getStudentId());
            ps.setString(3, entity.getCoverLetter());
            if (ps.executeUpdate() == 1) {
                return 951753852456L;
            }
        } catch (SQLException ignored) {
        }
        return null;
    }

    @Override
    public List<Application> getApplicationsByOfferId(Long offerId) {
        List<Application> applications = new ArrayList<>();
        String query = "select * from application where internship_offer_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, offerId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                applications.add(
                        new Application(
                                resultSet.getLong("internship_offer_id"),
                                resultSet.getLong("student_id"),
                                resultSet.getString("cover_letter")
                        )
                );
            }
        } catch (SQLException ignored) {
        }
        return applications;
    }

    @Override
    public Application getApplicationByOfferIdAndStudentId(Long offerId, Long studentId) {
        String query = "select * from application where internship_offer_id = ? and student_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, offerId);
            ps.setLong(2, studentId);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new Application(
                        resultSet.getLong("internship_offer_id"),
                        resultSet.getLong("student_id"),
                        resultSet.getString("cover_letter")
                );
            }
        } catch (SQLException ignored) {
        }
        return null;
    }
}
