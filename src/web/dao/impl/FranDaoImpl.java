package web.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.dao.face.FranDao;
import web.dbutil.JDBCTemplate;
import web.dto.Fran;

public class FranDaoImpl implements FranDao{

	Connection conn = JDBCTemplate.getConnection();
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	@Override
	public Fran selectGetFran(int franNo) {

		String sql = "SELECT * FROM FRAN WHERE FRAN_NO = ?";
		Fran result = new Fran();

		try {
			ps= conn.prepareStatement(sql);
			ps.setInt(1, franNo);
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				result.setFoodNo(rs.getInt("FOOD_NO"));
				result.setFranName(rs.getString("FRAN_NAME"));
				result.setFranNo(rs.getInt("FRAN_NO"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return result;
	}

	
	@Override
	public List<Fran> selectAll() {
		
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Fran> list = new ArrayList<>();
		Fran fran = null;
		
		String sql = "SELECT * FROM FRAN";
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				fran = new Fran();
				fran.setFranNo( rs.getInt("FRAN_NO") );
				fran.setFranName( rs.getString("FRAN_NAME") );
				fran.setFoodNo( rs.getInt("FOOD_NO") );
				
				list.add(fran);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		return list;
	}





}
