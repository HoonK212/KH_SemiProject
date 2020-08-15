package web.dto;

import java.util.Date;

public class Picture {

	private int	picNo;
	private String picName;
	private String picOrigin;
	private String picServer;
	private int	picHor;
	private int	picVer;
	private String picExt;
	private int	picSize;
	private Date picDate;
	private int	userNo;
	
	@Override
	public String toString() {
		return "Picture [picNo=" + picNo + ", picName=" + picName + ", picOrigin=" + picOrigin + ", picServer="
				+ picServer + ", picHor=" + picHor + ", picVer=" + picVer + ", picExt=" + picExt + ", picSize="
				+ picSize + ", picDate=" + picDate + ", userNo=" + userNo + "]";
	}
	
	public int getPicNo() {
		return picNo;
	}
	public void setPicNo(int picNo) {
		this.picNo = picNo;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public String getPicOrigin() {
		return picOrigin;
	}
	public void setPicOrigin(String picOrigin) {
		this.picOrigin = picOrigin;
	}
	public String getPicServer() {
		return picServer;
	}
	public void setPicServer(String picServer) {
		this.picServer = picServer;
	}
	public int getPicHor() {
		return picHor;
	}
	public void setPicHor(int picHor) {
		this.picHor = picHor;
	}
	public int getPicVer() {
		return picVer;
	}
	public void setPicVer(int picVer) {
		this.picVer = picVer;
	}
	public String getPicExt() {
		return picExt;
	}
	public void setPicExt(String picExt) {
		this.picExt = picExt;
	}
	public int getPicSize() {
		return picSize;
	}
	public void setPicSize(int picSize) {
		this.picSize = picSize;
	}
	public Date getPicDate() {
		return picDate;
	}
	public void setPicDate(Date picDate) {
		this.picDate = picDate;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
	
	
	
}
