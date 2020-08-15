package web.controller.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Board;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;
import web.util.Paging;

@WebServlet("/view/notice")
public class BoardViewNoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//서비스 객체 생성
	private BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//요청파라미터를 전달하여 Paging 객체 생성하기
		Paging paging = boardService.getPagingNotice(req);
		
		//게시글 페이징 처리 조회
		List<Board> boardList = boardService.getListNotice(paging);
		
		
		//페이징계산결과 MODEL값 전달
		req.setAttribute("paging", paging);
	
		//게시글 조회 MODEL값 전달
		req.setAttribute("boardList", boardList);

		//View 전달
		req.getRequestDispatcher("/WEB-INF/views/board/viewNotice.jsp")
			.forward(req, resp);
	}
}
