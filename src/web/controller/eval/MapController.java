package web.controller.eval;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Fran;
import web.dto.Menu;
import web.service.face.FranService;
import web.service.face.MenuService;
import web.service.impl.FranServiceImpl;
import web.service.impl.MenuServiceImpl;

@WebServlet("/eval/map")
public class MapController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MenuService menuService = new MenuServiceImpl();
	FranService franService = new FranServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		Menu menu = new Menu();
		menu.setMenuNo(Integer.parseInt(req.getParameter("menuno")));
		menu = menuService.view(menu);
		int menuno = menu.getMenuNo();

		Fran fran = new Fran();
		int franno = menu.getFranNo();
		fran = franService.getFran(franno);
		
		String franname = fran.getFranName();
		String param = req.getParameter("where");
		String where = franname + " " + param;
		
		req.setAttribute("where", where);
		req.setAttribute("map", menuno);
		req.getRequestDispatcher("/WEB-INF/views/eval/map.jsp").forward(req, resp);
	}

}
