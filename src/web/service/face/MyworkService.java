package web.service.face;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import web.dto.Mywork;
import web.util.Paging;

public interface MyworkService {

	
	/**
	 * Mywork 목록조회
	 * 
	 * @param req
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String,Object>> getList(HttpServletRequest req);

	/**
	 * MenuNo로 Mywork 조회
	 * 
	 * @param req
	 * @return List<Map<String,Object>>
	 */
	public Map<String, Object> getMywork(HttpServletRequest req);

}
