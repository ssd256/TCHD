package member.model.vo;

import java.sql.Date;

public class Member {
	private int mem_no;
	private String mem_type;
	private String mem_id;
	private String mem_pw;
	private String mem_name;
	private String mem_phone;
	private String mem_addr;
	private String mem_email;
	private String mem_leave;
	
	private Date pm_birth;
	private String pm_volist;
	
	private String gm_regno;
	private String gm_name;
	private String gm_ok_ny;
	
	
	public Member() {
		
	}
	

	
	public Member(String mem_id, String mem_pw) {
		super();
		this.mem_id = mem_id;
		this.mem_pw = mem_pw;
	}


	public Member(int mem_no,String mem_pw ,String mem_name, String mem_phone, String mem_addr, String mem_email, Date pm_birth) {
		super();
		this.mem_no = mem_no;
		this.mem_pw = mem_pw;
		this.mem_name = mem_name;
		this.mem_phone = mem_phone;
		this.mem_addr = mem_addr;
		this.mem_email = mem_email;
		this.pm_birth = pm_birth;
	}
 
	


	public Member(int mem_no,String mem_type, String mem_id, String mem_pw, String mem_name, String mem_phone, String mem_addr,
			String mem_email, Date pm_birth) {
		super();
		this.mem_no=mem_no;
		this.mem_type = mem_type;
		this.mem_id = mem_id;
		this.mem_pw = mem_pw;
		this.mem_name = mem_name;
		this.mem_phone = mem_phone;
		this.mem_addr = mem_addr;
		this.mem_email = mem_email;
		this.pm_birth = pm_birth;
	}

	



	public Member(int mem_no,String mem_pw ,String mem_name, String mem_phone, String mem_addr, String mem_email, String gm_regno,
			String gm_name) {
		super();
		this.mem_no = mem_no;
		this.mem_pw= mem_pw;
		this.mem_name = mem_name;
		this.mem_phone = mem_phone;
		this.mem_addr = mem_addr;
		this.mem_email = mem_email;
		this.gm_regno = gm_regno;
		this.gm_name = gm_name;
	}



	public Member(int mem_no, String mem_type, String mem_id, String mem_pw, String mem_name, String mem_phone, String mem_addr,
			String mem_email, String gm_regno, String gm_name) {
		super();
		this.mem_no = mem_no;
		this.mem_type = mem_type;
		this.mem_id = mem_id;
		this.mem_pw = mem_pw;
		this.mem_name = mem_name;
		this.mem_phone = mem_phone;
		this.mem_addr = mem_addr;
		this.mem_email = mem_email;
		this.gm_regno = gm_regno;
		this.gm_name = gm_name;
	}
	
	
	public Member(int mem_no, String mem_id, String mem_name, String mem_email) {
		super();
		this.mem_no = mem_no;
		this.mem_id = mem_id;
		this.mem_name = mem_name;
		this.mem_email = mem_email;
	}
	
	public Member(int mem_no, String mem_id, String mem_email) {
		super();
		this.mem_no = mem_no;
		this.mem_id = mem_id;
		this.mem_email = mem_email;
	}
	

	public int getMem_no() {
		return mem_no;
	}


	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}


	public String getMem_type() {
		return mem_type;
	}


	public void setMem_type(String mem_type) {
		this.mem_type = mem_type;
	}


	public String getMem_id() {
		return mem_id;
	}


	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}


	public String getMem_pw() {
		return mem_pw;
	}


	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}


	public String getMem_name() {
		return mem_name;
	}


	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}


	public String getMem_phone() {
		return mem_phone;
	}


	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}


	public String getMem_addr() {
		return mem_addr;
	}


	public void setMem_addr(String mem_addr) {
		this.mem_addr = mem_addr;
	}


	public String getMem_email() {
		return mem_email;
	}


	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}


	public String getMem_leave() {
		return mem_leave;
	}


	public void setMem_leave(String mem_leave) {
		this.mem_leave = mem_leave;
	}


	public Date getPm_birth() {
		return pm_birth;
	}


	public void setPm_birth(Date pm_birth) {
		this.pm_birth = pm_birth;
	}


	public String getPm_volist() {
		return pm_volist;
	}


	public void setPm_volist(String pm_volist) {
		this.pm_volist = pm_volist;
	}


	public String getGm_regno() {
		return gm_regno;
	}


	public void setGm_regno(String gm_regno) {
		this.gm_regno = gm_regno;
	}


	public String getGm_name() {
		return gm_name;
	}


	public void setGm_name(String gm_name) {
		this.gm_name = gm_name;
	}


	public String getGm_ok_ny() {
		return gm_ok_ny;
	}


	public void setGm_ok_ny(String gm_ok_ny) {
		this.gm_ok_ny = gm_ok_ny;
	}



	@Override
	public String toString() {
		return "Member [mem_no=" + mem_no + ", mem_type=" + mem_type + ", mem_id=" + mem_id + ", mem_pw=" + mem_pw
				+ ", mem_name=" + mem_name + ", mem_phone=" + mem_phone + ", mem_addr=" + mem_addr + ", mem_email="
				+ mem_email + ", mem_leave=" + mem_leave + ", pm_birth=" + pm_birth + ", pm_volist=" + pm_volist
				+ ", gm_regno=" + gm_regno + ", gm_name=" + gm_name + ", gm_ok_ny=" + gm_ok_ny + "]";
	}




		
	
}