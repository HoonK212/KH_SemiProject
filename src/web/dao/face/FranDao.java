package web.dao.face;

import java.util.List;

import web.dto.Fran;

public interface FranDao {

	/**
	 * 로그인된 회원의 프랜넘버로 프랜객체가져오기
	 * @param franNo
	 * @return
	 */
	Fran selectGetFran(int franNo);

	/**
	 * 프랜차이즈 전체 리스트 가져오기
	 * 
	 * @return List<Fran>
	 */
	List<Fran> selectAll();

	
}
