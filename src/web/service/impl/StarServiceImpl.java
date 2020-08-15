package web.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import web.dao.face.StarDao;
import web.dao.impl.StarDaoImpl;
import web.dto.Star;
import web.dto.User;
import web.service.face.StarService;

public class StarServiceImpl implements StarService{
	
	private StarDao starDao = new StarDaoImpl();
	

	@Override
	public int countStarByUserNo(HttpServletRequest req) {
	
		HttpSession session = req.getSession();
		String param = String.valueOf(session.getAttribute("userno"));
		int userno = Integer.parseInt(param);
		
		int cnt = starDao.selectCntStarByUserNo(userno);
		
		return cnt;
	}


	   @Override
	   public void updateStar(HttpServletRequest req) {
	      
	      //메뉴번호 전달받기
	      String param = req.getParameter("menuno");
	      int menuNo = Integer.parseInt(param);
	      
	      //회원번호 꺼내기(세션)
	      HttpSession session = req.getSession();
	      String p = String.valueOf(session.getAttribute("userno"));
	      int userno = Integer.parseInt(p);
	      
	      //수정된 별점 전달받기
	      String par = req.getParameter("updatescore");
	      double updatescore = Double.parseDouble(par);
	      
	      starDao.updateStarByUserNoMenuNo(updatescore, userno, menuNo);
	      
	   }


	@Override
	public Double AvgStar(HttpServletRequest req) {
		Star star = new Star();
		star.setMenuNo(Integer.parseInt(req.getParameter("menuno")));
		
		return starDao.AvgStarSelect(star);
	}


	@Override
	public void StarInsert(HttpServletRequest req) {
		Star star = new Star();
		//유저넘버값 추출 하는 세션
		HttpSession session = req.getSession();
		//유저아이디값을 넣어줌
		User user = new User();
		user.setUserNo((int)session.getAttribute("userno"));
		
		
		//해당하는 메뉴의 별점을 star에 넣어주고 dao로 전달
		star.setStarScore(Double.parseDouble(req.getParameter("starscore")));
		//해당하는 메뉴의 메뉴넘버를 star.menuno에 넣어주고 dao로 전달
		star.setMenuNo(Integer.parseInt(req.getParameter("menuno")));
		
		starDao.detailInsertStar(star,user);

	}


	   @Override
	   public void deleteStar(HttpServletRequest req) {
	      //메뉴번호 전달받기
	      String param = req.getParameter("menuNo");
	      int menuNo = Integer.parseInt(param);
	      
	      //회원번호 꺼내기(세션)
	      HttpSession session = req.getSession();
	      String p = String.valueOf(session.getAttribute("userno"));
	      int userno = Integer.parseInt(p);
	      
	      starDao.deleteStarByUserNoMenuNo(menuNo, userno);
	      
	   }



}
