package board.model.vo;

import java.sql.Date;

public class Comments {
	private int comNo;			// 댓글번호
	private int memNo;			// 회원번호
	private int boNo;			// 게시글번호
	private String comContent;	// 댓글내용
	private Date comDate;		// 댓글작성일자
	private String comDelete;	// 댓글삭제여부
	private String memId;		// 아이디
	///
	
	public Comments() {}
	
	public Comments(int comNo, int memNo, String memId, int boNo, String comContent, Date comDate, String comDelete) {
		super();
		this.comNo = comNo;
		this.memNo = memNo;
		this.memId = memId;
		this.boNo = boNo;
		this.comContent = comContent;
		this.comDate = comDate;
		this.comDelete = comDelete;
	}
	
	public Comments(int comNo, String comContent, int boNo, String memId, Date comDate, String comDelete) {
		super();
		this.comNo = comNo;
		this.comContent = comContent;
		this.boNo = boNo;
		this.memId = memId;
		this.comDate = comDate;
		this.comDelete = comDelete;
	}

	public Comments(int comNo, String comContent, int boNo, int memNo, String memId, Date comDate, String comDelete) {
		super();
		this.comNo = comNo;
		this.comContent = comContent;
		this.boNo = boNo;
		this.memNo = memNo;
		this.memId = memId;
		this.comDate = comDate;
		this.comDelete = comDelete;
	}
	
	public int getComNo() {
		return comNo;
	}
	public void setComNo(int comNo) {
		this.comNo = comNo;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public int getBoNo() {
		return boNo;
	}
	public void setBoNo(int boNo) {
		this.boNo = boNo;
	}
	public String getComContent() {
		return comContent;
	}
	public void setComContent(String comContent) {
		this.comContent = comContent;
	}
	public Date getComDate() {
		return comDate;
	}
	public void setComDate(Date comDate) {
		this.comDate = comDate;
	}
	public String getComDelete() {
		return comDelete;
	}
	public void setComDelete(String comDelete) {
		this.comDelete = comDelete;
	}

	@Override
	public String toString() {
		return "Comments [comNo=" + comNo + ", memNo=" + memNo + ", memId=" + memId + ", boNo=" + boNo + ", comContent="
				+ comContent + ", comDate=" + comDate + ", comDelete=" + comDelete + "]";
	}
}
