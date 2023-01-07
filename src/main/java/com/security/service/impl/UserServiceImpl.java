/**
 * Created By Sunil Verma
 * Date: 07/01/23
 * Time: 4:15 PM
 * Project Name: security
 */

package com.security.service.impl;

import com.nimbusds.jose.JOSEException;
import com.security.config.JwtTokenUtils;
import com.security.dto.GenericResponse;
import com.security.dto.LoginDto;
import com.security.dto.SignInResponseDto;
import com.security.entity.AppUser;
import com.security.repo.AppUserRepo;
import com.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    AppUserRepo appUserRepo;

    @Autowired
    JwtTokenUtils jwtTokenUtils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public GenericResponse signIn(LoginDto dto) throws JOSEException {

        AppUser user = appUserRepo.findByEmail(dto.getEmail());

        if (user == null) {
            return new GenericResponse(HttpStatus.UNAUTHORIZED.value(), "Email id is wrong", "FAILED");
        }

        if (bCryptPasswordEncoder.matches(dto.getPassword(), user.getPassword())) {
            SignInResponseDto response = new SignInResponseDto(user);
            response.setToken(jwtTokenUtils.getToken(user));
            return new GenericResponse(HttpStatus.OK.value(), response);
        } else {
            return new GenericResponse(HttpStatus.UNAUTHORIZED.value(), "Password is wrong", "FAILED");
        }

    }

    @Override
    public GenericResponse signUp(LoginDto dto) throws JOSEException {

        AppUser user = new AppUser();

        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setRoleType(dto.getRoleType());
        user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));

        appUserRepo.save(user);

        return new GenericResponse(HttpStatus.OK.value(), "Registration is done", "SUCCESS");
    }

    @Override
    public GenericResponse getAllUser() {
        return new GenericResponse(HttpStatus.OK.value(), appUserRepo.findAll().stream().map(SignInResponseDto :: new).toList());
    }

}
