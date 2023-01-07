/**
 * Created By Sunil Verma
 * Date: 07/01/23
 * Time: 4:11 PM
 * Project Name: security
 */

package com.security.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nimbusds.jose.JOSEException;
import com.security.dto.GenericResponse;
import com.security.dto.LoginDto;
import com.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/sign-in")
    public GenericResponse signIn( @RequestBody LoginDto dto) throws JOSEException {
        return userService.signIn(dto);
    }

    @PostMapping(value = "/sign-up")
    public GenericResponse signUp( @RequestBody LoginDto dto) throws JOSEException {
        return userService.signUp(dto);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @GetMapping(value = "/get-all")
    public GenericResponse getAllUser() {
        return userService.getAllUser();
    }

}
