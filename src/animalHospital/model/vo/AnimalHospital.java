package animalHospital.model.vo;

public class AnimalHospital {
	private int hos_no; // 병원 번호
	private String hos_name; // 병원 이름
	private String hos_addr; // 병원 주소
	private String hos_phone; // 병원 전화번호
	
	public AnimalHospital() {}

	public AnimalHospital(int hos_no, String hos_name, String hos_addr, String hos_phone) {
		super();
		this.hos_no = hos_no;
		this.hos_name = hos_name;
		this.hos_addr = hos_addr;
		this.hos_phone = hos_phone;
	}

	public int getHos_no() {
		return hos_no;
	}

	public void setHos_no(int hos_no) {
		this.hos_no = hos_no;
	}

	public String getHos_name() {
		return hos_name;
	}

	public void setHos_name(String hos_name) {
		this.hos_name = hos_name;
	}

	public String getHos_addr() {
		return hos_addr;
	}

	public void setHos_addr(String hos_addr) {
		this.hos_addr = hos_addr;
	}

	public String getHos_phone() {
		return hos_phone;
	}

	public void setHos_phone(String hos_phone) {
		this.hos_phone = hos_phone;
	}

	@Override
	public String toString() {
		return "Hospital [hos_no=" + hos_no + ", hos_name=" + hos_name + ", hos_addr=" + hos_addr + ", hos_phone="
				+ hos_phone + "]";
	}
}
