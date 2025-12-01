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

@WebServlet("/boarddelete.do")
public class BoardDeleteController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private BoardService service = new BoardService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method not supported.");
	    }


	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
        PrintWriter out = resp.getWriter();
        
        // 게시글 번호 가져오기
        String num = req.getParameter("num");
        
        // DB에서 게시글 가져오기
        BoardDAO dao = new BoardDAO();
        BoardDTO dto = dao.selectView(num);
        
        // 세션에서 로그인한 사용자 정보 가져오기
        HttpSession session = req.getSession();
        String sessionId = (String) session.getAttribute("UserId");
        
        // 세션 값이 null이면 로그인하지 않은 상태
        if (sessionId == null) {
            JSFunction.alertBack("로그인 후 삭제 가능합니다.", out);
            return;
        }
        
        // 작성자와 로그인한 사용자가 동일한지 확인
        if (sessionId.equals(dto.getId())) {
            // 게시글 삭제 처리
            dto.setNum(num);  // 게시글 번호 설정
            int delResult = dao.deletePost(dto);  // 게시글 삭제

            // DB 자원 종료
            dao.close();

            if (delResult == 1) {
                JSFunction.alertLocation("삭제되었습니다.", "boardlist.do", out);  // 성공 시 목록 페이지로 이동
            } else {
                JSFunction.alertBack("삭제에 실패하였습니다.", out);  // 실패 시 경고 후 이전 페이지로 돌아가기
            }
        } else {
            JSFunction.alertBack("본인만 삭제할 수 있습니다.", out);  // 작성자만 삭제할 수 있음
        }
    }
}