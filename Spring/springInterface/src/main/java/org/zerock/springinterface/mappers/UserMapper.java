package org.zerock.springinterface.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.springinterface.domain.UserVO;

@Mapper
public interface UserMapper {

    // 사용자 회원가입
    int insertUser(UserVO user);

    // 사용자 로그인
    UserVO getUserByUsernameAndPassword(UserVO user);

    // 사용자 조회 (중복 체크용)
    UserVO getUserByUsername(String username);

    // 닉네임으로 사용자 조회 (중복 체크용)
    UserVO getUserByNickname(String nickname);  // 추가

    // 사용자 총 수
    int getTotalUserCount();
}