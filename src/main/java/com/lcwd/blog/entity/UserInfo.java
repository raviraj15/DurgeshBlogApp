package com.lcwd.blog.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@javax.persistence.Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private int id;
    private String uname;
    private String email;
    private String password;
    private String roles;
}
