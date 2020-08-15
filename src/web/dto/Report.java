package web.dto;

import java.util.Date;

public class Report {

	private int	rptNo;
	private int	rptRsn;
	private String rptDetail;
	private Date rptDate;
	private String rptYn;
	private int	rptManage;
	private String rptNote;
	private int	rptUser;
	private int	rptTarget;
	private int	reviewNo;
	@Override
	public String toString() {
		return "Report [rptNo=" + rptNo + ", rptRsn=" + rptRsn + ", rptDetail=" + rptDetail + ", rptDate=" + rptDate
				+ ", rptYn=" + rptYn + ", rptManage=" + rptManage + ", rptNote=" + rptNote + ", rptUser=" + rptUser
				+ ", rptTarget=" + rptTarget + ", reviewNo=" + reviewNo + "]";
	}
	public int getRptNo() {
		return rptNo;
	}
	public void setRptNo(int rptNo) {
		this.rptNo = rptNo;
	}
	public int getRptRsn() {
		return rptRsn;
	}
	public void setRptRsn(int rptRsn) {
		this.rptRsn = rptRsn;
	}
	public String getRptDetail() {
		return rptDetail;
	}
	public void setRptDetail(String rptDetail) {
		this.rptDetail = rptDetail;
	}
	public Date getRptDate() {
		return rptDate;
	}
	public void setRptDate(Date rptDate) {
		this.rptDate = rptDate;
	}
	public String getRptYn() {
		return rptYn;
	}
	public void setRptYn(String rptYn) {
		this.rptYn = rptYn;
	}
	public int getRptManage() {
		return rptManage;
	}
	public void setRptManage(int rptManage) {
		this.rptManage = rptManage;
	}
	public String getRptNote() {
		return rptNote;
	}
	public void setRptNote(String rptNote) {
		this.rptNote = rptNote;
	}
	public int getRptUser() {
		return rptUser;
	}
	public void setRptUser(int rptUser) {
		this.rptUser = rptUser;
	}
	public int getRptTarget() {
		return rptTarget;
	}
	public void setRptTarget(int rptTarget) {
		this.rptTarget = rptTarget;
	}
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	
	
	
}
