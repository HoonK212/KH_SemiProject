package web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import web.dao.face.BoardDao;
import web.dao.impl.BoardDaoImpl;
import web.dto.Board;
import web.dto.User;
import web.service.face.BoardService;
import web.util.Paging;

public class BoardServiceImpl implements BoardService{

	private BoardDao boardDao = new BoardDaoImpl();
	
	public User getUserNoByUserId(User user) {
		
		user = boardDao.selectUserno(user);
		
		return user;
	}
	
	@Override //
	public User getUserIdNick(HttpServletRequest req) {
	
		HttpSession session = req.getSession();
		User user = new User();
		
		user.setUserId((String)session.getAttribute("userid"));
		user.setUserNick((String)session.getAttribute("usernick"));	
		return user;
	}
	
	@Override
	public List<Board> viewPosts() {
		
		List<Board> board = new ArrayList<>();
		board = boardDao.selectBoard();
		
		return board;
	}

	@Override //
	public Paging getPagingPosts(HttpServletRequest req) {
		
		//전달파라미터 curPage를 파싱한다
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param!=null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		}
		
		//검색어
		String search = (String)req.getParameter("search");
		//Board 테이블의 총 게시글 수를 조회한다
		int totalCount = boardDao.selectCntPosts(search);

		//Paging 객체 생성 - 현재 페이지(curPage), 총 게시글 수(totalCount) 활용
		Paging paging = new Paging(totalCount, curPage);
		paging.setSearch(search);
		
		
		//Paging 객체 반환
		return paging;
	}
	
	public Paging getPagingNotice(HttpServletRequest req) {
		
		//전달파라미터 curPage를 파싱한다
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param!=null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		}
		
		//검색어
		String search = (String)req.getParameter("search");
		//Board 테이블의 총 게시글 수를 조회한다
		int totalCount = boardDao.selectCntNotice(search);

		//Paging 객체 생성 - 현재 페이지(curPage), 총 게시글 수(totalCount) 활용
		Paging paging = new Paging(totalCount, curPage);
		paging.setSearch(search);
		
		//Paging 객체 반환
		return paging;
	}

	@Override //
	public Map<Board, String> getListPosts(Paging paging) {
		return boardDao.selectPosts(paging);
	}
	
	@Override //
	public List<Board> getListNotice(Paging paging) {
		return boardDao.selectNotice(paging);
	}

//	@Override //
//	public Map<String, Object> getList(Paging paging) {
//		return boardDao.selectAll(paging);
//	}
	
	@Override //
	public void addPosts(HttpServletRequest req) {
		Board board = new Board();
		User user = new User();
		
		board.setBdTitle((String)req.getParameter("title"));
		board.setBdQuestion((String)req.getParameter("content"));
		
		user = (User) req.getAttribute("userno");
		board.setUserNo(user.getUserNo());
	
		boardDao.insertBoard(board);
	}

	@Override //
	public Board viewPostsDetail(int boardNo) {
		
		return boardDao.selectBoardByBoardNo(boardNo);
	}

	@Override //
	public void viewCnt(int boardNo) {
		boardDao.updateViewCntByBoardNo(boardNo);
	}

	@Override
	public User postsDetailUser(int userno) {

		User user = boardDao.selectUserByUserNo(userno);
		
		return user;
	}

}