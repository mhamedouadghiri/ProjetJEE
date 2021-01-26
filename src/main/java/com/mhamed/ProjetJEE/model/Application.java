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
    private Byte[] candidatePicture;
    private String coverLetter;
    private Boolean request;
    private Boolean answer;
    private Boolean result;
}
