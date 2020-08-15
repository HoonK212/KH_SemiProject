package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dto.Fran;
import web.dto.Menu;
import web.util.Paging;

public interface MenuService {


	
	/**
	 * 페이징 객체 생성
	 * @param req - 요청 정보 객체
	 * @return Paging - 페이징 계산이 완료된 객체
	 */
	public Paging getPaging(HttpServletRequest req , int franno);

	
	/**
	 *  페이징 처리하여 보여질 게시글 목록만 조회
	 * @param paging - 페이징 정보 객체
	 * @return List<Menu> - 게시글 전체 조회 결과 리스트
	 */
	public List<Menu> getList(Paging paging , int franno);

	
	/**
	 * 요청파라미터 얻기
	 * @param req - 요청정보 객체
	 * @return Menu - 전달파라미터 Menuno를 포함한 객체
	 */
	public Menu getMenuNo(HttpServletRequest req);


	/**
	 * 주어진 menuno를 이용하여 게시글을 조회한다
	 * 
	 * @param menuno - menuno를 가지고 있는객체
	 * @return Menu - 조회된 게시글
	 */
	public Menu view(Menu menuno);


	/**
	 * 게시글 작성
	 * 입력한 게시글 내용을 DB에 저장
	 * @param req - 요청정보 객체(게시글내용 + 첨부파일)
	 */
	public void applyMenu(HttpServletRequest req , int franno);


	/**
	 * 전체 메뉴 가져오기(평점보기 첫페이지)
	 * @return
	 */
	public List<Menu> getAllMenu();


	/**
	 * 평점보기에서 누른 메뉴번호로 메뉴정보 얻어오기 
	 * @param foodByMenuNo
	 * @return
	 */
	public Menu getMenuByMenu(int foodByMenuNo);

	
	
	/**
	 * 푸드넘버 로 선택한프랜차이즈 메뉴가져오기
	 * @param foodname
	 * @return
	 */
	public List<Menu> getMenuByfoodNo(String foodname);


	/**
	 * 필터건거(테스투중)
	 * @param detailfilter
	 * @param foodName
	 * @return
	 */
	public List<Menu> getMenuByfilter(String detailfilter, String foodName);


	
	


	

	
	

}
