package web.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.User;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;

@WebServlet("/add/posts")
public class BoardAddPostsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//서비스 객체 생성
	private BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		req.setAttribute("user", boardService.getUserIdNick(req));
	
		
		req.getRequestDispatcher("/WEB-INF/views/board/addPosts.jsp")
			.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	
		User user = boardService.getUserIdNick(req);
		
		req.setAttribute("userno", boardService.getUserNoByUserId(user) );
		
		boardService.addPosts(req);
		
		resp.sendRedirect("/view/posts");
	}
}
