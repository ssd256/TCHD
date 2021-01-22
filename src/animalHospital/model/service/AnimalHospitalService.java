package animalHospital.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import animalHospital.model.dao.AnimalHospitalDAO;
import animalHospital.model.vo.AnimalHospital;
import board.model.vo.PageInfo;

public class AnimalHospitalService {

	public int getAllListCount() {
		Connection conn = getConnection();
		
		int result = new AnimalHospitalDAO().getAllListCount(conn);
		
		close(conn);
		
		return result;
	}
	
	public ArrayList<AnimalHospital> allHospitalList(PageInfo pi) {
		Connection conn =getConnection();
		
		ArrayList<AnimalHospital> hospitalList = new AnimalHospitalDAO().allHospitalList(conn, pi);
		
		close(conn);
		
		return hospitalList;
	}
	
	public int getselectListCount(String addr) {
		Connection conn = getConnection();
		
		int result = new AnimalHospitalDAO().getselectListCount(conn, addr);
		
		close(conn);
		
		return result;
	}
	
	public ArrayList<AnimalHospital> selectAddr(PageInfo pi, String addr) {
		Connection conn = getConnection();
		
		ArrayList<AnimalHospital> hospitalList = new AnimalHospitalDAO().selectAddr(conn, pi, addr);
		
		close(conn);
		
		return hospitalList;
	}
	
	public AnimalHospital selectHospital(int hosNo) {
		Connection conn = getConnection();
		
		AnimalHospital hospital = new AnimalHospitalDAO().selectHospital(conn, hosNo);
		
		close(conn);
		
		return hospital;
	}
}
