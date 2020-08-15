package web.dao.face;

import java.util.List;

import java.util.Map;

import web.util.Paging;

public interface MyworkDao {
	
	/**
	 * Mywork 갯수 조회
	 * 
	 * @return int
	 */
//	public int selectCntAll();
	
	/**
	 * Mywork 목록 조회
	 * 
	 * @param int userno
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String,Object>> selectAll(int userno);
	
	/**
	 * MenoNo로 Mywork 조회
	 * 
	 * @param menuNo
	 * @return List<Map<String,Object>>
	 */
	public Map<String, Object> selectMywork(int userno, int menuNo);

}
