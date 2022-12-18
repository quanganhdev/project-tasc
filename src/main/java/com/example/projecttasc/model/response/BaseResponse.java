package com.example.projecttasc.model.response;

import lombok.Data;

@Data
public class BaseResponse {
    private int code;
    private String mess;

    public BaseResponse(int code, String mess) {
        this.code = 1;
        this.mess = "oke la";
    }
    public BaseResponse(){
        this.code = 1;
        this.mess = "oke la";
    }

}

