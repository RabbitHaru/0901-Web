package org.zerock.springex.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.springex.dto.MemberDTO;
import org.zerock.springex.mappers.MemberMapper;
import org.zerock.springex.vo.MemberVO;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService{
    private final MemberMapper memberMapper;

    public MemberDTO login(MemberDTO param) {
        // mapper에서 사용하기 위해 dto를 vo로 변환
        MemberVO vo = param.convertVO();
        // DB에서 id와 pw로 조회
        MemberVO loginVO = memberMapper.login(vo);
        // 조회 결과를 VO에서 DTO로 변환
        if(loginVO == null || loginVO.getId() == null){
            return null;
        }
        MemberDTO loginDTO = new MemberDTO(loginVO);
        // 반환 실행
        return loginDTO;
    }

    @Override
    public int join(MemberDTO memberDTO){
        MemberVO vo = memberDTO.convertVO();
        log.info("회원가입 처리: {}", vo);
        return memberMapper.join(vo);
    }
}
