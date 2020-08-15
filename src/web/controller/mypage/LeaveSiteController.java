package web.controller.mypage;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.Picture;
import web.service.face.PictureService;
import web.service.face.UserService;
import web.service.impl.PictureServiceImpl;
import web.service.impl.UserServiceImpl;


@WebServlet("/leavesite")
public class LeaveSiteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserServiceImpl();
	private PictureService pictureService = new PictureServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//----------프로필 섹션 정보 불러오기------------------------
		// 프로필 사진
		Picture picture = pictureService.info( req );
		
		// 회원 등급
		HttpSession session = req.getSession();
		String param = String.valueOf(session.getAttribute("usergrade"));
		int usergrade = Integer.parseInt(param);
		
		String grade = null;
		
		if( usergrade == 1) { grade = "일반회원"; }
		else if( usergrade == 2) { grade = "우수회원"; }
		else if( usergrade == 3) { grade = "VIP"; }
		else if( usergrade == 4) { grade = "VVIP"; }
		else { grade = null; }
		//-----------------------------------------------------------
		
		// 전달받은 데이터 request 컨텍스트에 저장하기
		req.setAttribute("picture", picture );
		req.setAttribute("grade", grade);
		
		req.getRequestDispatcher("/WEB-INF/views/mypage/leavesite.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		//1.사이트탈퇴
		userService.leaveSite(req);

		//2.세션 정보 삭제
		HttpSession session = req.getSession();
		session.invalidate();
		
		//2.메인페이지로 리다이렉트
		resp.sendRedirect("/main");
	
	}
	
	
}
