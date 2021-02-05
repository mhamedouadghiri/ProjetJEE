package com.mhamed.ProjetJEE.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class School extends User {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String password;
    private Long internshipsManagerId;
}
