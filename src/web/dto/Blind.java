package web.dto;

import java.util.Date;

public class Blind {

	private int	blindNo;
	private String blindRsn;
	private Date blindDate;
	private Date blindStart;
	private String blindYn;
	private String blindNote;
	private int menuNo;
	@Override
	public String toString() {
		return "Blind [blindNo=" + blindNo + ", blindRsn=" + blindRsn + ", blindDate=" + blindDate + ", blindStart="
				+ blindStart + ", blindYn=" + blindYn + ", blindNote=" + blindNote + ", menuNo=" + menuNo + "]";
	}
	public int getBlindNo() {
		return blindNo;
	}
	public void setBlindNo(int blindNo) {
		this.blindNo = blindNo;
	}
	public String getBlindRsn() {
		return blindRsn;
	}
	public void setBlindRsn(String blindRsn) {
		this.blindRsn = blindRsn;
	}
	public Date getBlindDate() {
		return blindDate;
	}
	public void setBlindDate(Date blindDate) {
		this.blindDate = blindDate;
	}
	public Date getBlindStart() {
		return blindStart;
	}
	public void setBlindStart(Date blindStart) {
		this.blindStart = blindStart;
	}
	public String getBlindYn() {
		return blindYn;
	}
	public void setBlindYn(String blindYn) {
		this.blindYn = blindYn;
	}
	public String getBlindNote() {
		return blindNote;
	}
	public void setBlindNote(String blindNote) {
		this.blindNote = blindNote;
	}
	public int getMenuNo() {
		return menuNo;
	}
	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}
	
	
	
	
}
