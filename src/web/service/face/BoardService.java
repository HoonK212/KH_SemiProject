package web.service.face;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import web.dto.Board;
import web.dto.User;
import web.util.Paging;

public interface BoardService {
	
	public User getUserNoByUserId(User user);
	
	/**
	 * 요청 파라미터의 세션객체 이용하여 사용자 아이디, 닉네임 반환
	 * @param req - 세션 객체 이용할 요청 객체
	 * @return User - 사용자 객체
	 */
	public User getUserIdNick(HttpServletRequest req);

	public List<Board> viewPosts();
	
	/**
	 * 요청 파라미터로 페이징 계산해서 페이징 객체 반환
	 * @param req - 요청 파라미터 객체
	 * @return Paging - 계산된 페이징 객체 반환
	 */
	public Paging getPagingPosts(HttpServletRequest req);
	
	public Paging getPagingNotice(HttpServletRequest req);
	
	/**
	 * 페이징 객체 전달하여 게시판 목록 반환
	 * @param paging - 전달 페이징 객체
	 * @return Board - 페이징 처리 게시판 목록 반환
	 */
	public Map<Board, String> getListPosts(Paging paging);
	public List<Board> getListNotice(Paging paging);
	
	/**
	 * 요청 파라미터로 Board객체 Dao 전달
	 * @param req - 요청 파라미터 객체
	 */
	public void addPosts(HttpServletRequest req);
	
	/**
	 * 전달파라미터 Dao 전달하여 Board 객체 반환
	 * @param boardNo - 상세 조회 번호
	 * @return Board - boardNo의 Board 객체
	 */
	public Board viewPostsDetail(int boardNo);
	
	/**
	 * boardNo 에 해당하는 조회수 증가
	 * @param boardNo - 조회된 게시글 번호
	 */
	public void viewCnt(int boardNo);
	
	
	public User postsDetailUser(int userno);
	
	
}