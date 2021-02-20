package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.Application;

import java.util.List;

public interface ApplicationDAO {

    Long save(Application entity);

    List<Application> getApplicationsByOfferId(Long offerId);

    Application getApplicationByOfferIdAndStudentId(Long offerId, Long studentId);
}
