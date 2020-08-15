package web.controller.fran;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.Fran;
import web.dto.Menu;
import web.service.face.MenuService;
import web.service.impl.MenuServiceImpl;
import web.util.Paging;

@WebServlet("/fran")
public class FranMainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MenuService menuService = new MenuServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		int franno = (int) session.getAttribute("franno");
		
		Paging paging = menuService.getPaging(req , franno);
		

		List<Menu> MenuList = menuService.getList(paging ,  franno);
		
		req.setAttribute("paging", paging);
		
		req.setAttribute("MenuList", MenuList);
		
		
		// 첫화면(로그인됬을경우)에는 등록된 프랜차이즈 게시물들을 보여준다
		req.getRequestDispatcher("/WEB-INF/views/fran_main.jsp").forward(req, resp);
	
		
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}
	
	
}
