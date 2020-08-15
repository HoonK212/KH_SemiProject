package web.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import web.dao.face.ImageDao;
import web.dao.face.MenuDao;
import web.dao.impl.ImageDaoImpl;
import web.dao.impl.MenuDaoImpl;
import web.dto.Image;
import web.dto.Menu;
import web.service.face.MenuService;
import web.util.Paging;

public class MenuServiceImpl implements MenuService {

	private MenuDao menuDao = new MenuDaoImpl();
	
	private ImageDao imageDao = new ImageDaoImpl();

	@Override
	public Paging getPaging(HttpServletRequest req , int franno) {
		
		
		//전달파라미터 curPage를 파싱한다
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param!=null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		}
		
		//Menu 테이블의 총 게시글 수를 조회한다
		int totalCount = menuDao.selectCntAll(franno);
		
		//Paging 객체 생성
		Paging paging = new Paging(totalCount, curPage); 
		
		//계산된 Paging 객체 반환
		return paging;
	}

	@Override
	public List<Menu> getList(Paging paging ,  int franno) {
		return menuDao.selectCntAll(paging  , franno);
	}

	@Override
	public Menu getMenuNo(HttpServletRequest req) {

		Menu menuno = new Menu();
		
		String param = req.getParameter("menuNo");
		if(param!=null && !"".equals(param)) {
			menuno.setMenuNo(Integer.parseInt(param));
		}
		
		return menuno;
	}

	@Override
	public Menu view(Menu menuno) {
		
		Menu menu = menuDao.selectMenuByMenuno(menuno);
		
		return menu;
	}

	@Override
	public void applyMenu(HttpServletRequest req , int franno) {
		
		Menu menu = new Menu();
		
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
		
		if(!isMultipart) {
			return;
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		int maxMem = 1 * 1024 * 1024; // 1MB
		factory.setSizeThreshold(maxMem);
		
		ServletContext context = req.getServletContext();
		String path = context.getRealPath("upload");
		
		File repository = new File(path); //임시 저장 폴더
		factory.setRepository(repository);
		
		int maxFile = 10 * 1024 * 1024;//10MB
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(maxFile);
		
		List <FileItem> items = null;
		
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		Iterator<FileItem> iter = items.iterator();
		
		Image imageFile = null;
		
		
		while(iter.hasNext()) {
			
			FileItem item = iter.next();

			if(item.getSize() <= 0 ) continue;

		
			if(item.isFormField()) {
		
				String key = item.getFieldName();
				
				
				if( "applyMenuName".equals(key)) {
					try {
						menu.setMenuName(item.getString("UTF-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}else if("applyMenuDate".equals(key)) {
					try {
						
						String param = item.getString("UTF-8");

						if( param != null && !"".equals(param) ) {
							SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
							menu.setMenuDate(form.parse(param));
						}
						
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else if("applyMenuInfo".equals(key)) {
					try {
						menu.setMenuInfo(item.getString("UTF-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}else if("applyMenuCost".equals(key)) {
				try {
					menu.setMenuCost(Integer.parseInt(item.getString("UTF-8")));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
				
				
				
			}
			
			if(!item.isFormField()) {
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssS");
				String rename = sdf.format(new Date());
				
				//확장자
				String origin = item.getName();//원본파일명
				String ext = origin.substring(origin.lastIndexOf(".")+1 );
				
				// 저장될 이름
				String stored = rename + "." + ext;
			
				//----- DB에 업로드된 파일에 대한 정보 기록하기 ----
				imageFile = new Image();
				
				int imgSize = (int) item.getSize();
				
				imageFile.setImgName(path);//저장경로
				imageFile.setImgOrigin(origin);//원본이름
				imageFile.setImgServer(stored);	//서버이름
				imageFile.setImgExt(ext);//확장자
				imageFile.setImgSize(imgSize);//파일크기
				
				long filesize = 0;
				filesize = item.getSize();
				imageFile.setImgSize((int)filesize );
				//--------------------------------------------------
				
				//실제 업로드 파일
				File up = new File(
						context.getRealPath("upload") //업로드될 폴더
						, stored //저장파일의 이름(변환됨)
						);
				
				
				
				try {
					item.write(up); //실제 업로드(최종 결과 파일 생성)
					item.delete(); //임시 파일 삭제
				} catch (Exception e) {
					e.printStackTrace();
				}
				
	             
				
			}
		}
	

			int menuno = menuDao.insertMenu(menu , franno);
			imageFile.setMenuNo(menuno);//메뉴번호
			imageDao.insertImage(imageFile);
		
	}

	
	@Override
	public List<Menu> getAllMenu() {

		return menuDao.selectAllMenu();
	}

	
	@Override
	public Menu getMenuByMenu(int foodByMenuNo) {
		return menuDao.selectMenuByMenuNo(foodByMenuNo) ;
	}

	@Override
	public List<Menu> getMenuByfoodNo(String foodname) {
		return menuDao.selectMenuByFoodNo(foodname);
	}

	@Override
	public List<Menu> getMenuByfilter(String detailfilter, String foodName) {
		return menuDao.selectMenuByFoodNo(detailfilter,foodName);
	}


	


	
	
	
	

	
	

}
