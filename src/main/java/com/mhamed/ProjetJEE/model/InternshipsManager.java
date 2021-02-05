package com.mhamed.ProjetJEE.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InternshipsManager extends User {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;
}
