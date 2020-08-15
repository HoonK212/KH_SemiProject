package web.dao.face;

import java.util.List;
import java.util.Map;

import web.dto.Board;
import web.dto.User;
import web.util.Paging;

public interface BoardDao {
	public User selectUserno(User user);

	public List<Board> selectBoard();
	
	/**
	 * 총 게시글 수 조회
	 * @return 총 게시글 수 반환
	 */
	public int selectCntPosts(String search);
	
	public int selectCntNotice(String search);
	
	/**
	 * 페이징 객체 전달하여 게시판 목록 조회
	 * @param paging - 페이징 객체
	 * @return Board - 페이징 처리 게시판 목록 반환
	 */
	public Map<Board, String> selectPosts(Paging paging);

	
	public List<Board> selectNotice(Paging paging);
	
	/**
	 * 전달 파라미터 Board 테이블 삽입
	 * @param board - 삽입 할 Board 객체
	 */
	public void insertBoard(Board board);

	/**
	 * 자유게시판 번호로 게시글 상세 조회
	 * @param boardno - board 테이블 자유게시판 번호
	 * @return Baord - boardno의 board 객체 전체 조회
	 */
	public Board selectBoardByBoardNo(int boardno);
	
	/**
	 * boardNo에 해당하는 조회수 칼럼 업데이트
	 * @param boardNo - 조회된 게시글 번호
	 */
	public void updateViewCntByBoardNo(int boardNo);

	public User selectUserByUserNo(int userno);
	
}