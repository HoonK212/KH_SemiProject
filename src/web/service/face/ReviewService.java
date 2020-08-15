package web.service.face;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import web.dto.Picture;
import web.dto.Review;
import web.util.Paging;

public interface ReviewService {
	
	
	/**
	 * 회원번호로 리뷰 작성수 조회
	 * 
	 * @param req
	 * @return int
	 */
	int countReviewByUserNo(HttpServletRequest req);
	
	/**
	 * 메뉴번호로 리뷰작성 수정
	 * 
	 * @param req
	 */
	void updateReview(HttpServletRequest req);
	
	//---------------------------------------------------------------------------
	
//	public Map<Review, Picture> evalReviewDetail(Paging paging, int userno, int menuno);
	public List<Map<String, Object>> evalReviewDetail(Paging paging, int userno, int menuno);
	
	public Paging getPagingReview(HttpServletRequest req);
	
	//---------------------------------------------------------------------------

	/**
	 * 한줄평에 대한 인설트
	 */
	public void ReviewInsert(HttpServletRequest req);
	
	/**
	 * 좋아요 싫어요 버튼 서비스
	 * 
	 */
	public void goodandbadbtn(HttpServletRequest req);


	/**
	 * 메뉴번호로 리뷰삭제
	 * 
	 * @param req
	 */
	void deleteReview(HttpServletRequest req);
	
	public int ReviewCount(HttpServletRequest req);
	
	public int good_badcnt(HttpServletRequest req);
	

}
