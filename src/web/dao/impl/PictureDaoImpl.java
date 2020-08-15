package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import web.dao.face.PictureDao;
import web.dbutil.JDBCTemplate;
import web.dto.Picture;

public class PictureDaoImpl implements PictureDao {

	@Override
	public void insertFile(Picture picture) {
		// TODO Auto-generated method stub
		
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		int result = -1;
		
		
		String sql = "INSERT INTO PICTURE VALUES( null, ?, ?, ?, ?, ?, ?, ?, sysdate, ?)";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, picture.getPicName());
			ps.setString(2, picture.getPicOrigin());
			ps.setString(3, picture.getPicServer());
			ps.setInt(4, picture.getPicHor());
			ps.setInt(5, picture.getPicVer());
			ps.setString(6, picture.getPicExt());
			ps.setInt(7, picture.getPicSize());
			ps.setInt(8, picture.getUserNo());

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
	public Picture selectPicture(int userno) {

		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Picture picture = null;
		
		String sql = "SELECT * FROM PICTURE WHERE USERS_NO = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userno);
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				
				picture = new Picture();
				picture.setPicNo( rs.getInt(1) );
				picture.setPicName( rs.getString(2) );
				picture.setPicOrigin( rs.getString(3) );
				picture.setPicServer( rs.getString(4) );
				picture.setPicHor( rs.getInt(5) );
				picture.setPicVer( rs.getInt(6) );
				picture.setPicExt( rs.getString(7) );
				picture.setPicSize( rs.getInt(8) );
				picture.setPicDate( rs.getDate(9) );
				picture.setUserNo( rs.getInt(10) );
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		return picture;
	}

	@Override
	public void deleteFile(Picture picture) {

		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		
		int result = -1;
		
		String sql = "delete picture where users_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, picture.getUserNo());
			
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

}
