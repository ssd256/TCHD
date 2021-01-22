package support.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import animalHospital.model.vo.AnimalHospital;
import board.model.vo.PageInfo;
import support.model.vo.Support;

public class SupportDAO {
	Properties prop = new Properties();
	
	public SupportDAO() {
		String fileName = SupportDAO.class.getResource("/sql/support/support-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int selectMemNo(Connection conn, Support support) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int mem_no = 0;
		
		String query = prop.getProperty("selectMemNo");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, support.getMem_id());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				mem_no = rset.getInt("mem_no");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return mem_no;
	}
	
	public int applyMem(Connection conn, Support support) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("applyMem");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, support.getMem_no());
			pstmt.setInt(2, support.getSup_price());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int applyNonMem(Connection conn, Support support) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("applyNonMem");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, support.getSup_price());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectSupNo(Connection conn, Support support) {
		Statement stmt = null;
		ResultSet rset = null;
		int sup_no = 0;
		
		String query = prop.getProperty("selectSupNo");
		
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				sup_no = rset.getInt("MAX(SUP_NO)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return sup_no;
	}

	public int checkSupNo(Connection conn, int supNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int check = 0;
		
		String query = prop.getProperty("checkSupNo");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, supNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				check = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return check;
	}

	public int[] getListCount(Connection conn, int mem_no, Date searchDate) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int[] listTotal = new int[2];
		
		String query = prop.getProperty("getListCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mem_no);
			pstmt.setDate(2, searchDate);
			pstmt.setDate(3, searchDate);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listTotal[0] = rset.getInt(1); // COUNT(*)
				listTotal[1] = rset.getInt(2); // SUM(SUP_PRICE)
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listTotal;
	}
	
	public Support selectListNonMem(Connection conn, String supNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Support support = null;
		
		int supNo_int = Integer.parseInt(supNo);
		String query = prop.getProperty("selectListNonMem");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, supNo_int);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				support = new Support(rset.getInt("sup_no"), 
						rset.getInt("sup_price"), 
						rset.getDate("sup_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return support;
	}
	
	public ArrayList<Support> selectListMem(Connection conn, int mem_no, Date searchDate, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
//		ArrayList<Support> supportList = new ArrayList<Support>();
		ArrayList<Support> supportList = null;
		
		String query = prop.getProperty("selectListMem");

		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mem_no);
			pstmt.setDate(2, searchDate);
			pstmt.setDate(3, searchDate);
			pstmt.setInt(4, startRow);
			pstmt.setInt(5, endRow);
			
			rset = pstmt.executeQuery();
			
			supportList = new ArrayList<Support>();
			
			while(rset.next()) {
				Support support = new Support(rset.getInt("sup_no"), 
											  rset.getInt("sup_price"), 
											  rset.getDate("sup_date"));
				
				supportList.add(support);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return supportList;
	}
}
