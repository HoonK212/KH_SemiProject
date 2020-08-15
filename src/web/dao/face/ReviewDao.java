package web.dao.face;

import java.util.List;
import java.util.Map;

import web.dto.Menu;
import web.dto.Picture;
import web.dto.Review;
import web.dto.Reviewverif;
import web.dto.User;
import web.util.Paging;

public interface ReviewDao {
	
	/**
	 * 회원번호로 리뷰 작성수 조회
	 * 
	 * @param userno
	 * @return int
	 */
	int selectCntReviewByUserNo(int userno);

	/**
	 * 회원번호와 메뉴번호로 리뷰 조회하여 수정
	 * 
	 * @param menuNo
	 * @param updateContent
	 */
	void updateReviewByMenuNoUserNo(String updateContent, int userno, int menuNo);

	
	//---------------------------------------------------------------------------
	
//	public Map<Review, Picture> selectReview(Paging paging, int userno, int menuno);
	public List<Map<String, Object>> selectReview(Paging paging, int userno, int menuno);
	
	public int selectCntReport();
	//---------------------------------------------------------------------------

	/**
	 *한줄평 등록기능 
	 * @param review 해당하는 리뷰에 값 추가
	 * @param user 해당하는 유저의 넘버 가져오기
	 */
	public void detailInsertReview(Review review, User user);
	/**
	 * 
	 */
	
	public void goodbtn(Review review,User user);
	
	public void badbtn(Review review,User user);
	/**
	 * 회원번호와 메뉴번호로 별점 삭제
	 * 
	 * @param menuNo
	 * @param userno
	 */
	void deleteReviewByMenuNoUserNo(int menuNo, int userno);
	
	public int reviewVerif(Review review);
	
	public int good_bad_btn(Reviewverif reviewverif);
	
	public void goodbtn_insert(Reviewverif reviewverif);
	public void badbtn_insert(Reviewverif reviewverif);
	
}

