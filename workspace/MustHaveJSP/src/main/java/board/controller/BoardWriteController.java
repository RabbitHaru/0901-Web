package board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import board.service.BoardService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.JspWriter;
import model1.BoardDAO;
import model1.BoardDTO;
import utils.JSFunction;

@WebServlet("/boardwrite.do")
public class BoardWriteController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private BoardService service = new BoardService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/12_1/Write.jsp")
		.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");

		HttpSession session = req.getSession();
		
		BoardDTO dto = new BoardDTO();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setId(session.getAttribute("UserId").toString());

		
		int iResult = service.insertWrite(dto);
		
		if(iResult == 1){
            resp.sendRedirect("boardlist.do"); 
        }else{
        	 JSFunction.alertBack("글쓰기에 실패하였습니다.", out);
        }
	}
}
