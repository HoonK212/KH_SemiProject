package web.dao.face;

import javax.servlet.http.HttpServletRequest;

import web.dto.Fran;
import web.dto.User;

public interface UserDao {
	
	
	/**
	 * ID&PW 일치하는 회원수 조회(로그인)
	 * 
	 * @param user
	 * @return int
	 */
	int selectUserCntByIdPw(User user);
	
	/**
	 * ID로 회원 조회
	 * 
	 * @param user
	 * @return User객체
	 */
	User selectUserByUserid(User user);
	
	/**
	 * 회원 가입
	 * 
	 * @param user
	 * @return int
	 */
	int insert(User user);

	/**
	 * 프랜차이즈번호 조회
	 * 
	 * @param parameter
	 * @return int
	 */
	int selectFranNoByFranName(String parameter);
	
	/**
	 * ID로 회원 삭제
	 * 
	 * @param userid
	 */
	void deleteUser(String userid);
	
	/**
	 * 이름과 메일로 회원 조회
	 * 
	 * @param name
	 * @param email
	 * @return User객체
	 */
	User selectUserByNameAndEmail(User user);
	
	
	/**
	 * 비밀번호 변경
	 * 
	 * @param newpw
	 * @param id
	 */
	void updatePw(String newpw, String id);

	/**
	 * 방문횟수 업데이트
	 * 
	 * @param user
	 */
	void updateUserCnt(User user);
	
	/**
	 * 로그인 날짜 업데이트
	 * 
	 * @param user
	 */
	void updateLoginDate(User user);
	

	

}
