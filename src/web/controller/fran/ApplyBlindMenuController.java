package web.controller.fran;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.Blind;
import web.dto.Menu;
import web.service.face.BlindService;
import web.service.face.MenuService;
import web.service.impl.BlindServiceImpl;
import web.service.impl.MenuServiceImpl;
import web.util.Paging;

@WebServlet("/fran/apply/blind")
public class ApplyBlindMenuController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MenuService menuService = new MenuServiceImpl();
	
	private BlindService blindService = new BlindServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		int franno =  (int) session.getAttribute("franno");
		
		
		Paging paging = menuService.getPaging(req , franno);
		
		List<Menu> MenuList = menuService.getList(paging ,franno);
		
		
		req.setAttribute("MenuList", MenuList);
		
		req.getRequestDispatcher("/WEB-INF/views/fran_board/applyBlind.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		int franno =  (int) session.getAttribute("franno");
		
		String[] Arrmenuno  = req.getParameterValues("applyblindNameByMenuNo");
		
		int menuno = Integer.parseInt(Arrmenuno[0]);
		
		Blind blind = blindService.getParam(req);
		
		
		
		blindService.applyBlind(blind, menuno);
		
		
		resp.sendRedirect("/fran");
		
	}
}
