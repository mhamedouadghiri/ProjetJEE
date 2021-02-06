package com.mhamed.ProjetJEE.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InternshipsManager extends User {
    private String name;

    @Builder
    public InternshipsManager(Long id, String email, String password, String phone, String name) {
        super(id, email, password, phone);
        this.name = name;
    }
}
