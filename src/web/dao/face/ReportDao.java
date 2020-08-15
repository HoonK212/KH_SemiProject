package web.dao.face;

import java.util.List;
import java.util.Map;

import web.dto.Report;
import web.dto.Review;

public interface ReportDao {
	
	/**
	 * 신고자번호로 신고내역 조회
	 * 
	 * @param rptUsers
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> selectListOfReport(int rptUsers);
	
	/**
	 * 신고자번호로 신고내역 카운트
	 * 
	 * @param rptUsers
	 * @return int
	 */
	int selectCntReportByUserNo(int rptUsers);
	

	void insertReport(Report report);
	
}
