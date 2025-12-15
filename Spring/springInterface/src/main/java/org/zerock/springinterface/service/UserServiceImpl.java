package org.zerock.springinterface.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.springinterface.domain.UserVO;
import org.zerock.springinterface.domain.dto.UserDTO;
import org.zerock.springinterface.mappers.UserMapper;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean joinUser(UserDTO userDTO) {
        // 아이디 중복 체크
        UserVO existingUser = userMapper.getUserByUsername(userDTO.getUsername());
        if (existingUser != null) {
            return false; // 이미 존재하는 아이디
        }

        // 닉네임 중복 체크 (추가)
        UserVO existingNickname = userMapper.getUserByNickname(userDTO.getNickname());
        if (existingNickname != null) {
            return false; // 이미 존재하는 닉네임
        }

        UserVO user = convertToVO(userDTO);
        int result = userMapper.insertUser(user);
        return result > 0;
    }

    @Override
    public UserDTO loginUser(String username, String password) {
        UserVO user = new UserVO();
        user.setUsername(username);
        user.setPassword(password);
        user = userMapper.getUserByUsernameAndPassword(user);
        return user != null ? convertToDTO(user) : null;
    }

    @Override
    public int getTotalUserCount() {
        return userMapper.getTotalUserCount();
    }

    // VO → DTO 변환
    private UserDTO convertToDTO(UserVO userVO) {
        UserDTO dto = new UserDTO();
        dto.setId(userVO.getId());
        dto.setUsername(userVO.getUsername());
        dto.setPassword(userVO.getPassword());
        dto.setEmail(userVO.getEmail());
        dto.setNickname(userVO.getNickname());  // nickname으로 변경
        return dto;
    }

    // DTO → VO 변환
    private UserVO convertToVO(UserDTO userDTO) {
        UserVO vo = new UserVO();
        vo.setUsername(userDTO.getUsername());
        vo.setPassword(userDTO.getPassword());
        vo.setEmail(userDTO.getEmail());
        vo.setNickname(userDTO.getNickname());  // nickname으로 변경
        return vo;
    }
}