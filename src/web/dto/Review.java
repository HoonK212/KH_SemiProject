package web.dto;

import java.util.Date;

public class Review {

	private int	reviewNo;
	private String reviewContent;
	private Date reviewDate;
	private int	reviewGood;
	private int	reviewBad;
	private String reviewReport;
	private int	userNo;
	private int	menuNo;
	@Override
	public String toString() {
		return "Review [reviewNo=" + reviewNo + ", reviewContent=" + reviewContent + ", reviewDate=" + reviewDate
				+ ", reviewGood=" + reviewGood + ", reviewBad=" + reviewBad + ", reviewReport=" + reviewReport
				+ ", userNo=" + userNo + ", menuNo=" + menuNo + "]";
	}
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public Date getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	public int getReviewGood() {
		return reviewGood;
	}
	public void setReviewGood(int reviewGood) {
		this.reviewGood = reviewGood;
	}
	public int getReviewBad() {
		return reviewBad;
	}
	public void setReviewBad(int reviewBad) {
		this.reviewBad = reviewBad;
	}
	public String getReviewReport() {
		return reviewReport;
	}
	public void setReviewReport(String reviewReport) {
		this.reviewReport = reviewReport;
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
