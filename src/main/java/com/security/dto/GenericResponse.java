package com.security.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class GenericResponse {

    private int code;

    private String status;

    private String message;

    private Object payLoad;

    public GenericResponse(int code, String message, String status) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public GenericResponse(int code, Object payLoad) {

        this.code = code;
        this.payLoad = payLoad;
    }

	
}
