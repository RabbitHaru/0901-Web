package org.zerock.springbookblog.domain.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String nickname;
}