package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.Application;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ApplicationDatasource extends BaseDatasource<Application> implements ApplicationDAO {

    @Override
    public Application get(Long id) {
        return null;
    }

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
    public Boolean delete(Long id) {
        return null;
    }
}
