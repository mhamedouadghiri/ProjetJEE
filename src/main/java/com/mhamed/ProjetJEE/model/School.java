package com.mhamed.ProjetJEE.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class School {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String password;
    private Long internshipsManagerId;
}
