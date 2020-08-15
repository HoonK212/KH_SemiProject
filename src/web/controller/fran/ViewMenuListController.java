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

@WebServlet("/fran/list")
public class ViewMenuListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MenuService menuService = new MenuServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		int franno =  (int) session.getAttribute("franno");
		
		
		Paging paging = menuService.getPaging(req , franno);
		
		List<Menu> MenuList = menuService.getList(paging ,franno);
		
		req.setAttribute("paging", paging);
		
		req.setAttribute("MenuList", MenuList);
		
		req.getRequestDispatcher("/WEB-INF/views/fran_board/list.jsp").forward(req, resp);
	
	}
	
	
}
