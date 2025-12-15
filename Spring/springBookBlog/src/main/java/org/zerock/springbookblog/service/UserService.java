package org.zerock.springbookblog.service;

import org.zerock.springbookblog.domain.dto.UserDTO;

public interface UserService {

    // 회원가입
    boolean joinUser(UserDTO userDTO);

    // 로그인
    UserDTO loginUser(String username, String password);

    // 사용자 총 수
    int getTotalUserCount();
}