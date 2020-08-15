package web.service.face;

import java.util.List;

import web.dto.Fran;

public interface FranService {

	/**
	 * 로그인된 회원의 프랜넘버로 프랜객체가져오기
	 * @param franNo
	 * @return
	 */
	Fran getFran(int franNo);

	/**
	 * 프랜차이즈 전체 정보 가져오기
	 * 
	 * @return List<Fran>
	 */
	List<Fran> getFranname();

	

	

}
