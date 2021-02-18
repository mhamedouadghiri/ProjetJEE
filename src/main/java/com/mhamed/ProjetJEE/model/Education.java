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
public class Education {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String name;
    private String level;
    private Long studentId;
}
