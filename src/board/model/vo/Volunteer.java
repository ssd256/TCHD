package board.model.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class Volunteer {
	private int boNo;				// 게시글 번호
	private int boType;				// 게시판 분류번호
	private String cateName;		// 게시판 분류명
	private String boTitle;
	private int boCount;
	private Date boDate;
	private int memNo;				// 회원번호
	private String memId;			// 아이디
	private String boDeleteYn;
	private int voMaxmember;		// 정원
	private int voApplymember;		// 신청인원
	private String voDeadline;		// 마감유무
	private Timestamp voDate;			// 봉사일시;
	private String voArea;			// 지역선택
	private String voPlace;			// 봉사지
	private String voComment;		// 내용
	
	public Volunteer(int boNo, int boType, String cateName, String boTitle, int boCount, Date boDate, int memNo,
			String memId, String boDeleteYn, int voMaxmember, int voApplymember, String voDeadline, Timestamp voDate,
			String voArea, String voPlace, String voComment) {
		super();
		this.boNo = boNo;
		this.boType = boType;
		this.cateName = cateName;
		this.boTitle = boTitle;
		this.boCount = boCount;
		this.boDate = boDate;
		this.memNo = memNo;
		this.memId = memId;
		this.boDeleteYn = boDeleteYn;
		this.voMaxmember = voMaxmember;
		this.voApplymember = voApplymember;
		this.voDeadline = voDeadline;
		this.voDate = voDate;
		this.voArea = voArea;
		this.voPlace = voPlace;
		this.voComment = voComment;
	}

	public Volunteer(int boNo, String cateName, String boTitle, Timestamp voDate, String voPlace, Date boDate, int voMaxmember, String voComment) {
		super();
		this.boNo = boNo;
		this.cateName = cateName;
		this.boTitle = boTitle;
		this.voDate = voDate;
		this.voPlace = voPlace;
		this.boDate = boDate;
		this.voMaxmember = voMaxmember;
		this.voComment = voComment;
	}
	
	public Volunteer(int boNo, String cateName, String boTitle, Timestamp voDate, Date boDate, int voMaxmember, String voComment) {
		super();
		this.boNo = boNo;
		this.cateName = cateName;
		this.boTitle = boTitle;
		this.voDate = voDate;
		this.boDate = boDate;
		this.voMaxmember = voMaxmember;
		this.voComment = voComment;
	}
	
	public Volunteer(int boNo, int boType, String cateName, String boTitle, String boDeleteYn, int memNo, String memId,
			Date boDate, int voMaxmember, int voApplymember, Timestamp voDate, String voArea) {
		super();
		this.boNo = boNo;
		this.boType = boType;
		this.cateName = cateName;
		this.boTitle = boTitle;
		this.boDate = boDate;
		this.memNo = memNo;
		this.memId = memId;
		this.boDeleteYn = boDeleteYn;
		this.voMaxmember = voMaxmember;
		this.voApplymember = voApplymember;
		this.voDate = voDate;
		this.voArea = voArea;
	}
	
	public Volunteer(int boNo, int boType, String cateName, String voArea, String boTitle, int memNo, String memId, Date boDate, 
			 int voMaxmember, int voApplymember, Timestamp voDate, int boCount, String voComment, String voPlace, String voDeadLine) {
		super();
		this.boNo = boNo;
		this.boType = boType;
		this.cateName = cateName;
		this.voArea = voArea;
		this.boTitle = boTitle;
		this.memNo = memNo;
		this.memId = memId;
		this.boDate = boDate;
		this.voMaxmember = voMaxmember;
		this.voApplymember = voApplymember;
		this.voDate = voDate;
		this.boCount = boCount;
		this.voComment = voComment;
		this.voPlace = voPlace;
		this.voDeadline=voDeadLine;
	}
	
	public Volunteer(int boNo, int boType, String cateName, String voArea, String boTitle, int memNo, String memId, Date boDate, 
			 int voMaxmember, int voApplymember, Timestamp voDate, int boCount) {
		super();
		this.boNo = boNo;
		this.boType = boType;
		this.cateName = cateName;
		this.voArea = voArea;
		this.boTitle = boTitle;
		this.memNo = memNo;
		this.memId = memId;
		this.boDate = boDate;
		this.voMaxmember = voMaxmember;
		this.voApplymember = voApplymember;
		this.voDate = voDate;
		this.boCount = boCount;
	}
	public Volunteer(int boNo, String boTitle, String voArea, Timestamp voDate, String voPlace, int voMaxmember, String voComment, int boType) {
		super();
		this.boNo = boNo;
		this.boTitle = boTitle;
		this.voArea = voArea;
		this.voDate = voDate;
		this.voPlace = voPlace;
		this.voMaxmember = voMaxmember;
		this.voComment = voComment;
		this.boType = boType;
	}

	public Volunteer(int boNo, String boTitle, String voArea, Timestamp voDate, String voPlace, int voMaxmember, String voComment, String cateName, int memNo) {
		super();
		this.boNo = boNo;
		this.boTitle = boTitle;
		this.voArea = voArea;
		this.voDate = voDate;
		this.voPlace = voPlace;
		this.voMaxmember = voMaxmember;
		this.voComment = voComment;
		this.cateName = cateName;
		this.memNo = memNo;
	}
	
	// 봉사 신청.
	public Volunteer(int boNo, int memNo) {
		super();
		this.boNo = boNo;
		this.memNo = memNo;
	}

	public int getBoNo() {
		return boNo;
	}

	public void setBoNo(int boNo) {
		this.boNo = boNo;
	}

	public int getBoType() {
		return boType;
	}

	public void setBoType(int boType) {
		this.boType = boType;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getBoTitle() {
		return boTitle;
	}

	public void setBoTitle(String boTitle) {
		this.boTitle = boTitle;
	}

	public int getBoCount() {
		return boCount;
	}

	public void setBoCount(int boCount) {
		this.boCount = boCount;
	}

	public Date getBoDate() {
		return boDate;
	}

	public void setBoDate(Date boDate) {
		this.boDate = boDate;
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

	public String getBoDeleteYn() {
		return boDeleteYn;
	}

	public void setBoDeleteYn(String boDeleteYn) {
		this.boDeleteYn = boDeleteYn;
	}

	public int getVoMaxmember() {
		return voMaxmember;
	}

	public void setVoMaxmember(int voMaxmember) {
		this.voMaxmember = voMaxmember;
	}

	public int getVoApplymember() {
		return voApplymember;
	}

	public void setVoApplymember(int voApplymember) {
		this.voApplymember = voApplymember;
	}

	public String getVoDeadline() {
		return voDeadline;
	}

	public void setVoDeadline(String voDeadline) {
		this.voDeadline = voDeadline;
	}

	public Timestamp getVoDate() {
		return voDate;
	}

	public void setVoDate(Timestamp voDate) {
		this.voDate = voDate;
	}

	public String getVoArea() {
		return voArea;
	}

	public void setVoArea(String voArea) {
		this.voArea = voArea;
	}

	public String getVoPlace() {
		return voPlace;
	}

	public void setVoPlace(String voPlace) {
		this.voPlace = voPlace;
	}

	public String getVoComment() {
		return voComment;
	}

	public void setVoComment(String voComment) {
		this.voComment = voComment;
	}

	@Override
	public String toString() {
		return "Volunteer [boNo=" + boNo + ", boType=" + boType + ", cateName=" + cateName + ", boTitle=" + boTitle
				+ ", boCount=" + boCount + ", boDate=" + boDate + ", memNo=" + memNo + ", memId=" + memId
				+ ", boDeleteYn=" + boDeleteYn + ", voMaxmember=" + voMaxmember + ", voApplymember=" + voApplymember
				+ ", voDeadline=" + voDeadline + ", voDate=" + voDate + ", voArea=" + voArea + ", voPlace=" + voPlace
				+ ", voComment=" + voComment + "]";
	}
}