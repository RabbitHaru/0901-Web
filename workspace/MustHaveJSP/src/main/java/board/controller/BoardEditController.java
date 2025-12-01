package board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import board.service.BoardService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import model1.BoardDAO;
import model1.BoardDTO;
import utils.JSFunction;

@WebServlet("/boardedit.do")
public class BoardEditController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private BoardService service = new BoardService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 // GET 요청 시 게시글 번호(num)를 받아서 BoardDTO 객체를 생성하고, 해당 게시글을 조회
	    String num = req.getParameter("num");  // num은 String 타입
	    BoardDTO dto = service.getBoard(num);

	    // 세션에서 로그인한 사용자 정보 가져오기
	    HttpSession session = req.getSession();
	    String sessionId = (String) session.getAttribute("UserId");

	    // 작성자와 로그인 사용자가 같지 않으면 수정할 수 없으므로 돌아가기
	    if (sessionId == null || !sessionId.equals(dto.getId())) {
	        resp.sendRedirect("boardlist.do");  // 게시판 목록으로 리다이렉트
	        return;
	    }

	    // 수정할 게시글 정보 request에 설정
	    req.setAttribute("dto", dto);

	    // 수정 화면으로 포워딩
	    req.getRequestDispatcher("/12_1/EditResult.jsp").forward(req, resp);
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	    PrintWriter out = resp.getWriter();

	    // 폼에서 전달된 수정된 값들 가져오기
	    String num = req.getParameter("num");  // num은 String 타입
	    String title = req.getParameter("title");
	    String content = req.getParameter("content");

	    // 게시글 정보 담을 DTO 객체 생성
	    BoardDTO dto = new BoardDTO();
	    dto.setNum(num);  // num을 String으로 전달
	    dto.setTitle(title);
	    dto.setContent(content);

	    // 세션에서 로그인한 사용자 정보 가져오기
	    HttpSession session = req.getSession();
	    String sessionId = (String) session.getAttribute("UserId");

	    // 작성자와 로그인 사용자가 같지 않으면 수정할 수 없으므로 돌아가기
	    BoardDTO originalDto = service.getBoard(num);
	    if (!sessionId.equals(originalDto.getId())) {
	        JSFunction.alertBack("작성자 본인만 수정할 수 있습니다.", out);
	        return;
	    }

	    // 게시글 수정 처리
	    int result = service.updateEdit(dto);  // 서비스에서 수정 메서드 호출

	    // 수정 성공 후 게시글 목록 페이지로 리다이렉트
	    if (result > 0) {
	        resp.sendRedirect("boardlist.do");
	    } else {
	        JSFunction.alertBack("수정 실패", out);  // 실패 시 경고 메시지
	    }
	}
}