package web.controller.eval;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.Image;
import web.dto.Menu;
import web.dto.Picture;
import web.dto.Review;
import web.service.face.ImageService;
import web.service.face.MenuService;
import web.service.face.ReviewService;
import web.service.face.StarService;
import web.service.impl.ImageServiceImpl;
import web.service.impl.MenuServiceImpl;
import web.service.impl.ReviewServiceImpl;
import web.service.impl.StarServiceImpl;
import web.util.Paging;

@WebServlet("/eval/detail")
public class DetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MenuService menuService = new MenuServiceImpl();
	
	private ImageService imageService = new ImageServiceImpl();
	
	private StarService starService = new StarServiceImpl();
	
	private ReviewService reviewService = new ReviewServiceImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		Ajax 로 받은 메뉴넘버
		int foodByMenuNo = Integer.parseInt(req.getParameter("menuno"));
		
		// 받은 메뉴넘버로 메뉴정보 전체가져오기	
		Menu menu = menuService.getMenuByMenu(foodByMenuNo);
		
		// 받은 메뉴넘버로 이미지정보 전체 가져오기
		Image image = imageService.getImageByMenuno(foodByMenuNo);
		req.setAttribute("menu", menu);
		req.setAttribute("image", image);
		

		   // 별점 서비스에서 들고온 더블값을 avg에 넣어줌
        Double avg = starService.AvgStar(req);
        //        // 해당하는 컨트롤러에 staravg 라는 이름의 속성으로 값을 던짐
        req.setAttribute("staravg", avg);
		

		
		//요청파라미터를 전달하여 Paging 객체 생성하기
		Paging paging = reviewService.getPagingReview(req);
		
		String param1 = req.getParameter("menuno");
		int menuno = 0;
		if( param1!=null && !"".equals(param1) ) {
			menuno = Integer.parseInt(param1);
		}
		req.setAttribute("menuno", req.getParameter("menuno"));
		
		HttpSession session = req.getSession();
		int userno = (int) session.getAttribute("userno");
		
		//게시글 페이징 처리 조회
		List<Map<String, Object>> reviewList = reviewService.evalReviewDetail(paging, userno, menuno);
		
		
		req.setAttribute("reviewList", reviewList);
		
		//페이징계산결과 MODEL값 전달
		req.setAttribute("paging", paging);
	
	
		
		//로그인 안하면 동작안함 , 로그인하면 동작하면서 한줄평 작성했던거 검증
		if(session.getAttribute("userno") != null) {
		int reviewcnt = reviewService.ReviewCount(req);
		req.setAttribute("revcnt", reviewcnt);
		}
	
		
		
		req.getRequestDispatcher("/WEB-INF/views/eval/detail.jsp").forward(req, resp);
	}

}
