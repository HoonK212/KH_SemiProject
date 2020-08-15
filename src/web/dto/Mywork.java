package web.dto;

import java.util.Date;

public class Mywork {
	
	private int menuNo;
	private String menuName;
	private double starScore;
	private String reviewContent;
	private int usersNo;
	private String usersNick;
	private Date writtendate;
	
	
	public int getMenuNo() {
		return menuNo;
	}
	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public double getStarScore() {
		return starScore;
	}
	public void setStarScore(double starScore) {
		this.starScore = starScore;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public int getUsersNo() {
		return usersNo;
	}
	public void setUsersNo(int usersNo) {
		this.usersNo = usersNo;
	}
	public String getUsersNick() {
		return usersNick;
	}
	public void setUsersNick(String usersNick) {
		this.usersNick = usersNick;
	}
	public Date getWrittendate() {
		return writtendate;
	}
	public void setWrittendate(Date writtendate) {
		this.writtendate = writtendate;
	}
	
	
	@Override
	public String toString() {
		return "Mywork [menuNo=" + menuNo + ", menuName=" + menuName + ", starScore=" + starScore + ", reviewContent="
				+ reviewContent + ", usersNo=" + usersNo + ", usersNick=" + usersNick + ", writtendate=" + writtendate
				+ "]";
	}
	
	
}
