package web.dao.face;

public interface MailauthDao {
	
	/**
	 * 생성한 인증코드와 이메일주소 삽입
	 * 
	 * @param code
	 * @param fROM
	 */
	void insert(String code, String fROM);

	/**
	 * 이메일주소로 저장된 인증코드 조회
	 * 
	 * @param email
	 * @return String - 인증 코드
	 */
	String selectRandom(String email);

}
