package web.controller.login;

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


@WebServlet("/searchid")
public class SearchIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private UserService userService = new UserServiceImpl();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User user = userService.searchId(req);
		
		String id = user.getUserId();
		
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		//아이디 전달
		if( id != null && id != "") {
			out.println( new Gson().toJson(id) );
		}else {
			out.println( new Gson().toJson(id) );
		}
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User user = userService.checkId(req);
		
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		if( user.getUserId() != null && user.getUserId() != "" ) {
			out.println( new Gson().toJson(user.getUserId()) );
		}else {
			out.println( new Gson().toJson(user.getUserId()) );
		}
		
	}

}
