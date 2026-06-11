package com.task1.user_management_api.dto;
import lombok.Data;
@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String name;

}
