package web.controller.eval;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.Image;
import web.dto.Menu;
import web.service.face.ImageService;
import web.service.face.MenuService;
import web.service.face.StarService;
import web.service.impl.ImageServiceImpl;
import web.service.impl.MenuServiceImpl;
import web.service.impl.StarServiceImpl;

@WebServlet("/eval/filter")
public class FilterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MenuService menuService = new MenuServiceImpl();
	
	private ImageService imageService = new ImageServiceImpl();
	
	private StarService starService = new StarServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String foodname = req.getParameter("food");
		List<Menu> menu = menuService.getMenuByfoodNo(foodname);
		List<Image> image = imageService.getImageByFran(menu , foodname);

		
		req.setAttribute("menu", menu);
		req.setAttribute("image", image);
		
		HttpSession session = req.getSession();
		session.setAttribute("food", foodname);
		
		req.getRequestDispatcher("/WEB-INF/views/eval/filter.jsp").forward(req, resp);
	}
	
}
