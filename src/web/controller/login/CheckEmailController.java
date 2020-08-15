package web.controller.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.service.face.UserService;
import web.service.impl.UserServiceImpl;


@WebServlet("/checkemail")
public class CheckEmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		boolean result = userService.checkEmail(req);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json;charset=utf-8");
		
		out.println(result);
	
	}
	
}
