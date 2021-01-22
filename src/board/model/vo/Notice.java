package board.model.vo;

import java.sql.Date;

public class Notice {
	private int boNo;
	private String boTitle;
	private String boContent;
	private Date boDate;
	private int boCount;
	private String boDeleteYn;
	private String noticeSubject;
	private int rNum;
	
	
	
	public Notice() {
		
	}


	public Notice(int boNo, String boTitle, String boContent, Date boDate, int boCount, String boDeleteYn,
			String noticeSubject, int rNum) {
		super();
		this.boNo = boNo;
		this.boTitle = boTitle;
		this.boContent = boContent;
		this.boDate = boDate;
		this.boCount = boCount;
		this.boDeleteYn = boDeleteYn;
		this.noticeSubject = noticeSubject;
		this.rNum=rNum;

	}


	public int getrNum() {
		return rNum;
	}


	public void setrNum(int rNum) {
		this.rNum = rNum;
	}


	public int getBoNo() {
		return boNo;
	}


	public void setBoNo(int boNo) {
		this.boNo = boNo;
	}



	public String getBoTitle() {
		return boTitle;
	}


	public void setBoTitle(String boTitle) {
		this.boTitle = boTitle;
	}


	public String getBoContent() {
		return boContent;
	}


	public void setBoContent(String boContent) {
		this.boContent = boContent;
	}


	public Date getBoDate() {
		return boDate;
	}


	public void setBoDate(Date boDate) {
		this.boDate = boDate;
	}


	public int getBoCount() {
		return boCount;
	}


	public void setBoCount(int boCount) {
		this.boCount = boCount;
	}


	public String getBoDeleteYn() {
		return boDeleteYn;
	}


	public void setBoDeleteYn(String boDeleteYn) {
		this.boDeleteYn = boDeleteYn;
	}


	public String getNoticeSubject() {
		return noticeSubject;
	}


	public void setNoticeSubject(String noticeSubject) {
		this.noticeSubject = noticeSubject;
	}


	@Override
	public String toString() {
		return "Notice [boNo=" + boNo + ", boTitle=" + boTitle + ", boContent=" + boContent
				+ ", boDate=" + boDate + ", boCount=" + boCount + ", boDeleteYn=" + boDeleteYn + ", noticeSubject="
				+ noticeSubject + "]";
	}




	
	
	
}
