package web.service.face;

import javax.servlet.http.HttpServletRequest;

import web.dto.Fran;
import web.dto.User;

public interface UserService {
	
	/**
	 * 로그인 정보 얻기
	 * 
	 * @param req
	 * @return User객체
	 */
	User getLoginUser(HttpServletRequest req);
	
	/**
	 * 로그인 인증
	 * 
	 * @param user
	 * @return boolean
	 */
	boolean login(User user);
	
	/**
	 * 회원정보 얻기
	 * 
	 * @param user
	 * @return User객체
	 */
	User info(User user);
	
	/**
	 * 아이디 중복확인
	 * 
	 * @param req
	 * @return User객체
	 */
	User doublecheckId(HttpServletRequest req);
	
	/**
	 * 일반인 회원가입 
	 * 
	 * @param req
	 * @return int
	 */
	int joinUser(HttpServletRequest req);
	
	/**
	 * 프랜차이즈 회원가입
	 * 
	 * @param req
	 * @return int
	 */
	int joinFran(HttpServletRequest req);
	
	/**
	 * 프랜차이즈관리자 회원가입 key 인증
	 * 
	 * @param req
	 * @return boolean
	 */
	boolean keyVerify(HttpServletRequest req);
	
	/**
	 * 사이트 탈퇴 = User테이블에서 회원 삭제
	 * 
	 * @param req
	 */
	void leaveSite(HttpServletRequest req);
	
	/**
	 * 아이디 조회 (아이디 잃어버렸을시)
	 * 이름과 메일주소로 조회
	 * 
	 * @param req
	 * @return User객체
	 */
	User searchId(HttpServletRequest req);

	/**
	 * 아이디 확인 (비밀번호 잃어버렸을시)
	 * 	
	 * @param req
	 * @return User객체
	 */
	User checkId(HttpServletRequest req);
	
	/**
	 * 비밀번호 변경
	 * 
	 * @param req
	 */
	void changePw(HttpServletRequest req);
	
	/**
	 * 아이디에 해당되는 이메일 조회
	 * 
	 * @param req
	 */
	boolean checkEmail(HttpServletRequest req);

	/**
	 * 방문횟수 업데이트
	 * @param user
	 * 
	 */
	void updateUserCnt(User user);
	
	/**
	 * 마이페이지 비밀번호 변경
	 * 
	 * @param req
	 */
	void changePwInMypage(HttpServletRequest req);
	

}
