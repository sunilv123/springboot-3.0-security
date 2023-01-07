/**
 * Created By Sunil Verma
 * Date: 07/01/23
 * Time: 4:13 PM
 * Project Name: security
 */

package com.security.service;

import com.nimbusds.jose.JOSEException;
import com.security.dto.GenericResponse;
import com.security.dto.LoginDto;

public interface UserService {

    GenericResponse signIn(LoginDto dto) throws JOSEException;

    GenericResponse signUp(LoginDto dto) throws JOSEException;

    GenericResponse getAllUser();
}
