package web.service.face;

public interface MailauthService {

	/**
	 * 인증코드와 이메일 주소 저장
	 * 
	 * @param code
	 * @param fROM
	 */
	void insert(String code, String fROM);
	
	/**
	 * 이메일주소로 저장된 인증코드를 꺼내서 사용자가 입력한 코드와 일치하는지 확인
	 * 
	 * @param code
	 * @param email
	 * @return boolean
	 */
	boolean verifyCode(String code, String email);

}
