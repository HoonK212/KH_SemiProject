package web.dto;

import java.util.Date;

public class Star {

	private int	starNo;
	private double starScore;
	private Date starDate;
	private int	userNo;
	private int	menuNo;
	@Override
	public String toString() {
		return "Star [starNo=" + starNo + ", starScore=" + starScore + ", starDate=" + starDate + ", userNo=" + userNo
				+ ", menuNo=" + menuNo + "]";
	}
	public int getStarNo() {
		return starNo;
	}
	public void setStarNo(int starNo) {
		this.starNo = starNo;
	}
	public double getStarScore() {
		return starScore;
	}
	public void setStarScore(double starScore) {
		this.starScore = starScore;
	}
	public Date getStarDate() {
		return starDate;
	}
	public void setStarDate(Date starDate) {
		this.starDate = starDate;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getMenuNo() {
		return menuNo;
	}
	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}
	
	
	
	
	
}
