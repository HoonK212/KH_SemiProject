package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import web.dao.face.ReviewDao;
import web.dbutil.JDBCTemplate;
import web.dto.Picture;
import web.dto.Report;
import web.dto.Review;
import web.dto.Reviewverif;
import web.dto.User;
import web.util.Paging;

public class ReviewDaoImpl implements ReviewDao {

	
	@Override
	public int selectCntReviewByUserNo(int userno) {
		
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int cnt = 0;
		
		String sql = "SELECT COUNT(*) FROM REVIEW WHERE USERS_NO = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userno);
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				cnt = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		return cnt;
	}

	@Override
	public void updateReviewByMenuNoUserNo(String updateContent, int userno, int menuNo) {

		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		
		int result = -1;
		
		String sql = "UPDATE REVIEW SET REVIEW_CONTENT = ?";
		sql += "	WHERE USERS_NO = ? AND MENU_NO = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, updateContent);
			ps.setInt(2, userno);
			ps.setInt(3, menuNo);
			
			result = ps.executeUpdate();
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
	}

	@Override
	public List<Map<String, Object>> selectReview(Paging paging, int userno, int menuno) {
		
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		conn = JDBCTemplate.getConnection();
		
		//SQL 작성
		String sql = "";
		sql += "SELECT *";
		sql += " FROM review R join picture P on r.users_no = p.users_no";
		sql += " LEFT OUTER JOIN (select * from reviewverif where userno=?) RV ON r.review_no = rv.review_no";
		sql += " WHERE r.menu_no=?";
		sql += " and review_report = 'N' ";
		sql += " ORDER BY (r.review_good-r.review_bad) DESC";
		
//		sql += "SELECT RV.* FROM (";
//		sql += "    SELECT rownum rnum, B.* FROM ("; 
//		sql += "        SELECT *";
//		sql += "        FROM review R join picture P on r.users_no = p.users_no";
//		sql += "		LEFT OUTER JOIN reviewverif RV ON r.review_no = rv.review_no";
//		sql += "		WHERE r.menu_no=?";
//		sql += "		and review_report = 'N' "; 
//		sql += "        ORDER BY r.review_no DESC";
//		sql += "    ) B";
//		sql += "    ORDER BY rnum";
//		sql += " ) RV";
//		sql += " WHERE rnum BETWEEN ? AND ?";
		
//		sql += "SELECT * FROM (";
//		sql += "    SELECT rownum rnum, B.* FROM ("; 
//		sql += "        SELECT *";
//		sql += "        FROM review R join picture P on r.users_no = p.users_no";
//		sql += "		WHERE r.menu_no=?";
//		sql += "		and review_report = 'N' "; 
//		sql += "        ORDER BY r.review_no DESC";
//		sql += "    ) B";
//		sql += "    ORDER BY rnum";
//		sql += " ) BOARD";
//		sql += " WHERE rnum BETWEEN ? AND ?";

//		System.out.println(sql);
//		System.out.println(paging.getStartNo());
//		System.out.println(paging.getEndNo());
//		System.out.println(paging.getSearch());
		
		//결과 저장할 List
		List<Map<String, Object>> reviewList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, userno);
			ps.setInt(2, menuno);
//			ps.setInt(2, paging.getStartNo());	//페이징 게시글 시작 번호
//			ps.setInt(3, paging.getEndNo());	//페이징 게시글 끝 번호
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
//			System.out.println(rs.next());
			//조회 결과 처리
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();

//				System.out.println(rs.getInt("bd_no"));
				Picture picture = new Picture(); //결과값 저장 객체
				Review review = new Review();
				Reviewverif reviewverif = new Reviewverif();
				
				//결과값 한 행 처리
				picture.setPicNo(rs.getInt("pic_no"));
				picture.setPicName(rs.getString("pic_name"));
				picture.setPicServer(rs.getString("pic_server"));
				
				review.setReviewNo(rs.getInt("review_no"));
				review.setReviewContent(rs.getString("review_content"));
				review.setReviewDate(rs.getDate("review_date"));
				review.setReviewGood(rs.getInt("review_good"));
				review.setReviewBad(rs.getInt("review_bad"));
				review.setReviewReport(rs.getString("review_report"));
				review.setUserNo(rs.getInt("users_no"));
				review.setMenuNo(rs.getInt("menu_no"));
				
				reviewverif.setGood_bad(rs.getInt("good_bad"));
				
				//맵에 결과값 저장
				map.put("picture", picture);
				map.put("review", review);
				map.put("reviewverif", reviewverif);
				
				//리스트에 결과값 저장
				reviewList.add(map);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		System.out.println("넘어가냐"+reviewList);
		//최종 결과 반환
		return reviewList;
	}
//	@Override
//	public Map<Review, Picture> selectReview(Paging paging, int menuno) {
//		
//		Connection conn = JDBCTemplate.getConnection();
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		
//		conn = JDBCTemplate.getConnection();
//		
//		//SQL 작성
//		String sql = "";
//		sql += "SELECT * FROM (";
//		sql += "    SELECT rownum rnum, B.* FROM ("; 
//		sql += "        SELECT *";
//		sql += "        FROM review R join picture P on r.users_no = p.users_no";
//		sql += "		WHERE r.menu_no=?";
//		sql += "		and review_report = 'N' "; 
//		sql += "        ORDER BY r.review_no DESC";
//		sql += "    ) B";
//		sql += "    ORDER BY rnum";
//		sql += " ) BOARD";
//		sql += " WHERE rnum BETWEEN ? AND ?";
//		
//		System.out.println(sql);
//		System.out.println(paging.getStartNo());
//		System.out.println(paging.getEndNo());
//		System.out.println(paging.getSearch());
//		
//		//결과 저장할 List
//		Map<Review, Picture> reviewList = new LinkedHashMap<>();
//		
//		try {
//			ps = conn.prepareStatement(sql); //SQL수행 객체
//			
//			ps.setInt(1, menuno);
//			ps.setInt(2, paging.getStartNo());	//페이징 게시글 시작 번호
//			ps.setInt(3, paging.getEndNo());	//페이징 게시글 끝 번호
//			
//			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
//			
////			System.out.println(rs.next());
//			//조회 결과 처리
//			while(rs.next()) {
////				System.out.println(rs.getInt("bd_no"));
//				Picture picture = new Picture(); //결과값 저장 객체
//				Review review = new Review();
//				
//				//결과값 한 행 처리
//				picture.setPicNo(rs.getInt("pic_no"));
//				picture.setPicName(rs.getString("pic_name"));
//				picture.setPicServer(rs.getString("pic_server"));
//				
//				review.setReviewNo(rs.getInt("review_no"));
//				review.setReviewContent(rs.getString("review_content"));
//				review.setReviewDate(rs.getDate("review_date"));
//				review.setReviewGood(rs.getInt("review_good"));
//				review.setReviewBad(rs.getInt("review_bad"));
//				review.setReviewReport(rs.getString("review_report"));
//				review.setUserNo(rs.getInt("users_no"));
//				review.setMenuNo(rs.getInt("menu_no"));
//				
//				//리스트에 결과값 저장
//				reviewList.put(review, picture);
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			//DB객체 닫기
//			JDBCTemplate.close(rs);
//			JDBCTemplate.close(ps);
//		}
//		System.out.println("넘어가냐"+reviewList);
//		//최종 결과 반환
//		return reviewList;
//	}

	@Override
	public int selectCntReport() {
		
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		conn = JDBCTemplate.getConnection(); //DB연결
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM review";
		
		//최종 결과값
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 반환
			
			//조회결과 처리
			while( rs.next() ) {
				cnt = rs.getInt(1);
				System.out.println("이거되냐");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		System.out.println("개수"+cnt);
		return cnt;
	}
	
	@Override
	public void detailInsertReview(Review review, User user) {
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		
		
		String sql = "insert into review values (null, ?, sysdate, 0, 0, 'N', ?, ?)";
	
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1,review.getReviewContent());
			ps.setInt(2, user.getUserNo());
			ps.setInt(3, review.getMenuNo());
			
			ps.executeUpdate();

		
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(ps);
			}
	}

	@Override
	public void goodbtn(Review review,User user) {
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		
		
		String sql = "update review set review_good = review_good+1 where review_no = ?";
	
		
		
		
		try {
			ps = conn.prepareStatement(sql);
 			
			ps.setInt(1, review.getReviewNo());
			
			ps.executeUpdate();
			
			

		
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(ps);
			}
		
	}

	@Override
	public void badbtn(Review review,User user) {
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		
		
		String sql = "update review set review_bad = review_bad+1 where review_no = ?";
	
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, review.getReviewNo());
			
			ps.executeUpdate();

		
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(ps);
			}
		
		
	}

	  @Override
	   public void deleteReviewByMenuNoUserNo(int menuNo, int userno) {
	      Connection conn = JDBCTemplate.getConnection();
	      PreparedStatement ps = null;
	      
	      int result = -1;
	      
	      String sql = "DELETE REVIEW WHERE MENU_NO = ? AND USERS_NO = ?";
	      
	      try {
	         ps = conn.prepareStatement(sql);
	         ps.setInt(1, menuNo);
	         ps.setInt(2, userno);
	         
	         result = ps.executeUpdate();
	         
	         if(result>0) {
	            JDBCTemplate.commit(conn);
	         }else {
	            JDBCTemplate.commit(conn);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         JDBCTemplate.close(ps);
	      }
	   }

	
	

	@Override
	public int reviewVerif(Review review) {
		
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		String sql = "select count(review_content) as content_number from review where users_no = ? and menu_no = ? "; 
				sql += " group by review_content";
		
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, review.getUserNo());
			ps.setInt(2, review.getMenuNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				cnt = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		return cnt;
	}

	@Override
	public int good_bad_btn(Reviewverif reviewverif) {
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		String sql = "select good_bad from reviewverif where users_no = ? and review_no = ? " ; 
		
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reviewverif.getUserno());
			ps.setInt(2, reviewverif.getReviewno());
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				cnt = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		return cnt;
	}

	@Override
	public void goodbtn_insert(Reviewverif reviewverif) {
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		
		
		String sql = "insert into reviewverif values (?,?,?)";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, reviewverif.getUserno());
			ps.setInt(2, reviewverif.getReviewno());
			ps.setInt(3, 1);
			ps.executeUpdate();

			
		
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(ps);
			}
		
		
	}

	@Override
	public void badbtn_insert(Reviewverif reviewverif) {
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		
		
		String sql = "insert into reviewverif values (?,?,?)";
	
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, reviewverif.getUserno());
			ps.setInt(2, reviewverif.getReviewno());
			ps.setInt(3, 2);
			
			ps.executeUpdate();

		
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(ps);
			}
		
		
	}


}
