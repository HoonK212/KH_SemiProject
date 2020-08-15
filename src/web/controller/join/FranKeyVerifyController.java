package web.controller.join;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web.dto.User;
import web.service.face.UserService;
import web.service.impl.UserServiceImpl;


@WebServlet("/frankeyverify")
public class FranKeyVerifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		
		resp.setContentType("application/json;charset=utf-8");
		//Key인증
		boolean result = userService.keyVerify( req );
		System.out.println(result);
		
		if( result ) {
			out.println("{\"result\":true}");
		} else {
			out.println("{\"result\":false}");
		}

	}
	
	
}
