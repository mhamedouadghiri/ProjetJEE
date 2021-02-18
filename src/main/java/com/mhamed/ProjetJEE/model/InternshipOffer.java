package com.mhamed.ProjetJEE.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternshipOffer {
    private Long id;
    private String title;
    private Integer duration;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private Integer pay;
    private Boolean status;
    private String field;
    private Long companyId;
}
