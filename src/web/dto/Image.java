package web.dto;

import java.util.Date;

public class Image {

	private int	imgNo;
	private String	imgName;
	private String	imgOrigin;
	private String	imgServer;
	private int	imgHor;
	private int	imgVer;
	private String imgExt;
	private int	imgSize;
	private Date imgDate;
	private int	menuNo;
	@Override
	public String toString() {
		return "Image [imgNo=" + imgNo + ", imgName=" + imgName + ", imgOrigin=" + imgOrigin + ", imgServer="
				+ imgServer + ", imgHor=" + imgHor + ", imgVer=" + imgVer + ", imgExt=" + imgExt + ", imgSize="
				+ imgSize + ", imgDate=" + imgDate + ", menuNo=" + menuNo + "]";
	}
	public int getImgNo() {
		return imgNo;
	}
	public void setImgNo(int imgNo) {
		this.imgNo = imgNo;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getImgOrigin() {
		return imgOrigin;
	}
	public void setImgOrigin(String imgOrigin) {
		this.imgOrigin = imgOrigin;
	}
	public String getImgServer() {
		return imgServer;
	}
	public void setImgServer(String imgServer) {
		this.imgServer = imgServer;
	}
	public int getImgHor() {
		return imgHor;
	}
	public void setImgHor(int imgHor) {
		this.imgHor = imgHor;
	}
	public int getImgVer() {
		return imgVer;
	}
	public void setImgVer(int imgVer) {
		this.imgVer = imgVer;
	}
	public String getImgExt() {
		return imgExt;
	}
	public void setImgExt(String imgExt) {
		this.imgExt = imgExt;
	}
	public int getImgSize() {
		return imgSize;
	}
	public void setImgSize(int imgSize) {
		this.imgSize = imgSize;
	}
	public Date getImgDate() {
		return imgDate;
	}
	public void setImgDate(Date imgDate) {
		this.imgDate = imgDate;
	}
	public int getMenuNo() {
		return menuNo;
	}
	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}
	
	
	
}
