package board.model.vo;

import java.sql.Date;

public class Adopt {	// 입양게시판
	private int boNo;				// 게시판 번호
	private int boType;				// 게시판 분류번호
	private String cateName;		// 게시판 분류명
	private String id;				// 아이디
	private String petKinds;		// 동물 구분(개, 고양이)
	private String petCategory;		// 동물 품종
	private String petGender;		// 성별
	private String petUnigender;	// 중성화여부
	private String petName;			// 이름
	private String petAge;			// 나이
	private Date petRescueDate;		// 구조일시	--> 게시판 목록에서 보이게 하자
	private String rescueDate;		// 구조일시 	--> 수정게시판에서 쓸 예정(수정X)
	private float petWeight;		// 몸무게
	private String petColor;		// 색깔
	private String petSize;			// 크기
	private String petComment;		// 하고 싶은 말	 --> 게시판 상세보기에서 보이게 하자
	private String adoptYn;			// 입양 여부	-> N : 미입양상태(default),  Y : 입양상태(게시판에서 안보임)
	
	public Adopt() {}
	
	// 입양신청 --> adoptYn = Y로 바꿈
	public Adopt(int boNo, String adoptYn) {
		super();
		this.boNo = boNo;
		this.adoptYn = adoptYn;
	}
	
	
	// 입양게시판 수정하기1
	public Adopt(String petKinds, String petCategory, String petGender, String petUnigender, String petName,
			String petAge, String rescueDate, float petWeight, String petColor, String petSize, String petComment) {
		super();
		this.petKinds = petKinds;
		this.petCategory = petCategory;
		this.petGender = petGender;
		this.petUnigender = petUnigender;
		this.petName = petName;
		this.petAge = petAge;
		this.rescueDate = rescueDate;
		this.petWeight = petWeight;
		this.petColor = petColor;
		this.petSize = petSize;
		this.petComment = petComment;
	}
	

	// 입양게시판 수정하기2
	public Adopt(String petKinds, String petCategory, String petGender, String petUnigender, String petName,
			String petAge, Date petRescueDate, float petWeight, String petColor, String petSize, String petComment) {
		super();
		this.petKinds = petKinds;
		this.petCategory = petCategory;
		this.petGender = petGender;
		this.petUnigender = petUnigender;
		this.petName = petName;
		this.petAge = petAge;
		this.petRescueDate = petRescueDate;
		this.petWeight = petWeight;
		this.petColor = petColor;
		this.petSize = petSize;
		this.petComment = petComment;
	}
	
	// 입양게시판 목록
	public Adopt(int boNo, int boType, String cateName, String id, String petKinds, String petCategory, String petGender,
			String petUnigender, String petName, String petAge, Date petRescueDate, float petWeight, String petColor,
			String petSize, String petComment, String adoptYn) {
		super();
		this.boNo = boNo;
		this.boType = boType;
		this.cateName = cateName;
		this.id = id;
		this.petKinds = petKinds;
		this.petCategory = petCategory;
		this.petGender = petGender;
		this.petUnigender = petUnigender;
		this.petName = petName;
		this.petAge = petAge;
		this.petRescueDate = petRescueDate;
		this.petWeight = petWeight;
		this.petColor = petColor;
		this.petSize = petSize;
		this.petComment = petComment;
		this.adoptYn = adoptYn;
	}

	// 입양게시판 상세보기
	public Adopt(int boNo, int boType, String cateName, String id, String petKinds, String petCategory, String petGender,
			String petUnigender, String petName, String petAge, Date petRescueDate, float petWeight, String petColor,
			String petSize, String petComment) {
		super();
		this.boNo = boNo;
		this.boType = boType;
		this.cateName = cateName;
		this.id = id;
		this.petKinds = petKinds;
		this.petCategory = petCategory;
		this.petGender = petGender;
		this.petUnigender = petUnigender;
		this.petName = petName;
		this.petAge = petAge;
		this.petRescueDate = petRescueDate;
		this.petWeight = petWeight;
		this.petColor = petColor;
		this.petSize = petSize;
		this.petComment = petComment;
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
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPetKinds() {
		return petKinds;
	}

	public void setPetKinds(String petKinds) {
		this.petKinds = petKinds;
	}

	public String getPetCategory() {
		return petCategory;
	}

	public void setPetCategory(String petCategory) {
		this.petCategory = petCategory;
	}

	public String getPetGender() {
		return petGender;
	}

	public void setPetGender(String petGender) {
		this.petGender = petGender;
	}

	public String getPetUnigender() {
		return petUnigender;
	}

	public void setPetUnigender(String petUnigender) {
		this.petUnigender = petUnigender;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getPetAge() {
		return petAge;
	}

	public void setPetAge(String petAge) {
		this.petAge = petAge;
	}

	public Date getPetRescueDate() {
		return petRescueDate;
	}

	public void setPetRescueDate(Date petRescueDate) {
		this.petRescueDate = petRescueDate;
	}

	public float getPetWeight() {
		return petWeight;
	}
	
	public String getRescueDate() {
		return rescueDate;
	}

	public void setRescueDate(String rescueDate) {
		this.rescueDate = rescueDate;
	}

	public void setPetWeight(float petWeight) {
		this.petWeight = petWeight;
	}

	public String getPetColor() {
		return petColor;
	}

	public void setPetColor(String petColor) {
		this.petColor = petColor;
	}

	public String getPetSize() {
		return petSize;
	}

	public void setPetSize(String petSize) {
		this.petSize = petSize;
	}

	public String getPetComment() {
		return petComment;
	}

	public void setPetComment(String petComment) {
		this.petComment = petComment;
	}

	public String getAdoptYn() {
		return adoptYn;
	}

	public void setAdoptYn(String adoptYn) {
		this.adoptYn = adoptYn;
	}

} // class end
