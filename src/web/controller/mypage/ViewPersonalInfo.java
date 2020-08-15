package web.controller.mypage;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.Fran;
import web.dto.Picture;
import web.dto.User;
import web.service.face.FranService;
import web.service.face.PictureService;
import web.service.face.UserService;
import web.service.impl.FranServiceImpl;
import web.service.impl.PictureServiceImpl;
import web.service.impl.UserServiceImpl;


@WebServlet("/view/info")
public class ViewPersonalInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PictureService pictureService = new PictureServiceImpl();
	private UserService userService = new UserServiceImpl();
	private FranService franService = new FranServiceImpl();
	
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
		
		
		//관심프랜차이즈 이름 불러오기 & 저장 (null이 아닌경우)
		String p = String.valueOf(session.getAttribute("fran"));
		if( session.getAttribute("fran") != null ) {
			Fran f = franService.getFran( Integer.parseInt(p) );
			req.setAttribute("fran2", f.getFranName());
		}
		
		// 전달받은 데이터 request 컨텍스트에 저장하기
		req.setAttribute("picture", picture );
		req.setAttribute("grade", grade);
		
		req.getRequestDispatcher("/WEB-INF/views/mypage/personalinfo.jsp").forward(req, resp);
		
		
	}
	
	
}
