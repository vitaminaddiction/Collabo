package com.collabo.taskmanagement.auth;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberReq {
    private String email;
    private String password;
    private String confirmpassword;
    private String phone;
    private String name;
}
