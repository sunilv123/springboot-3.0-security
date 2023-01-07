/**
 * Created By Sunil Verma
 * Date: 07/01/23
 * Time: 5:13 PM
 * Project Name: security
 */

package com.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.security.entity.AppUser;
import com.security.enums.RoleType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SignInResponseDto {

    private String id;

    private String email;

    private RoleType roleType;

    private String name;

    private String token;

    public SignInResponseDto(AppUser user){

        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.roleType = user.getRoleType();
    }
}
