package com.mhamed.ProjetJEE.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Representative extends User {
    private String firstName;
    private String lastName;
    private Long companyId;

    @Builder
    public Representative(Long id, String email, String password, String phone, String firstName, String lastName, Long companyId) {
        super(id, email, password, phone);
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyId = companyId;
    }
}
