package web.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import web.dao.face.MyworkDao;
import web.dao.impl.MyworkDaoImpl;
import web.dto.Mywork;
import web.service.face.MyworkService;

public class MyworkServiceImpl implements MyworkService {
	
	private MyworkDao myworkDao = new MyworkDaoImpl();
	


	@Override
	public List<Map<String,Object>> getList(HttpServletRequest req) {

		HttpSession session = req.getSession();
		String param = String.valueOf(session.getAttribute("userno"));
		int userno = Integer.parseInt(param);
		
		return myworkDao.selectAll(userno);
	}

	@Override
	public Map<String, Object> getMywork(HttpServletRequest req) {
		
		String param01 = req.getParameter("menuNo");
		int menuNo = Integer.parseInt(param01);
		
		HttpSession session = req.getSession();
		String param02 = String.valueOf(session.getAttribute("userno"));
		int userno = Integer.parseInt(param02);
		
		return myworkDao.selectMywork(userno, menuNo);
	}

}
