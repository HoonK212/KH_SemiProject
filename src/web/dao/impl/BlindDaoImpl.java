package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import web.dao.face.BlindDao;
import web.dbutil.JDBCTemplate;
import web.dto.Blind;

public class BlindDaoImpl implements BlindDao {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	@Override
	public void insertBlind(Blind blind , int menuno) {

		conn = JDBCTemplate.getConnection();
		
		String sql = "";
		sql += "INSERT INTO blind(BLIND_NO ,BLIND_RSN ,BLIND_DATE,BLIND_START,BLIND_YN,BLIND_NOTE,MENU_NO)";
		sql += " VALUES(blind_SEQ.nextval , ? , sysdate , ? ,'N' , ? , ? )";		
				
				
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, blind.getBlindRsn());
			java.sql.Date d = new java.sql.Date(blind.getBlindDate().getTime());
			ps.setDate(2, d);
			ps.setString(3, blind.getBlindNote());
			ps.setInt(4, menuno);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
		}
		
		
	}

	
	
	
	
	
	
	
}
