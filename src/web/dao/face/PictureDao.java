package web.dao.face;

import web.dto.Picture;

public interface PictureDao {
	
	/**
	 * 프로필사진 업로드
	 * 
	 * @param picture
	 */
	void insertFile(Picture picture);
	
	/**
	 * 업로드한 프로필사진 조회
	 * 
	 * @param userno
	 * @return Picture객체
	 */
	Picture selectPicture(int userno);
	
	
	/**
	 * 프로필사진 삭제
	 * 
	 * @param picture
	 */
	void deleteFile(Picture picture);


}
