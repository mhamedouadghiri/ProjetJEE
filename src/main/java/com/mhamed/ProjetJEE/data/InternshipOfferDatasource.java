package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.InternshipOffer;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InternshipOfferDatasource extends BaseDatasource<InternshipOffer> implements InternshipOfferDAO {

    @Override
    public InternshipOffer get(Long id) {
        return null;
    }

    @Override
    public Long save(InternshipOffer entity) {
        String query = "insert into internship_offer values (null, ?, ?, ?, ?, ?, ?, ?, null, null, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getTitle());
            ps.setObject(2, entity.getDuration(), Types.INTEGER);
            ps.setObject(3, entity.getStartDate(), Types.DATE);
            ps.setObject(4, entity.getEndDate(), Types.DATE);
            ps.setString(5, entity.getDescription());
            ps.setObject(6, entity.getPay(), Types.INTEGER);
            ps.setObject(7, entity.getStatus(), Types.BOOLEAN);
            ps.setString(8, entity.getField());
            ps.setLong(9, entity.getCompanyId());
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
    public List<InternshipOffer> getOffersByCompanyId(Long companyId) {
        List<InternshipOffer> offers = new ArrayList<>();
        String query = "select * from internship_offer where company_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, companyId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                offers.add(
                        new InternshipOffer(
                                resultSet.getLong("id"),
                                resultSet.getString("title"),
                                resultSet.getObject("duration", Integer.class),
                                resultSet.getObject("start_date", LocalDate.class),
                                resultSet.getObject("end_date", LocalDate.class),
                                resultSet.getString("description"),
                                resultSet.getObject("pay", Integer.class),
                                resultSet.getObject("status", Boolean.class),
                                resultSet.getString("field"),
                                resultSet.getLong("company_id")
                        )
                );
            }
        } catch (SQLException ignored) {
        }
        return offers;
    }
}
