package web.controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.face.ReviewService;
import web.service.face.StarService;
import web.service.impl.ReviewServiceImpl;
import web.service.impl.StarServiceImpl;



@WebServlet("/delete/mywork")
public class DeleteMyWorkController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReviewService reviewService = new ReviewServiceImpl();
	private StarService starService = new StarServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//별점 , 리뷰 삭제 (별점 먼저 삭제해야함!)
		starService.deleteStar(req);
		reviewService.deleteReview(req);
		
		//현재 페이지로 리다이렉트
		resp.sendRedirect("/view/mywork");
	
	
	}
}
