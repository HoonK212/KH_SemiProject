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


@WebServlet("/doublecheckid")
public class DoubleCheckId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		User joinUser = new User();
		joinUser = userService.doublecheckId(req);

		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();

		if( joinUser.getUserId() == null ) {
			out.println( "{\"result\":true}" );
		} else {
			out.println( "{\"result\":false}" );
		}
	
	}

}
