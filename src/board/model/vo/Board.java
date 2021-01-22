package board.model.vo;

import java.sql.Date;

public class Board {
	private int boNo;
	private String boType;
	private String boTitle;
	private String boContent;
	private Date boDate;
	private int boCount;
	private String boDeleteYn;
	private int memNo;
	private int cateNo;
	
	public Board() {}
	
	public Board(int boNo, String boType, String boTitle,  Date boDate) {
		super();
		this.boNo = boNo;
		this.boType = boType;
		this.boTitle = boTitle;
		this.boDate = boDate;
	}
	
	public Board(int boNo, String boType, String boTitle, String boContent, Date boDate, int boCount, String boDeleteYn,
			int memNo) {
		super();
		this.boNo = boNo;
		this.boType = boType;
		this.boTitle = boTitle;
		this.boContent = boContent;
		this.boDate = boDate;
		this.boCount = boCount;
		this.boDeleteYn = boDeleteYn;
		this.memNo = memNo;
	}
	
	public Board(int boNo, String boType, String boTitle, String boContent, Date boDate, int boCount, String boDeleteYn,
			int memNo, int cateNo) {
		super();
		this.boNo = boNo;
		this.boType = boType;
		this.boTitle = boTitle;
		this.boContent = boContent;
		this.boDate = boDate;
		this.boCount = boCount;
		this.boDeleteYn = boDeleteYn;
		this.memNo = memNo;
		this.cateNo = cateNo;
	}

	
	// 입양게시판 수정
	public Board(int boNo, String boType, String boTitle, String boContent, int memNo) {
		super();
		this.boNo = boNo;
		this.boType = boType;
		this.boTitle = boTitle;
		this.boContent = boContent;
		this.memNo = memNo;
	}

	public int getBoNo() {
		return boNo;
	}

	public void setBoNo(int boNo) {
		this.boNo = boNo;
	}

	public String getBoType() {
		return boType;
	}

	public void setBoType(String boType) {
		this.boType = boType;
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

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public int getCateNo() {
		return cateNo;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}

	
	
} // class end
