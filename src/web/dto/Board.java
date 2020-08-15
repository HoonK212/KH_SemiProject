package web.dto;

import java.util.Date;

public class Board {

	private int	bdNo;
	private String bdTitle;
	private String bdQuestion;
	private String bdAnswer;
	private Date bdDate;
	private int	bdCnt;
	private String bdGrp;
	private String bdYn;
	private int	userNo;
	@Override
	public String toString() {
		return "Board [bdNo=" + bdNo + ", bdTitle=" + bdTitle + ", bdQuestion=" + bdQuestion + ", bdAnswer=" + bdAnswer
				+ ", bdDate=" + bdDate + ", bdCnt=" + bdCnt + ", bdGrp=" + bdGrp + ", bdYn=" + bdYn + ", userNo="
				+ userNo + "]";
	}
	public int getBdNo() {
		return bdNo;
	}
	public void setBdNo(int bdNo) {
		this.bdNo = bdNo;
	}
	public String getBdTitle() {
		return bdTitle;
	}
	public void setBdTitle(String bdTitle) {
		this.bdTitle = bdTitle;
	}
	public String getBdQuestion() {
		return bdQuestion;
	}
	public void setBdQuestion(String bdQuestion) {
		this.bdQuestion = bdQuestion;
	}
	public String getBdAnswer() {
		return bdAnswer;
	}
	public void setBdAnswer(String bdAnswer) {
		this.bdAnswer = bdAnswer;
	}
	public Date getBdDate() {
		return bdDate;
	}
	public void setBdDate(Date bdDate) {
		this.bdDate = bdDate;
	}
	public int getBdCnt() {
		return bdCnt;
	}
	public void setBdCnt(int bdCnt) {
		this.bdCnt = bdCnt;
	}
	public String getBdGrp() {
		return bdGrp;
	}
	public void setBdGrp(String bdGrp) {
		this.bdGrp = bdGrp;
	}
	public String getBdYn() {
		return bdYn;
	}
	public void setBdYn(String bdYn) {
		this.bdYn = bdYn;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
	
}
