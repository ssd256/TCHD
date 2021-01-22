package board.model.vo;

import java.sql.Date;

public class Files {
	private int fileNo;
	private int boNo;
	private int boType;
	private String orignName;
	private String changeName;
	private String filePath;
	private Date uploadDate;
	private int fileLevel;
	private int downloadCount;
	private String status;
	
	public Files() {}
	
	public Files(int boNo, String changeName) {
		this.boNo = boNo;
		this.changeName = changeName;
	}
	
	// 수정하기 
	public Files(int boNo, String orignName, String changeName, String filePath, int fileLevel) {
		super();
		this.boNo = boNo;
		this.orignName = orignName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.fileLevel = fileLevel;
	}
	
	
	// 목록보기
	public Files(int fileNo, int boNo, int boType, String orignName, String changeName, String filePath,
			Date uploadDate, int fileLevel, String status) {
		super();
		this.fileNo = fileNo;
		this.boNo = boNo;
		this.boType = boType;
		this.orignName = orignName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
		this.fileLevel = fileLevel;
		this.status = status;
	}
	
	
	public Files(int fileNo, int boNo, String orignName, String changeName, String filePath,
			Date uploadDate, int fileLevel, int downloadCount, String status) {
		super();
		this.fileNo = fileNo;
		this.boNo = boNo;
		this.orignName = orignName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
		this.fileLevel = fileLevel;
		this.downloadCount = downloadCount;
		this.status = status;
	}
	


	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
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

	public String getOrignName() {
		return orignName;
	}

	public void setOrignName(String orignName) {
		this.orignName = orignName;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getFileLevel() {
		return fileLevel;
	}

	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}

	public int getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

} // class end

