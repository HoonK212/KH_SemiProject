package web.controller.join;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Fran;
import web.service.face.FranService;
import web.service.face.UserService;
import web.service.impl.FranServiceImpl;
import web.service.impl.UserServiceImpl;


@WebServlet("/userjoin")
public class UserJoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserServiceImpl();
	private FranService franService = new FranServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//프랜차이즈 정보 가져오기
		List<Fran> listFran = franService.getFranname();
		
		//저장
		req.setAttribute("listFran", listFran);
		
		req.getRequestDispatcher("/WEB-INF/views/join/join_user.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//회원가입
		int result = userService.joinUser(req);
		
		//회원가입성공
		if( result > 0) {
			resp.sendRedirect("/main");
		}
	}
}
