package web.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import web.dao.face.MyworkDao;
import web.dbutil.JDBCTemplate;
import web.dto.Menu;
import web.dto.Review;
import web.dto.Star;
import web.dto.User;
import web.util.Paging;

public class MyworkDaoImpl implements MyworkDao {

	@Override
	public List<Map<String,Object>> selectAll(int userno) {
		
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Map<String,Object>> listMywork = new ArrayList<>();

		String sql = "SELECT * FROM ( "; 
			 	sql += "	SELECT M.MENU_NAME, A.* FROM"; 
				sql += "	(    SELECT U.USERS_NICK, SR.* FROM USERS U,"; 
				sql += "		(SELECT S.MENU_NO, S.USERS_NO, S.STAR_SCORE, S.STAR_DATE, R.REVIEW_CONTENT, R.REVIEW_DATE"; 
				sql += "        	FROM STAR S LEFT OUTER JOIN REVIEW R ON S.MENU_NO = R.MENU_NO AND S.USERS_NO = R.USERS_NO ) SR"; 
				sql += "        WHERE U.USERS_NO = SR.USERS_NO  )A, MENU M"; 
				sql += "        WHERE M.MENU_NO = A.MENU_NO ) WHERE USERS_NO = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,userno);
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				
				Menu menu = new Menu();
				menu.setMenuName(rs.getString("MENU_NAME"));
				menu.setMenuNo(rs.getInt("MENU_NO"));
				
				User user = new User();
				user.setUserNick(rs.getString("USERS_NICK"));
				user.setUserNo(rs.getInt("USERS_NO"));
				
				Star star = new Star();
				star.setMenuNo(rs.getInt("MENU_NO"));
				star.setUserNo(rs.getInt("USERS_NO"));
				star.setStarScore(rs.getDouble("STAR_SCORE"));
				star.setStarDate(rs.getDate("STAR_DATE"));
				
				Review review = new Review();
				review.setMenuNo(rs.getInt("MENU_NO"));
				review.setUserNo(rs.getInt("USERS_NO"));
				review.setReviewContent(rs.getString("REVIEW_CONTENT"));
				review.setReviewDate(rs.getDate("REVIEW_DATE"));
				
				Map<String, Object> mapMywork = new HashMap<>();
				mapMywork.put("menu", menu);
				mapMywork.put("user", user);
				mapMywork.put("star", star);
				mapMywork.put("review", review);
				
				listMywork.add(mapMywork);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		
		return listMywork;
	}



	@Override
	public Map<String, Object> selectMywork(int userno, int menuNo) {

		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Map<String, Object> mapMywork = new HashMap<>();
		
		String sql = "SELECT * FROM ( "; 
	 	sql += "	SELECT M.MENU_NAME, A.* FROM"; 
		sql += "	(    SELECT U.USERS_NICK, SR.* FROM USERS U,"; 
		sql += "		(SELECT S.MENU_NO, S.USERS_NO, S.STAR_SCORE, S.STAR_DATE, R.REVIEW_CONTENT, R.REVIEW_DATE"; 
		sql += "        	FROM STAR S LEFT OUTER JOIN REVIEW R ON S.MENU_NO = R.MENU_NO AND S.USERS_NO = R.USERS_NO ) SR"; 
		sql += "        WHERE U.USERS_NO = SR.USERS_NO  )A, MENU M"; 
		sql += "        WHERE M.MENU_NO = A.MENU_NO ) WHERE USERS_NO = ? AND MENU_NO = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userno);
			ps.setInt(2, menuNo);
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				Menu menu = new Menu();
				menu.setMenuName(rs.getString("MENU_NAME"));
				menu.setMenuNo(rs.getInt("MENU_NO"));
				
				User user = new User();
				user.setUserNick(rs.getString("USERS_NICK"));
				user.setUserNo(rs.getInt("USERS_NO"));
				
				Star star = new Star();
				star.setMenuNo(rs.getInt("MENU_NO"));
				star.setUserNo(rs.getInt("USERS_NO"));
				star.setStarScore(rs.getDouble("STAR_SCORE"));
				star.setStarDate(rs.getDate("STAR_DATE"));
				
				Review review = new Review();
				review.setMenuNo(rs.getInt("MENU_NO"));
				review.setUserNo(rs.getInt("USERS_NO"));
				review.setReviewContent(rs.getString("REVIEW_CONTENT"));
				review.setReviewDate(rs.getDate("REVIEW_DATE"));
				
				mapMywork.put("menu", menu);
				mapMywork.put("user", user);
				mapMywork.put("star", star);
				mapMywork.put("review", review);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		return mapMywork;
	}

}
