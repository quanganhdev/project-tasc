package com.example.projecttasc.model.request;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
@Data
public class UserRequest {
    private String Email;

    private String fullName;

    private String Phone;

    private Date Brithday;

    private  String Password;

    private String Address;

    private String userName;
}
