package web.service.face;


import javax.servlet.http.HttpServletRequest;

import web.dto.Star;


public interface StarService {
	
	
	/**
	 * 회원번호로 별점 작성수 조회
	 * 
	 * @param req
	 * @return int
	 */
	int countStarByUserNo(HttpServletRequest req);
	
	/**
	 * 별점 수정
	 * 
	 * @param req
	 */
	void updateStar(HttpServletRequest req);
	
	/**
	 * 평점에 대한 평균값 가져오기
	 */
	public Double AvgStar(HttpServletRequest req);
	
	/**
	 * 별점에 대한 인설트
	 */
	public void StarInsert(HttpServletRequest req);

	/**
	 * 메뉴번호로 별점 삭제
	 * 
	 * @param req
	 */
	void deleteStar(HttpServletRequest req);

}
