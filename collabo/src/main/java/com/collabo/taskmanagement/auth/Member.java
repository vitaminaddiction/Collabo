package com.collabo.taskmanagement.auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private int idx;
    private String name;
    private int age;
    private String gender;
    private String phone;
    private String email;
    private String password;
}
