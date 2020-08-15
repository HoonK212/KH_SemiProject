package web.controller.eval;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.service.face.ReviewService;
import web.service.impl.ReviewServiceImpl;

@WebServlet("/eval/detail/goodbad")
public class GoodAndBadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReviewService reviewService = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();

		//로그인 안하면 동작안함 , 로그인하면 동작하면서 좋아요 싫어요  눌렀던거 검증
		if(session.getAttribute("userno") != null) {
			int good_badcnt = reviewService.good_badcnt(req);
			req.setAttribute("good_badcnt", good_badcnt);
		}
		
		String goodbad = req.getParameter("goodbad");
		
		//좋아요 싫어요 버튼
		reviewService.goodandbadbtn(req);
		
	}
}
