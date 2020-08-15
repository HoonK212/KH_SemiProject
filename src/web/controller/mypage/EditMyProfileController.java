package web.controller.mypage;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.Picture;
import web.dto.User;
import web.service.face.PictureService;
import web.service.face.ReportService;
import web.service.face.ReviewService;
import web.service.face.StarService;
import web.service.face.UserService;
import web.service.impl.PictureServiceImpl;
import web.service.impl.ReportServiceImpl;
import web.service.impl.ReviewServiceImpl;
import web.service.impl.StarServiceImpl;
import web.service.impl.UserServiceImpl;


@WebServlet("/editProfile")
public class EditMyProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PictureService pictureService = new PictureServiceImpl();
	private StarService starService = new StarServiceImpl();
	private ReviewService reviewService = new ReviewServiceImpl();
	private UserService userService = new UserServiceImpl();
	private ReportService reportService = new ReportServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		Picture picture = new Picture();
		
		// 1.프로필 사진정보
		picture = pictureService.info( req );
		
		// 2.회원 등급 (int -> String명)
		String param = String.valueOf(session.getAttribute("usergrade"));
		int usergrade = Integer.parseInt(param);
		
		String grade = null;
		
		if( usergrade == 1) { grade = "일반회원"; }
		else if( usergrade == 2) { grade = "우수회원"; }
		else if( usergrade == 3) { grade = "VIP"; }
		else if( usergrade == 4) { grade = "VVIP"; }
		else { grade = null; }
		
		// 3.별점작성수, 리뷰작성수, 신고내역수
		int cntStar = starService.countStarByUserNo(req);
		int cntReview = reviewService.countReviewByUserNo(req);
		int cntReport = reportService.countReportByUserNo(req);
		
		// 4.방문횟수
		User user = new User();
		String userid = String.valueOf(session.getAttribute("userid"));
		user.setUserId(userid);
		user = userService.info(user);
		int users_cnt = user.getUserCnt();
		
		// 5.request에 저장
		req.setAttribute("picture", picture );
		req.setAttribute("grade", grade);
		req.setAttribute("cntstar", cntStar);
		req.setAttribute("cntreview", cntReview);
		req.setAttribute("cntreport", cntReport);
		req.setAttribute("users_cnt", users_cnt);
		
		// 6.포워딩
		req.getRequestDispatcher("/WEB-INF/views/mypage/editProfile.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 인코딩 설정
		req.setCharacterEncoding("utf-8");
		
		// 프로필 사진 업로드 
		// 처리할 전달인자 : 프로필사진, 회원번호
		pictureService.uploadPicture(req);
		
		// 2.본페이지로 리다이렉트
		resp.sendRedirect("/editProfile");
		
		
	}
	
}
