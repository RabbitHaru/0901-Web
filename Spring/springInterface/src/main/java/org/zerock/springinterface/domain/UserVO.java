package org.zerock.springinterface.domain;

import lombok.Data;

@Data
public class UserVO {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String nickname;
    private String createdAt;
}