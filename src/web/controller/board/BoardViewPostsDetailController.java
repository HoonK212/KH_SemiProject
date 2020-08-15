package web.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Board;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;

@WebServlet("/detail/posts")
public class BoardViewPostsDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//서비스 객체 생성
	private BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String param = req.getParameter("bdNo");
		int boardNo = 0;
		if( param!=null && !"".equals(param) ) {
			boardNo = Integer.parseInt(param);
		}
		
		boardService.viewCnt(boardNo);
		
		Board board = new Board();
		board = boardService.viewPostsDetail(boardNo);
		
		req.setAttribute("board", board);
		
		int userno = board.getUserNo();
		
		req.setAttribute("user", boardService.postsDetailUser(userno));
		
		req.getRequestDispatcher("/WEB-INF/views/board/detailPosts.jsp")
		.forward(req, resp);
	}
}
