package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.InternshipOffer;

import java.util.List;

public interface InternshipOfferDAO extends BaseDAO<InternshipOffer> {

    List<InternshipOffer> getOffersByCompanyId(Long companyId);
}
