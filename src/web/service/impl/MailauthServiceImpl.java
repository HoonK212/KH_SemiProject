package web.service.impl;

import web.dao.face.MailauthDao;
import web.dao.impl.MailauthDaoImpl;
import web.service.face.MailauthService;

public class MailauthServiceImpl implements MailauthService {
	
	MailauthDao mailauthDao = new MailauthDaoImpl();
	
	@Override
	public void insert(String code, String TO) {
		
		mailauthDao.insert(code, TO);
		
	}

	@Override
	public boolean verifyCode(String code, String email) {
		
		//인증코드 꺼내기
		String random = mailauthDao.selectRandom(email);
		
		//사용자가 입력한 코드와 비교하기
		if(code.equals(random)) {
			return true;
		}else {
			return false;
		}
	}

}
