package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dto.Image;
import web.dto.Menu;

public interface ImageService {

	Image info(HttpServletRequest req , int menunoByImage);

	List<Image> getAllImage();

	/**
	 * 메뉴넘버로 메뉴정보 전체 가져오기
	 * @param foodByMenuNo
	 * @return
	 */
	Image getImageByMenuno(int foodByMenuNo);

	
	
	/**
	 *  잠깐 테스트중이다
	 * @param menu
	 * @param foodname
	 * @return
	 */
	List<Image> getImageByFran(List<Menu> menu, String foodname);

	
	/**
	 * 필터건 음식들(테스트중)
	 * @param detailfilter
	 * @param foodName
	 * @return
	 */
	List<Image> getMenuByfilter(String detailfilter, String foodName);

}
