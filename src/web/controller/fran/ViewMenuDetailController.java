package web.controller.fran;

import java.io.IOException;

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
import web.service.impl.ImageServiceImpl;
import web.service.impl.MenuServiceImpl;

@WebServlet("/fran/view")
public class ViewMenuDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MenuService menuService = new MenuServiceImpl();
	
	private ImageService imageService = new ImageServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
		Menu menuno = menuService.getMenuNo(req);
		
		
		Menu viewMenu = menuService.view(menuno);
		
		req.setAttribute("viewMenu", viewMenu);
		
		
		int menunoByImage = menuno.getMenuNo();
		
		
		Image image = new Image();
		image = imageService.info( req , menunoByImage ); 
		req.setAttribute("image", image);
				
		
		req.getRequestDispatcher("/WEB-INF/views/fran_board/view.jsp").forward(req, resp);
	}
	
}
