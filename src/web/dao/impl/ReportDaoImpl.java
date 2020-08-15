package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import web.dao.face.ReportDao;
import web.dbutil.JDBCTemplate;
import web.dto.Report;
import web.dto.Review;
import web.dto.User;

public class ReportDaoImpl implements ReportDao {

	@Override
	public List<Map<String, Object>> selectListOfReport(int rptUsers) {
		
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Map<String, Object>> list = new ArrayList<>();
		
		String sql = "SELECT * FROM (SELECT A.*, U.USERS_NICK FROM (SELECT P.*, R.REVIEW_CONTENT from REPORT P LEFT OUTER JOIN REVIEW R";
		sql += "	ON P.REVIEW_NO = R.REVIEW_NO) A LEFT OUTER JOIN USERS U ON A.RPT_TARGET = U.USERS_NO) WHERE RPT_USERS = ?";

		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rptUsers);
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				
				Report report = new Report();
				report.setReviewNo( rs.getInt("REVIEW_NO") );
				report.setRptDate( rs.getDate("RPT_DATE") );
				report.setRptDetail( rs.getString("RPT_DETAIL") );
				report.setRptManage( rs.getInt("RPT_MANAGE") );
				report.setRptNo( rs.getInt("RPT_NO") );
				report.setRptNote( rs.getString("RPT_NOTE") );
				report.setRptRsn( rs.getInt("RPT_RSN") );
				report.setRptTarget( rs.getInt("RPT_TARGET") );
				report.setRptUser( rs.getInt("RPT_USERS") );
				report.setRptYn( rs.getString("RPT_YN") );
				
				Review review = new Review();
				review.setReviewNo( rs.getInt("REVIEW_NO") );
				review.setReviewContent( rs.getString("REVIEW_CONTENT") );
				
				User user = new User();
				user.setUserNick( rs.getString("USERS_NICK") );
				
				Map<String, Object> mapReport = new HashMap<>();
				mapReport.put("report", report);
				mapReport.put("review", review);
				mapReport.put("user", user);
				
				list.add(mapReport);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		return list;
	}

	@Override
	public int selectCntReportByUserNo(int rptUsers) {
		
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int cnt = 0;
		
		String sql = "SELECT COUNT(*) FROM REPORT WHERE RPT_USERS = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rptUsers);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
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
    public void insertReport(Report report) {
        Connection conn = JDBCTemplate.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "INSERT INTO REPORT(RPT_RSN, RPT_DETAIL , RPT_DATE , RPT_YN, RPT_USERS,  RPT_TARGET ,REVIEW_NO)";
        sql+= " VALUES(? , ? , sysdate , 'N' , ? ,?,?)";
        
        try {
            ps = conn.prepareStatement(sql);
            
            ps.setInt(1, report.getRptRsn());
            ps.setString(2, report.getRptDetail());
            ps.setInt(3,report.getRptUser());
            ps.setInt(4, report.getRptTarget());
            ps.setInt(5, report.getReviewNo());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rs);
        }
        
    }

}
