package web.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import web.dao.face.PictureDao;
import web.dao.impl.PictureDaoImpl;
import web.dto.Picture;
import web.dto.User;
import web.service.face.PictureService;

public class PictureServiceImpl implements PictureService {
	
	PictureDao pictureDao = new PictureDaoImpl();
	
	
	@Override
	public void uploadPicture(HttpServletRequest req) {

		// DTO 객체 
		Picture picture = null;
		User user = null;
		
		//1. 파일업로드 형태가 데이터가 맞는지 검사 : 요청메시지 Content-Type이 multipart/form-data 가 맞는지 확인한다
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
		
		//1-1. multipart/form-data 인코딩으로 전송되지 않았을 경우 처리 중단하기
		if( !isMultipart ) {
			return; // uploadPicture() 메소드 중단시키기
		}
		
		//1-2. 여기 이후부터는 multipart/form-data로 요청된 상황
		
		//2. 업로드된 파일을 처리하는 아이템팩토리 객체 생성
		
		//	ItemFactory : 업로드된 데이터(FileItem)를 처리하는 방식을 설정하는 클래스
		
		//	FileItem : 클라이언트로부터 전송된 데이터를 객체화시킨 것
		
		// DiskFileItemFactory class
		//	-> 디스크(HDD) 기반의 파일아이템 처리 API
		//	-> 업로드된 파일을 디스크(HDD)에 임시 저장하고 나중에 처리한다
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		
		//3. 업로드된 파일아이템의 용량이 적당히 작으면 메모리에서 하도록 설정
		int maxMem = 1 * 1024 * 1024; // 1MB
		factory.setSizeThreshold(maxMem);
		
		
		//4. 용량이 적당히 크면 임시파일(HDD) 만들어서 처리하도록 설정	-> 임시파일 폴더 설정
		
		ServletContext context = req.getServletContext();
		String path = context.getRealPath("tmp");
		
		File repository = new File(path); // 임시 저장 폴더
	//	System.out.println(repository);
		
		factory.setRepository(repository);
		
		//파일업로드 수행 객체 생성
		//	DiskFileItemFactory 객체 이용해서 생성함
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//파일 업로드 용량제한 설정 : 10MB
		//5. 업로드 허용 용량 기준을 넘지 않을 경우에만 파일업로드 처리되도록 설정 -> 기준을 넘으면 업로드 차단됨
		int maxFile = 10 * 1024 * 1024; //10MB
		upload.setFileSizeMax(maxFile);
		
		
		// --------------------- 파일 업로드 준비 완료 -------------------------
		
		// 6. 업로드된 데이터 추출(파싱)
		//	임시 파일 업로드도 같이 수행된다
		
		List<FileItem> items = null;
		
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		// 7. 파싱된 요청정보 데이터 처리하기
		//	items 리스트에 요청파라미터(파일 포함)가 파싱되어 들어있음
		
		//	요청정보의 3가지 형태
		//		1. 빈 파일 (용량이 0인 파일)
		//		2. form-data (전달 파라미터)
		//		3. 파일
		
		// 파일 아이템 반복자
		Iterator<FileItem> iter = items.iterator();
		
		// 사용자 DTO 객체생성
		user = new User();
		// userno저장
		int userno = -1;
		
		// 모든 요청정보 처리하는 반복문
		while( iter.hasNext() ) {
			
			// 요쳥 정보 처리하는 반북문
			FileItem item = iter.next();
			
			
			// 1) 빈 파일에 대한 처리
			if( item.getSize() <= 0) {
				continue;
			}
			
			// 2) 기본 전달 파라미터에 대한 처리
			
			if( item.isFormField() ) {
				
				// ---------- 키 값에 따라 처리하는 방식 -----------
				// 키값 꺼내기
				String key = item.getFieldName();
				
				if( "userno".equals(key) ) { //전달파라미터 name이 "userno"
					try {
						userno = Integer.parseInt( item.getString("UTF-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				} // -------  user 객체에 userno 삽입완료
			}
			
			// 3) 파일에 대한 처리
			if( !item.isFormField() ) {
				
				// 업로드된 파일을 처리하는 방식
				
				// 1) 파일을 웹 서버의 로컬 디스크에 저장
				//	파일의 정보를 DB에 기록해야한다 - 경로를 저장한다
				// --------------------------------
				
				// 서버에 저장되는 파일명을 "년월일시분초밀리초.확장자" 로 변경하기
				
				// 파일명 - 년월일시분초밀리초
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssS");
				String rename = sdf.format(new Date());
				
				// 확장자
				String origin = item.getName(); //원본파일명
				String ext = origin.substring( origin.lastIndexOf(".")+1 ); //확장자
				
				// 저장될 이름
				String stored = rename + "." + ext;
				
				// --------- 파일 정보 저장하기 ----------
				picture = new Picture();
				picture.setPicOrigin(origin);
				picture.setPicServer(stored);
				picture.setPicExt(ext);
				// 원본이름, 서버이름, 확장자 저장완료
				
				// 파일크기 저장
				picture.setPicSize( (int)item.getSize() );
				
				
				
				// ------------------------------------------------ Picture에 원본이름, 서버이름, 확장자, 파일크기 저장완료
				
				// 실제 업로드 파일 
				String repositloc = context.getRealPath("upload_picture");
				File up = new File( repositloc, stored );
				
				
				//저장경로 저장
				picture.setPicName(repositloc);

				try {
					item.write(up); //실제 업로드(최종 결과 파일 생성)
					item.delete(); //임시 파일 삭제
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				int hor=0;
				int ver=0;
				
				try {
					BufferedImage bi = ImageIO.read(up);
					hor=bi.getWidth();
					ver=bi.getHeight();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				// 파일가로값 저장
				picture.setPicHor(hor);
				
				// 파일세로값 저장
				picture.setPicVer(ver);
				
				//사용자번호 저장
				picture.setUserNo(userno);
				
			} // if( !item.isFormFiel d() ) end
			
		} // while(hasNext() ) end
		
		
		// 사진 업로드
		if( picture != null) { 
			
			//사진 업로드하기전에 본인이 올린 사진은 모두 지움
			pictureDao.deleteFile(picture);
			
			//사진 업로드
			pictureDao.insertFile(picture);
		}
	
	}//insertPicture() end


	@Override
	public Picture info(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		String param = String.valueOf(session.getAttribute("userno"));
		int userno = Integer.parseInt(param);
		
		return pictureDao.selectPicture(userno);
	}



	
}//class end
	
	


