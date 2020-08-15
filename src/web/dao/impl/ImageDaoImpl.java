package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.dao.face.ImageDao;
import web.dbutil.JDBCTemplate;
import web.dto.Image;
import web.dto.Menu;

public class ImageDaoImpl implements ImageDao{

   private Connection conn = null; //DB연결 객체
   private PreparedStatement ps = null; //SQL수행 객체
   private ResultSet rs = null; //SQL조회 결과 객체
   
   @Override
   public void insertImage(Image imageFile) {
      conn = JDBCTemplate.getConnection();
      
      String sql ="";
      sql += "INSERT INTO Image( IMG_NAME, IMG_ORIGIN, IMG_SERVER, IMG_HOR, IMG_VER,IMG_EXT,IMG_SIZE,IMG_DATE ,MENU_NO )";
      sql += " VALUES(?,?,?,?,?,?,?,sysdate,?)";
      
      
      try {
         ps = conn.prepareStatement(sql);
         
         ps.setString(1, imageFile.getImgName());
         ps.setString(2, imageFile.getImgOrigin());
         ps.setString(3, imageFile.getImgServer());
         ps.setInt(4, imageFile.getImgHor());
         ps.setInt(5, imageFile.getImgVer());
         ps.setString(6, imageFile.getImgExt());
         ps.setInt(7, imageFile.getImgSize());
         ps.setInt(8, imageFile.getMenuNo());
         
         ps.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         JDBCTemplate.close(ps);
      }
   }

   @Override
   public Image selectImage(int menunoByImage) {

      Connection conn = JDBCTemplate.getConnection();
      PreparedStatement ps = null;
      ResultSet rs = null;
      
      Image image = null;
      
      String sql = " SELECT * FROM IMAGE WHERE MENU_NO = ?";
      
      try {
         ps = conn.prepareStatement(sql);
         ps.setInt(1, menunoByImage);
         
         rs = ps.executeQuery();
         
         while( rs.next()) {
            
            image = new Image();
            
            image.setImgNo(rs.getInt("IMG_NO"));
            image.setImgName(rs.getString("IMG_NAME"));
            image.setImgOrigin(rs.getString("IMG_ORIGIN"));
            image.setImgServer(rs.getString("IMG_SERVER"));
            image.setImgHor(rs.getInt("IMG_HOR"));
            image.setImgVer(rs.getInt("IMG_VER"));
            image.setImgExt(rs.getString("IMG_EXT"));
//            IMG_EXT
//            IMG_SIZE
//            IMG_DATE
            image.setMenuNo(rs.getInt("MENU_NO"));
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         JDBCTemplate.close(ps);
         JDBCTemplate.close(rs);
      }
      return image;
   }

   
   @Override
   public List<Image> selectAllImage() {
      conn = JDBCTemplate.getConnection();
      
      String sql = "";
      sql += "select R.*, M.menu_stat from";
      sql += " (SELECT I.img_no,  I.img_origin, I.img_server, I.img_hor, I.img_ver, I.img_ext, I.img_size, I.img_date, I.menu_no, avg(S.star_score) as star_score";
      sql += " FROM image I, star S";
      sql += " WHERE I.menu_no = S.menu_no(+)";
      sql += " GROUP BY I.img_no,  I.img_origin, I.img_server, I.img_hor, I.img_ver, I.img_ext, I.img_size, I.img_date, I.menu_no";
      sql += " order by star_score desc nulls last, I.menu_no desc) R, menu M";
      sql += " where R.menu_no = M.menu_no and M.menu_stat='Y' and M.menu_blind='N'";
      
      
      
       List<Image> imageList = new ArrayList<>();
      
       try {
         ps = conn.prepareStatement(sql);
         rs = ps.executeQuery();
         
         while(rs.next()) {
            Image image = new Image();
            
            image.setImgNo(rs.getInt("IMG_NO"));
//            image.setImgName(rs.getString("IMG_NAME"));
            image.setImgOrigin(rs.getString("IMG_ORIGIN"));
            image.setImgServer(rs.getString("IMG_SERVER"));
            image.setImgHor(rs.getInt("IMG_HOR"));
            image.setImgVer(rs.getInt("IMG_VER"));
            image.setImgExt(rs.getString("IMG_EXT"));
            image.setImgExt(rs.getString("IMG_EXT"));
            image.setImgSize(rs.getInt("IMG_SIZE"));
            image.setImgDate(rs.getDate("IMG_DATE"));
            image.setMenuNo(rs.getInt("MENU_NO"));
            
            imageList.add(image);
            
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         JDBCTemplate.close(ps);
      }
      return imageList;
   }

   @Override
   public Image selectImageByMenuno(int foodByMenuNo) {
      
      conn = JDBCTemplate.getConnection();
      
      String sql = "select I.* from image I, menu M where I.menu_no=M.menu_no and M.menu_stat='Y' and M.menu_blind='N' and I.menu_no = ?";
      
      
      Image image  = null;
      
      try {
         ps = conn.prepareStatement(sql);
         
         ps.setInt(1, foodByMenuNo);
         
         rs = ps.executeQuery();
         
         while(rs.next()) {
            image = new Image();
            image.setImgNo(rs.getInt("IMG_NO"));
            image.setImgOrigin(rs.getString("IMG_ORIGIN"));
            image.setImgServer(rs.getString("IMG_SERVER"));
            image.setImgHor(rs.getInt("IMG_HOR"));
            image.setImgVer(rs.getInt("IMG_VER"));
            image.setImgExt(rs.getString("IMG_EXT"));
            image.setImgExt(rs.getString("IMG_EXT"));
            image.setImgSize(rs.getInt("IMG_SIZE"));
            image.setImgDate(rs.getDate("IMG_DATE"));
            image.setMenuNo(rs.getInt("MENU_NO"));
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         JDBCTemplate.close(rs);
         JDBCTemplate.close(ps);
      }
      return image;
   }

   
   
   @Override
   public List<Image> selectImageByFran(List<Menu> menu, String foodname) {
      
      conn = JDBCTemplate.getConnection();
      
      String sql = "";
      sql += "select R.* from";
      sql += " (SELECT";
      sql += " B.menu_no, B.menu_name, B.menu_info, B.menu_cost, B.menu_date, B.menu_stat, B.menu_blind, B.fran_no, B.fran_name, B.food_no, B.food_name, B.img_no ,B.img_server, avg(S.star_score) as star_score";
      sql += " FROM (select A.*, D.food_name from";
      sql += " (SELECT M.* , F.fran_name , F.food_no,i.img_no ,i.img_server  FROM menu M ,fran F, image I WHERE m.fran_no=f.fran_no and i.menu_no=m.menu_no ) A, food D";
      sql += " where A.food_no=D.food_no and D.food_name = ?) B, star S";
      sql += " WHERE B.menu_no = S.menu_no(+)";
      sql += " GROUP BY B.menu_no, B.menu_name, B.menu_info, B.menu_cost, B.menu_date, B.menu_stat, B.menu_blind, B.fran_no, B.fran_name, B.food_no, B.food_name, B.img_no ,B.img_server";
      sql += " order by star_score desc nulls last, B.menu_no desc) R";
      sql += " where R.menu_stat='Y' and R.menu_blind='N'";

      
      List<Image> imageList = new ArrayList<>();
      
       try {
         ps = conn.prepareStatement(sql);
         ps.setString(1, foodname);
         rs = ps.executeQuery();
         
         while(rs.next()) {
            Image image = new Image();
            
            image.setImgNo(rs.getInt("IMG_NO"));
            image.setImgServer(rs.getString("IMG_SERVER"));
            image.setMenuNo(rs.getInt("MENU_NO"));
            
            imageList.add(image);
            
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         JDBCTemplate.close(ps);
      }
      return imageList;
   }

   @Override
   public List<Image> selectImageByFran(String detailfilter, String foodName) {
      conn = JDBCTemplate.getConnection();
      
      String sql = "";
      
      if("평점순".equals(detailfilter)) {
         sql += "select R.* from";
         sql += " (SELECT";
         sql += " B.menu_no, B.menu_name, B.menu_info, B.menu_cost, B.menu_date, B.menu_stat, B.menu_blind, B.fran_no, B.fran_name, B.food_no, B.food_name, B.img_no ,B.img_server, avg(S.star_score) as star_score";
         sql += " FROM (select A.*, D.food_name from";
         sql += " (SELECT M.* , F.fran_name , F.food_no,i.img_no ,i.img_server  FROM menu M ,fran F, image I WHERE m.fran_no=f.fran_no and i.menu_no=m.menu_no ) A, food D";
         sql += " where A.food_no=D.food_no and D.food_name = ?) B, star S";
         sql += " WHERE B.menu_no = S.menu_no(+)";
         sql += " GROUP BY B.menu_no, B.menu_name, B.menu_info, B.menu_cost, B.menu_date, B.menu_stat, B.menu_blind, B.fran_no, B.fran_name, B.food_no, B.food_name, B.img_no ,B.img_server";
         sql += " order by star_score desc nulls last, B.menu_no desc) R";
         sql += " where R.menu_stat='Y' and R.menu_blind='N'";

      }else if("리뷰순".equals(detailfilter)) {
         sql += "select C.* from";
         sql += " (SELECT";
         sql+=" B.menu_no, B.menu_name, B.menu_info, B.menu_cost, B.menu_date, B.menu_stat, B.menu_blind, B.fran_no, B.fran_name, B.food_no, B.food_name, B.img_no ,B.img_server, count(R.review_no) as review_cnt";
         sql+=" FROM (select A.*, D.food_name from";
         sql+=" (select M.*, F.fran_name, F.food_no,i.img_no ,i.img_server from menu M, fran F, image I where M.fran_no = F.fran_no and i.menu_no=m.menu_no) A, food D";
         sql+=" where A.food_no=D.food_no and D.food_name = ?) B, review R";
         sql+=" WHERE B.menu_no = R.menu_no(+)";
         sql+=" GROUP BY B.menu_no, B.menu_name, B.menu_info, B.menu_cost, B.menu_date, B.menu_stat, B.menu_blind, B.fran_no, B.fran_name, B.food_no, B.food_name, B.img_no ,B.img_server";
         sql+=" order by review_cnt desc nulls last, B.menu_no desc) C";
         sql += " where C.menu_stat='Y' and C.menu_blind='N'";

         
      }else if("가격순".equals(detailfilter)) {
         sql += "select R.* from";
         sql += " (SELECT";
         sql += " B.menu_no, B.menu_name, B.menu_info, B.menu_cost, B.menu_date, B.menu_stat, B.menu_blind, B.fran_no, B.fran_name, B.food_no, B.food_name, B.img_no ,B.img_server, avg(S.star_score) as star_score";
         sql += " FROM (select A.*, D.food_name from";
         sql += " (SELECT M.* , F.fran_name , F.food_no,i.img_no ,i.img_server  FROM menu M ,fran F, image I WHERE m.fran_no=f.fran_no and i.menu_no=m.menu_no ) A, food D";
         sql += " where A.food_no=D.food_no and D.food_name = ?) B, star S";
         sql += " WHERE B.menu_no = S.menu_no(+)";
         sql += " GROUP BY B.menu_no, B.menu_name, B.menu_info, B.menu_cost, B.menu_date, B.menu_stat, B.menu_blind, B.fran_no, B.fran_name, B.food_no, B.food_name, B.img_no ,B.img_server";
         sql += " order by B.menu_Cost DESC, B.menu_no desc) R";
         sql += " where R.menu_stat='Y' and R.menu_blind='N'";

      }else if("출시일순".equals(detailfilter)) {
         sql += "select R.* from";
         sql += " (SELECT";
         sql += " B.menu_no, B.menu_name, B.menu_info, B.menu_cost, B.menu_date, B.menu_stat, B.menu_blind, B.fran_no, B.fran_name, B.food_no, B.food_name, B.img_no ,B.img_server, avg(S.star_score) as star_score";
         sql += " FROM (select A.*, D.food_name from";
         sql += " (SELECT M.* , F.fran_name , F.food_no,i.img_no ,i.img_server  FROM menu M ,fran F, image I WHERE m.fran_no=f.fran_no and i.menu_no=m.menu_no ) A, food D";
         sql += " where A.food_no=D.food_no and D.food_name = ?) B, star S";
         sql += " WHERE B.menu_no = S.menu_no(+)";
         sql += " GROUP BY B.menu_no, B.menu_name, B.menu_info, B.menu_cost, B.menu_date, B.menu_stat, B.menu_blind, B.fran_no, B.fran_name, B.food_no, B.food_name, B.img_no ,B.img_server";
         sql += " order by B.menu_date DESC, B.menu_no desc) R";
         sql += " where R.menu_stat='Y' and R.menu_blind='N'";

      }
      
      List<Image> imageList = new ArrayList<>();
      
       try {
         ps = conn.prepareStatement(sql);
         ps.setString(1, foodName);
         rs = ps.executeQuery();
         
         while(rs.next()) {
            Image image = new Image();
            
            image.setImgNo(rs.getInt("IMG_NO"));
//            image.setImgName(rs.getString("IMG_NAME"));
//            image.setImgOrigin(rs.getString("IMG_ORIGIN"));
            image.setImgServer(rs.getString("IMG_SERVER"));
//            image.setImgHor(rs.getInt("IMG_HOR"));
//            image.setImgVer(rs.getInt("IMG_VER"));
//            image.setImgExt(rs.getString("IMG_EXT"));
//            image.setImgExt(rs.getString("IMG_EXT"));
//            image.setImgSize(rs.getInt("IMG_SIZE"));
//            image.setImgDate(rs.getDate("IMG_DATE"));
            image.setMenuNo(rs.getInt("MENU_NO"));
            
            imageList.add(image);
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         JDBCTemplate.close(ps);
      }
      return imageList;
      

   
   }

   
   
   
   
   
}