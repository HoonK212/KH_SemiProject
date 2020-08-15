package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import web.dao.face.StarDao;
import web.dbutil.JDBCTemplate;
import web.dto.Star;
import web.dto.User;

public class StarDaoImpl implements StarDao {


	@Override
	public int selectCntStarByUserNo(int userno) {

		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int cnt = 0;
		
		String sql = "SELECT COUNT(*) FROM STAR WHERE USERS_NO = ?";
		
		
		
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
	public void updateStarByUserNoMenuNo(double updatescore, int userno, int menuNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		
		int result = -1;
		
		String sql = "UPDATE STAR SET STAR_SCORE = ?";
		sql += "	WHERE USERS_NO = ? AND MENU_NO = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, updatescore);
			ps.setInt(2, userno);
			ps.setInt(3, menuNo);
			
			result = ps.executeUpdate();
			
			if(result>0) {
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
	public Double AvgStarSelect(Star star) {

		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		String sql = "select m.menu_no,avg(s.star_score) as staravg from menu m , star s where m.menu_no = s.menu_no and m.menu_no = ?"; 
			sql += " 	group by m.menu_no";
		
		double avg = 0.1;
		
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, star.getMenuNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				
			avg = Math.round(rs.getDouble("staravg")*100d)/100d;
			
			
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		System.out.println("여기는 avgstar 디!에이!오! : " + avg);
		return avg;
	}

	@Override
	public void detailInsertStar(Star star ,User user) {
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		
		
		String sql = "insert into star values (null, ?, sysdate , ?, ?)";
	
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, star.getStarScore());
			ps.setInt(2, user.getUserNo());
			ps.setInt(3, star.getMenuNo());
			
			ps.executeUpdate();

		
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(ps);
			}
	}

	@Override
	public void deleteStarByUserNoMenuNo(int menuNo, int userno) {
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		
		int result = -1;
		
		String sql = "DELETE STAR WHERE MENU_NO = ? AND USERS_NO = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, menuNo);
			ps.setInt(2, userno);
			
			result = ps.executeUpdate();
			
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.commit(conn);
		}
		
	}

	
		
		
	}




