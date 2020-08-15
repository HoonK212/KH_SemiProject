package web.controller.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import web.dto.Fran;
import web.dto.User;
import web.service.face.FranService;
import web.service.face.UserService;
import web.service.impl.FranServiceImpl;
import web.service.impl.UserServiceImpl;


@WebServlet("/login")
public class UserLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserServiceImpl();
	private FranService franService = new FranServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//포워딩
		req.getRequestDispatcher("/WEB-INF/views/login/login.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//전달파라미터 얻기 - 로그인정보
		User user = userService.getLoginUser(req);
		
		
		//로그인 인증
		boolean login = userService.login(user);
		
		//로그인 성공
		if(login) {
			//로그인 사용자 정보 얻어오기
			user = userService.info(user);

			//---------징계회원 처리---------
			if( user.getUserReport() != null ) {
				
				//징계날짜
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String rd = sdf.format( new java.util.Date ( user.getUserReport().getTime() ) );
				int reportdate = Integer.parseInt(rd);

				//현재 날짜
				String td = sdf.format( new Date() );
				int today = Integer.parseInt(td);
				
				
				//징계날짜가 현재 날짜보다 같거나 크다면 로그인 막기
				if( reportdate == today || reportdate > today ) {
					
					//클라이언트에 보낼 정보
					Map map = new HashMap();
					map.put("report", true );
					
					//계정중지를 클라이언트에 알려줘야함
					PrintWriter out = resp.getWriter();
					out.println( new Gson().toJson(map) );
					
					return;
				}
			}
			
			//세션 저장
			HttpSession session = req.getSession();
			session.setAttribute("login", login);
			session.setAttribute("userid", user.getUserId());
			session.setAttribute("username", user.getUserName());
			session.setAttribute("usernick", user.getUserNick());
			session.setAttribute("userAuth", user.getUserAuth());
			session.setAttribute("userbirth", user.getUserBirth());
			session.setAttribute("useremail", user.getUserEmail());
			session.setAttribute("usergender", user.getUserGender());
			session.setAttribute("usergrade", user.getUserGrade());
			session.setAttribute("usertel", user.getUserTel());
			session.setAttribute("userno", user.getUserNo());
			session.setAttribute("fran", user.getFranNo());
			
			//방문 횟수 업데이트
			userService.updateUserCnt(user);
			
			// userAuth==2  , 프랜차이즈 관리자일경우!
			if( 2 == user.getUserAuth()) {
				
				Fran fran = new Fran();
				fran = franService.getFran(user.getFranNo());

				session.setAttribute("franname", fran.getFranName() );
				session.setAttribute("franno", fran.getFranNo() );
			}
			
			//클라이언트에 보낼 정보
			Map map = new HashMap();
			map.put("login", true );
			map.put("userAuth", user.getUserAuth());
			
			
			//로그인 성공했다는 것을 클라이언트에 알려줘야함
			//자바스크립트가 알아들을 수 있도록
			PrintWriter out = resp.getWriter();
			out.println( new Gson().toJson(map) );
			
		}
		
		//로그인 실패
		if(!login) {
			
			//클라이언트에 보낼 정보
			Map map = new HashMap();
			map.put("login", false );
			
			//로그인 실패했다는 것을 클라이언트에 알려줘야함
			//자바스크립트가 알아들을 수 있도록
			PrintWriter out = resp.getWriter();
			out.println( new Gson().toJson(map) );
			
		}

	}
	
}
