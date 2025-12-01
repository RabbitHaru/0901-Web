package board.service;

import java.util.List;
import java.util.Map;

import model1.BoardDAO;
import model1.BoardDTO;

public class BoardService {
	private BoardDAO dao = new BoardDAO();
	// 출력하는 리스트 전체 개수를 출력
	// 서비스의 경우 데이터를 변경하지 않는다면 작성할 내용이 거의 없음
	// 중간 연결 클래스로 생각하고 반드시 작성
	public int getListCount(Map<String, Object> param) {
		int totalCount = dao.selectCount(param);
		return totalCount;
	}
	public List<BoardDTO> getList(Map<String, Object> param){
		List<BoardDTO> dtoList = dao.selectList(param);
		return dtoList;
	}
	// 조회수 1증가 서비스
	public void editVisitCount(String num) {
		dao.updateVisitCount(num);
	}
	// 1건 조회 서비스
	public BoardDTO getBoard(String num) {
		BoardDTO dto = dao.selectView(num);
		// content의 엔터키 <br/> 태그로 변경
		dto.setContent(dto.getContent().replace("\r\n", "<br/>"));
		return dto;
	}
	public int insertWrite(BoardDTO dto) {
		int result = dao.insertWrite(dto); // DAO 메서드 호출
	    dao.close();                        // DAO 자원 닫기
	    return result;
	}
	public int updateEdit(BoardDTO dto) {
        // 게시글을 수정하는 DAO 메서드 호출
        int result = dao.updateEdit(dto);
        return result;
	}
}
