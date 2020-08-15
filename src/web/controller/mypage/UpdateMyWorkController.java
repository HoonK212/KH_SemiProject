package web.controller.mypage;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.Picture;
import web.dto.Review;
import web.service.face.MyworkService;
import web.service.face.PictureService;
import web.service.face.ReviewService;
import web.service.face.StarService;
import web.service.impl.MyworkServiceImpl;
import web.service.impl.PictureServiceImpl;
import web.service.impl.ReviewServiceImpl;
import web.service.impl.StarServiceImpl;


@WebServlet("/update/mywork")
public class UpdateMyWorkController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MyworkService myworkService = new MyworkServiceImpl();
	private ReviewService reviewService = new ReviewServiceImpl();
	private StarService starService = new StarServiceImpl();
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

		
		// Mywork 조회 (MenoNo이용)
		Map<String, Object> mywork = myworkService.getMywork(req);
		
		// Review 요소의 키 꺼내기
		Set set = mywork.keySet();
        Iterator iterator = set.iterator();
        String reviewkey = null;
        while(iterator.hasNext()){
            String key = (String) iterator.next();
            if(key.equals("review")) {
            	reviewkey = key;
            }
        }
        
        // 리뷰내용 존재여부 확인
        String reviewcontent = ((Review)mywork.get(reviewkey)).getReviewContent();
        System.out.println("여기?" + reviewcontent);
        if( reviewcontent == null ) {
        	session.setAttribute("reviewcontent", "no");
        }else {
        	session.setAttribute("reviewcontent", "yes");
        }
        
        
        // 전달받은 데이터 request 컨텍스트에 저장하기
        req.setAttribute("picture", picture );
        req.setAttribute("grade", grade);
        req.setAttribute("myWork", mywork);

        // 포워딩
		req.getRequestDispatcher("/WEB-INF/views/mypage/update.jsp").forward(req,  resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//별점 수정
		starService.updateStar(req);
		
		//리뷰내용 있는 경우 -> update
		//리뷰내용 없는 경우 -> insert
		HttpSession session = req.getSession();
		String rvc = String.valueOf( session.getAttribute("reviewcontent") );
		
		if( rvc.equals("yes") ) {
			reviewService.updateReview(req);
			System.out.println("리뷰업데이트");
		}else if( rvc.equals("no") ){
			if( req.getParameter("review") != "" ) {
				System.out.println(req.getParameter("review"));
				reviewService.ReviewInsert(req);
				System.out.println("리뷰삽입");
			}
		}
		
		//현재 페이지로 리다이렉트
		resp.sendRedirect("/view/mywork");
		
	}
	
}
