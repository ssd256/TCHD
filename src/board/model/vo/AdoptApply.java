package board.model.vo;

public class AdoptApply {			// 입양신청서 작성 폼
	private int applyNo;			// 신청서 번호
	private String applyContent;	// 신청서 내용
	private int memNo;				// 회원번호
	private int boNo;				// 게시판번호  --> 입양상태 된 게시글은 안보여야 해서 가져옴
	
	public AdoptApply(int applyNo, String applyContent, int memNo, int boNo) {
		super();
		this.applyNo = applyNo;
		this.applyContent = applyContent;
		this.memNo = memNo;
		this.boNo = boNo;
	}
	
	public AdoptApply(String applyContent, int memNo) {
		super();
		this.applyContent = applyContent;
		this.memNo = memNo;
	}



	public int getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(int applyNo) {
		this.applyNo = applyNo;
	}

	public String getApplyContent() {
		return applyContent;
	}

	public void setApplyContent(String applyContent) {
		this.applyContent = applyContent;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public int getBoNo() {
		return boNo;
	}

	public void setBoNo(int boNo) {
		this.boNo = boNo;
	}

	@Override
	public String toString() {
		return "AdoptApply [applyNo=" + applyNo + ", applyContent=" + applyContent + ", memNo=" + memNo + ", boNo="
				+ boNo + "]";
	}
	
} // class end
