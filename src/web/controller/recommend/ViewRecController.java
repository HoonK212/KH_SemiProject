package web.controller.recommend;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Image;
import web.dto.Menu;
import web.service.face.ImageService;
import web.service.face.MenuService;
import web.service.impl.ImageServiceImpl;
import web.service.impl.MenuServiceImpl;
import web.util.Paging;

@WebServlet("/rec")
public class ViewRecController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MenuService menuService = new MenuServiceImpl();
	private ImageService imageService = new ImageServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Menu> menu = menuService.getAllMenu();
		List<Image> image = imageService.getAllImage();
		
		req.setAttribute("menu", menu);
		req.setAttribute("image", image);
		
		req.getRequestDispatcher("/WEB-INF/views/recommend/recommend.jsp").forward(req, resp);
	}

}
