package web.service.face;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import web.dto.Report;
import web.dto.Review;

public interface ReportService {

	/**
	 * 신고내역 출력
	 * 
	 * @param req
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> getListOfReport(HttpServletRequest req);
	
	/**
	 * 신고내역 카운트
	 * 
	 * @param req
	 * @return int
	 */
	int countReportByUserNo(HttpServletRequest req);
	
	   /**
	    * 리뷰에대한 신고
	    * @param report
	    */
	   void reviewReport(Report report);
	

}
