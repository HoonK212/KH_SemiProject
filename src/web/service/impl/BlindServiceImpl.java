package web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import web.dao.face.BlindDao;
import web.dao.impl.BlindDaoImpl;
import web.dto.Blind;
import web.service.face.BlindService;

public class BlindServiceImpl implements BlindService {

	private BlindDao blindDao = new BlindDaoImpl();
	
	@Override
	public Blind getParam(HttpServletRequest req) {

		Blind blind = new Blind();
		
		blind.setBlindNote(req.getParameter("blind-message"));
		blind.setBlindRsn(req.getParameter("applyBlindRsn"));
		
		String param = req.getParameter("applyblindDate");
		if(param!=null && !"".equals(param)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				blind.setBlindDate(format.parse(param));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		
		return blind;
	}
	
	@Override
	public void applyBlind(Blind blind, int menuno) {
		
		blindDao.insertBlind(blind, menuno);
		
	}


}
