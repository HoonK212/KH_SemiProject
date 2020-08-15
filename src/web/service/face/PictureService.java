package web.service.face;

import javax.servlet.http.HttpServletRequest;

import web.dto.Picture;

public interface PictureService {
	
	/**
	 * 프로필사진 업로드
	 * 
	 * @param req
	 */
	void uploadPicture(HttpServletRequest req);
	
	/**
	 * 프로필사진 정보얻기
	 * 
	 * @param req
	 * @return Picture객체
	 */
	Picture info(HttpServletRequest req);
	


}
