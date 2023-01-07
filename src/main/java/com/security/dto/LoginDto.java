/**
 * Created By Sunil Verma
 * Date: 07/01/23
 * Time: 4:13 PM
 * Project Name: security
 */

package com.security.dto;

import com.security.enums.RoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LoginDto {

    private String email;

    private String password;

    private String name;

    private RoleType roleType;
}
