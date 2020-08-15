package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import web.dao.face.UserDao;
import web.dbutil.JDBCTemplate;
import web.dto.User;

public class UserDaoImpl implements UserDao {

	@Override
	public User selectUserByUserid(User user) {
		
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		User result = new User();
		
		String sql = "SELECT * FROM USERS WHERE USERS_ID = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserId());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				result.setUserNo( rs.getInt("USERS_NO"));	
				result.setUserId( rs.getString("USERS_ID")); 
				result.setUserPw( rs.getString("USERS_PW"));
				result.setUserName( rs.getString("USERS_NAME"));
				result.setUserNick( rs.getString("USERS_NICK"));
				result.setUserBirth( rs.getDate("USERS_BIRTH"));
				result.setUserGender( rs.getString("USERS_GENDER"));
				result.setUserEmail( rs.getString("USERS_EMAIL"));
				result.setUserTel( rs.getInt("USERS_TEL"));
				result.setUserDate( rs.getDate("USERS_DATE"));
				result.setUserCnt( rs.getInt("USERS_CNT"));
				result.setUserAuth( rs.getInt("USERS_AUTH"));
				result.setUserGrade( rs.getInt("USERS_GRADE"));
				result.setUserReport( rs.getDate("USERS_REPORT"));
				result.setFranNo( rs.getInt("FRAN_NO"));
				result.setUserLoginDate(rs.getDate("USERS_LOGIN_DATE"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		
		return result;
	}

	@Override
	public int selectUserCntByIdPw(User user) {
	
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int cnt = -1;
		
		String sql = "SELECT COUNT(*) FROM USERS WHERE USERS_ID = ? AND USERS_PW = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserId());
			ps.setString(2, user.getUserPw());
			
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
	public int insert(User user) {

		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		
		String sql = null;
		
		if(user.getFranNo()!=0) {
			sql = "INSERT INTO USERS VALUES( users_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, ?, ?, ?, ?, sysdate, null)";
		} else {
			sql = "INSERT INTO USERS VALUES( users_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, ?, ?, ?, null, sysdate, null)";
		}
		
		int result = -1;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserId());
			ps.setString(2, user.getUserPw());
			ps.setString(3, user.getUserName());
			ps.setString(4, user.getUserNick());
			ps.setDate(5, user.getUserBirth());
			ps.setString(6, user.getUserGender());
			ps.setString(7, user.getUserEmail());
			ps.setInt(8, user.getUserTel());
			ps.setInt(9, user.getUserCnt());
			ps.setInt(10, user.getUserAuth());
			ps.setInt(11, user.getUserGrade());
			if(user.getFranNo()!=0) {
				ps.setInt(12, user.getFranNo());
			}
			
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
		return result;
	}

	@Override
	public int selectFranNoByFranName(String franName) {
		
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int franno = 0;
		
		String sql = "SELECT FRAN_NO FROM FRAN WHERE FRAN_NAME = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, franName);
		
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				franno = rs.getInt(1);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		return franno;
	}

	@Override
	public void deleteUser(String userid) {
		
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		
		int result = -1;
		
		String sql = "DELETE USERS WHERE USERS_ID = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
			
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
	public User selectUserByNameAndEmail(User user) {
		
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		User result = new User();
		
		String sql = "SELECT * FROM USERS WHERE USERS_NAME = ? AND USERS_EMAIL = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserEmail());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				result.setUserNo( rs.getInt("USERS_NO"));	
				result.setUserId( rs.getString("USERS_ID")); 
				result.setUserPw( rs.getString("USERS_PW"));
				result.setUserName( rs.getString("USERS_NAME"));
				result.setUserNick( rs.getString("USERS_NICK"));
				result.setUserBirth( rs.getDate("USERS_BIRTH"));
				result.setUserGender( rs.getString("USERS_GENDER"));
				result.setUserEmail( rs.getString("USERS_EMAIL"));
				result.setUserTel( rs.getInt("USERS_TEL"));
				result.setUserDate( rs.getDate("USERS_DATE"));
				result.setUserCnt( rs.getInt("USERS_CNT"));
				result.setUserAuth( rs.getInt("USERS_AUTH"));
				result.setUserGrade( rs.getInt("USERS_GRADE"));
				result.setFranNo( rs.getInt("FRAN_NO"));
				result.setUserLoginDate( rs.getDate("USERS_LOGIN_DATE") );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		
		return result;
	}


	@Override
	public void updatePw(String newpw, String id) {
		
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;

		int result = -1;
		
		String sql = "UPDATE USERS SET USERS_PW = ? where users_id = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, newpw);
			ps.setString(2, id);
			
			result = ps.executeUpdate();
			
			if( result > 0) {
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
	public void updateUserCnt(User user) {
		
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		
		int result = -1;
		
		String sql = "UPDATE USERS SET USERS_CNT = USERS_CNT + 1 WHERE USERS_NO = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getUserNo());
			
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
	public void updateLoginDate(User user) {
		
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		
		int result = -1;
		
		String sql = "UPDATE USERS SET USERS_LOGIN_DATE = SYSDATE WHERE USERS_NO = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getUserNo());
			
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












