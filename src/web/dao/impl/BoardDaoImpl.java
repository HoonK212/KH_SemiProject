package web.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import web.dao.face.BoardDao;
import web.dbutil.JDBCTemplate;
import web.dto.Board;
import web.dto.User;
import web.util.Paging;

public class BoardDaoImpl implements BoardDao{
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public List<Board> selectBoard() {
		
		List<Board> list = new ArrayList<>();
		
		conn = JDBCTemplate.getConnection();
		
		String sql = "";
		sql += "SELECT * FROM board ORDER BY BD_NO DESC";
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery(sql);
			
			while(rs.next()) {
				Board board = new Board();
				
				board.setBdNo(rs.getInt("bd_no"));
				board.setBdTitle(rs.getString("bd_title"));
				list.add(board);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return list;
	}

	@Override //
	public int selectCntPosts(String search) {
		
		conn = JDBCTemplate.getConnection(); //DB연결
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM board WHERE bd_grp=1 and bd_title LIKE '%'||?||'%'";
		
		//최종 결과값
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setString(1, search);
			
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 반환
			
			//조회결과 처리
			while( rs.next() ) {
				cnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return cnt;
	}
	
	@Override //
	public int selectCntNotice(String search) {
		
		conn = JDBCTemplate.getConnection(); //DB연결
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM board WHERE bd_grp=2 and bd_title LIKE '%'||?||'%'";
		
		//최종 결과값
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setString(1, search);
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 반환
			
			//조회결과 처리
			while( rs.next() ) {
				cnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return cnt;
	}

	@Override //
	public Map<Board, String> selectPosts(Paging paging) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "    SELECT rownum rnum, B.* FROM ("; 
		sql += "        SELECT";
		sql += "			b.bd_no, b.bd_title, b.bd_question,";
		sql += "            b.bd_answer, b.bd_date, b.bd_cnt, b.bd_grp, b.bd_yn, u.users_id";
		sql += "        FROM board b join users u on b.users_no = u.users_no";
		sql += "		WHERE bd_grp=1 and bd_title LIKE '%'||?||'%'"; 
		sql += "        ORDER BY bd_no DESC";
		sql += "    ) B";
		sql += "    ORDER BY rnum";
		sql += " ) BOARD";
		sql += " WHERE rnum BETWEEN ? AND ?";
		

		
		
		//결과 저장할 List
		Map<Board, String > boardList = new LinkedHashMap<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setString(1, paging.getSearch());
			ps.setInt(2, paging.getStartNo());	//페이징 게시글 시작 번호
			ps.setInt(3, paging.getEndNo());	//페이징 게시글 끝 번호
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				Board b = new Board(); //결과값 저장 객체
				String user= null;
				
				//결과값 한 행 처리
				b.setBdNo( rs.getInt("bd_no") );
				b.setBdTitle( rs.getString("bd_title") );
				b.setBdQuestion( rs.getString("bd_question") );
				b.setBdAnswer( rs.getString("bd_answer") );
				b.setBdDate( rs.getDate("bd_date") );
				b.setBdCnt( rs.getInt("bd_cnt") );
				b.setBdGrp(rs.getString("BD_GRP"));
				b.setBdYn(rs.getString("bd_yn"));
				
				
				user = rs.getString("users_id");
				//리스트에 결과값 저장
				boardList.put(b, user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return boardList;
	}
	
	@Override
	public List<Board> selectNotice(Paging paging) {
		//DB연결 객체
				conn = JDBCTemplate.getConnection();
				
				//SQL 작성
				String sql = "";
				sql += "SELECT * FROM (";
				sql += "    SELECT rownum rnum, B.* FROM ("; 
				sql += "        SELECT";
				sql += "			bd_no, bd_title, bd_question,";
				sql += "            bd_answer, bd_date, bd_cnt, bd_grp, bd_yn";
				sql += "        FROM board";
				sql += "		WHERE bd_grp=2 and bd_title LIKE '%'||?||'%'"; 
				sql += "        ORDER BY bd_no DESC";
				sql += "    ) B";
				sql += "    ORDER BY rnum";
				sql += " ) BOARD";
				sql += " WHERE rnum BETWEEN ? AND ?";
				
				
				//결과 저장할 List
				List<Board> boardList = new ArrayList<>();
				
				try {
					ps = conn.prepareStatement(sql); //SQL수행 객체
					
					ps.setString(1, paging.getSearch());
					ps.setInt(2, paging.getStartNo());	//페이징 게시글 시작 번호
					ps.setInt(3, paging.getEndNo());	//페이징 게시글 끝 번호
					
					rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
					
					//조회 결과 처리
					while(rs.next()) {
						Board b = new Board(); //결과값 저장 객체
						
						//결과값 한 행 처리
						b.setBdNo( rs.getInt("bd_no") );
						b.setBdTitle( rs.getString("bd_title") );
						b.setBdQuestion( rs.getString("bd_question") );
						b.setBdAnswer( rs.getString("bd_answer") );
						b.setBdDate( rs.getDate("bd_date") );
						b.setBdCnt( rs.getInt("bd_cnt") );
						b.setBdGrp(rs.getString("BD_GRP"));
						b.setBdYn(rs.getString("bd_yn"));
						
						//리스트에 결과값 저장
						boardList.add(b);
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					//DB객체 닫기
					JDBCTemplate.close(rs);
					JDBCTemplate.close(ps);
				}
				
				//최종 결과 반환
				return boardList;
		
	}

	@Override //
	public void insertBoard(Board board) {
		conn = JDBCTemplate.getConnection(); //DB연결
		
		
		//SQL 작성
		String sql = "";
		sql += "insert into board values (null, ?, ?, null, ?, 0, 1, 'N',? )";
		
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setString(1, board.getBdTitle());
			ps.setString(2, board.getBdQuestion());
			ps.setDate(3, sqlDate);
			ps.setInt(4, board.getUserNo());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
	}

	@Override
	public User selectUserno(User user) {
		// DB 연결
		conn = JDBCTemplate.getConnection();
		
		// DB sql 작성
		String sql = "";
		sql += "SELECT users_no FROM users WHERE users_id=?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getUserId());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				user.setUserNo( rs.getInt("users_no"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return user;
	}

	@Override //
	public Board selectBoardByBoardNo(int boardno) {
		
		// DB 연결
		conn = JDBCTemplate.getConnection();
		
		Board board = new Board();
		
		// DB sql 작성
		String sql = "";
		sql += "SELECT * FROM board WHERE bd_no=?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, boardno);
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				board.setBdNo( rs.getInt("bd_no") );
				board.setBdTitle( rs.getString("bd_title") );
				board.setBdQuestion( rs.getString("bd_question") );
				board.setBdAnswer( rs.getString("bd_answer") );
				board.setBdDate( rs.getDate("bd_date") );
				board.setBdCnt( rs.getInt("bd_cnt") );
				board.setBdYn(rs.getString("bd_yn"));
				board.setUserNo(rs.getInt("users_no"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return board;
	}

	@Override
	public void updateViewCntByBoardNo(int boardNo) {

		// DB 연결
		conn = JDBCTemplate.getConnection();
		
		// DB sql 작성
		String sql = "";
		sql += "UPDATE board SET bd_cnt = bd_cnt+1 WHERE bd_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, boardNo);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
	}

	@Override
	public User selectUserByUserNo(int userno) {
		
		// DB 연결
		conn = JDBCTemplate.getConnection();
		User user = new User();
		
		// DB sql 작성
		String sql = "";
		sql += "SELECT * FROM users WHERE users_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, userno);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				user.setUserId(rs.getString("users_id"));
				user.setUserName(rs.getString("users_name"));
				user.setUserNick(rs.getString("users_nick"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return user;
	}



}