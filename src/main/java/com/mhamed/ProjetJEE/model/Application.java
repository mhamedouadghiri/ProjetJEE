package com.mhamed.ProjetJEE.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    private Long internshipOfferId;
    private Long studentId;
    private String coverLetter;
    private Boolean request;  // company's call for interview
    private Boolean answer;  // student's answer
    private Boolean result;  // result of the interview, on true creation of Internship entity

    public Application(Long internshipOfferId, Long studentId, String coverLetter) {
        this.internshipOfferId = internshipOfferId;
        this.studentId = studentId;
        this.coverLetter = coverLetter;
    }
}
