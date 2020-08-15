package web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import web.dao.face.ReportDao;
import web.dao.impl.ReportDaoImpl;
import web.dto.Report;
import web.dto.Review;
import web.service.face.ReportService;

public class ReportServiceImpl implements ReportService {

	private ReportDao reportDao = new ReportDaoImpl();
	
	
	@Override
	public List<Map<String, Object>> getListOfReport(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		String param = String.valueOf(session.getAttribute("userno"));
		int rptUsers = Integer.parseInt(param);
		
		List<Map<String, Object>> mapMyreport = new ArrayList<>();
		
		mapMyreport = reportDao.selectListOfReport(rptUsers);
		
		
		return mapMyreport;
	}

	@Override
	   public void reviewReport(Report report) {
	      reportDao.insertReport(report);
	   }

	@Override
	public int countReportByUserNo(HttpServletRequest req) {

		HttpSession session = req.getSession();
		String param = String.valueOf(session.getAttribute("userno"));
		int rptUsers = Integer.parseInt(param);
		
		int cnt = reportDao.selectCntReportByUserNo(rptUsers);
		
		return cnt;
	}
	

}
