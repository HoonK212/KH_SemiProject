package web.service.impl;

import java.util.List;

import web.dao.face.FranDao;
import web.dao.impl.FranDaoImpl;
import web.dto.Fran;
import web.service.face.FranService;

public class FranServiceImpl implements FranService {

	private FranDao franDao = new FranDaoImpl();

	@Override
	public Fran getFran(int franNo) {
		return franDao.selectGetFran(franNo);
	}

	@Override
	public List<Fran> getFranname() {
		return franDao.selectAll();
	}

	
	

}
