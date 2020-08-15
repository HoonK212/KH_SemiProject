package web.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import web.dao.face.ReviewDao;
import web.dao.impl.ReviewDaoImpl;
import web.dto.Picture;
import web.dto.Review;
import web.dto.Reviewverif;
import web.dto.User;
import web.service.face.ReviewService;
import web.util.Paging;

public class ReviewServiceImpl implements ReviewService {

	private ReviewDao reviewDao = new ReviewDaoImpl();

	@Override
	public int countReviewByUserNo(HttpServletRequest req) {

		HttpSession session = req.getSession();
		String param = String.valueOf(session.getAttribute("userno"));
		int userno = Integer.parseInt(param);

		int cnt = reviewDao.selectCntReviewByUserNo(userno);

		return cnt;
	}

	   @Override
	   public void updateReview(HttpServletRequest req) {
	      
	      //메뉴번호 전달받기
	      String param = req.getParameter("menuno");
	      int menuNo = Integer.parseInt(param);
	      
	      //회원번호 꺼내기(세션)
	      HttpSession session = req.getSession();
	      String p = String.valueOf(session.getAttribute("userno"));
	      int userno = Integer.parseInt(p);
	      
	      //수정된 리뷰컨텐츠 전달받기
	      String updateContent = req.getParameter("review");
	      
	      reviewDao.updateReviewByMenuNoUserNo(updateContent, userno, menuNo);
	      
	   }

	// ---------------------------------------------------------------------------

//	@Override
//	public Map<Review, Picture> evalReviewDetail(Paging paging, int menuno) {
//
//		return reviewDao.selectReview(paging, menuno);
//	}
	@Override
	public List<Map<String, Object>> evalReviewDetail(Paging paging, int userno, int menuno) {
		
		return reviewDao.selectReview(paging, userno, menuno);
	}

	@Override
	public Paging getPagingReview(HttpServletRequest req) {
		// 전달파라미터 curPage를 파싱한다

		String param = req.getParameter("curPage");
		int curPage = 0;
		if (param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}
		System.out.println(curPage);

		// 검색어
		String search = (String) req.getParameter("search");
		// Board 테이블의 총 게시글 수를 조회한다
		int totalCount = reviewDao.selectCntReport();

		// Paging 객체 생성 - 현재 페이지(curPage), 총 게시글 수(totalCount) 활용
		Paging paging = new Paging(totalCount, curPage);
		paging.setSearch(search);

		System.out.println(paging);
		// Paging 객체 반환
		return paging;
	}

	// ---------------------------------------------------------------------------

	@Override
	public void ReviewInsert(HttpServletRequest req) {
		Review review = new Review();
		// 유저넘버값 추출 하는 세션
		HttpSession session = req.getSession();
		// 유저아이디값을 넣어줌
		User user = new User();
		user.setUserNo((int) session.getAttribute("userno"));

		// 해당하는 한줄평 내용을 저장
		review.setReviewContent(req.getParameter("review"));

		// 해당하는 메뉴의 메뉴넘버를 review.menuno에 넣어주고 dao로 전달
		review.setMenuNo(Integer.parseInt(req.getParameter("menuno")));

		// 만약 review내용이 null이 아니라면 실행
		if (req.getParameter("review") != null && !req.getParameter("review").equals("")) {

			reviewDao.detailInsertReview(review, user);

		}

	}

	@Override
	public void goodandbadbtn(HttpServletRequest req) {
		System.out.println("씨빨 ㅉ또ㅉ띨다라고 되라고 개샊끼야");
		
		Review review = new Review();
		Reviewverif reviewverif = new Reviewverif();
		
		// 유저넘버값 추출 하는 세션
		HttpSession session = req.getSession();
		
		// 유저아이디값을 넣어줌
		User user = new User();
		user.setUserNo((int) session.getAttribute("userno"));
		
		// 넘버값 입력
		review.setReviewNo(Integer.parseInt(req.getParameter("reviewno")));
	
		//좋아요 싫어요 했나 안했나 검증
		reviewverif.setUserno((int) session.getAttribute("userno"));
		reviewverif.setReviewno((Integer.parseInt(req.getParameter("reviewno"))));
		
		
		String goodbad = req.getParameter("goodbad");
		System.out.println("goodbad ::::: " + goodbad);
		
		//좋아요 싫어요 했나 안했나 검증
		if (goodbad.equals("good")) {
			reviewDao.goodbtn(review, user);
			reviewDao.goodbtn_insert(reviewverif);
		} else if (goodbad.equals("bad")) {
			System.out.println("여기는 배드 컨트롤러" + review.getReviewNo());
			reviewDao.badbtn(review, user);
			reviewDao.badbtn_insert(reviewverif);
		}

	}

	@Override
	public void deleteReview(HttpServletRequest req) {
		// 메뉴번호 전달받기
		String param = req.getParameter("menuNo");
		int menuNo = Integer.parseInt(param);

		// 회원번호 꺼내기(세션)
		HttpSession session = req.getSession();
		String p = String.valueOf(session.getAttribute("userno"));
		int userno = Integer.parseInt(p);

		reviewDao.deleteReviewByMenuNoUserNo(menuNo, userno);
	}

	// 리뷰수 검증하는 코드
	@Override
	public int ReviewCount(HttpServletRequest req) {

		HttpSession session = req.getSession();

		Review review = new Review();

		review.setUserNo((int) session.getAttribute("userno"));
		review.setMenuNo(Integer.parseInt(req.getParameter("menuno")));

		return reviewDao.reviewVerif(review);
	}

	@Override
	public int good_badcnt(HttpServletRequest req) {
		HttpSession session = req.getSession();

		Reviewverif review = new Reviewverif();
		review.setUserno((int)session.getAttribute("userno"));
		
		
		review.setReviewno(Integer.parseInt(req.getParameter("reviewno")));
		
		
		return reviewDao.good_bad_btn(review);
	}



}
