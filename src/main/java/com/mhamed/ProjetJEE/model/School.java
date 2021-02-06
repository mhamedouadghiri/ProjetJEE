package com.mhamed.ProjetJEE.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class School extends User {
    private String name;
    private Long internshipsManagerId;

    @Builder
    public School(Long id, String email, String password, String phone, String name, Long internshipsManagerId) {
        super(id, email, password, phone);
        this.name = name;
        this.internshipsManagerId = internshipsManagerId;
    }
}
