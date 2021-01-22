package member.model.dao;
import static common.JDBCTemplate.close;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import board.model.vo.PageInfo;
import member.model.vo.Member;

public class MemberDAO {
	Properties prop =new Properties();
	
	public MemberDAO() {
		String fileName = MemberDAO.class.getResource("/sql/member/member-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt= conn.prepareStatement(prop.getProperty("insertMember"));
			pstmt.setString(1, member.getMem_type());
			pstmt.setString(2, member.getMem_id());
			pstmt.setString(3, member.getMem_pw());
			pstmt.setString(4, member.getMem_name());
			pstmt.setString(5, member.getMem_phone());
			pstmt.setString(6, member.getMem_addr());
			pstmt.setString(7, member.getMem_email());
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int insertMember_2Phase(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result =0;
		try {
			if(member.getMem_type().equals("PM")) {
				pstmt = conn.prepareStatement(prop.getProperty("insertMemberPm"));
				pstmt.setDate(1, member.getPm_birth());
				result = pstmt.executeUpdate();
			}else {
				pstmt = conn.prepareStatement(prop.getProperty("insertMemberGm"));
				pstmt.setString(1, member.getGm_regno());
				pstmt.setString(2, member.getGm_name());
				result = pstmt.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public Member login(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member loginUser = null;
		
		String query = prop.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMem_id());
			pstmt.setString(2, member.getMem_pw());
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				loginUser = new Member(rset.getInt("MEM_NO"),
									   rset.getString("MEM_TYPE"), 
									   rset.getString("MEM_ID"),
									   null, 	
									   rset.getString("MEM_NAME"),
									   rset.getString("MEM_PHONE"),
									   null,
									   null,
									   null
									   );
				if(rset.getString("MEM_TYPE").equals("GM")) {
					loginUser.setGm_ok_ny(rset.getString("GM_OK_YN"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return loginUser;
	}

	public int checkId(Connection conn, String inputId) {
		PreparedStatement pstmt=null;
		ResultSet rset = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("checkId"));
			pstmt.setString(1, inputId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result=rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public int confirmPw(Connection conn, String loginUserId,String inputPw) {
		PreparedStatement pstmt = null;
		ResultSet rset=null;
		int result = 0;
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("confirmPw"));
			pstmt.setString(1, loginUserId);
			pstmt.setString(2, inputPw);
			rset= pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public Member selectMemberPm(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectMemberPm"));
			pstmt.setString(1, memberId);
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				member = new Member(
							rset.getInt("MEM_NO"),
							rset.getString("MEM_PW"),
							rset.getString("MEM_NAME"),
							rset.getString("MEM_PHONE"),
							rset.getString("MEM_ADDR"),
							rset.getString("MEM_EMAIL"),
							rset.getDate("PM_BIRTH")
						);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return member;
	}

	public Member selectMemberGm(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectMemberGm"));
			pstmt.setString(1, memberId);
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				member = new Member(
							rset.getInt("MEM_NO"),
							rset.getString("MEM_PW"),
							rset.getString("MEM_NAME"),
							rset.getString("MEM_PHONE"),
							rset.getString("MEM_ADDR"),
							rset.getString("MEM_EMAIL"),
							rset.getString("GM_REGNO"),
							rset.getString("GM_NAME")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return member;
	}
	
	
	public int updateMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("updateMember"));
			pstmt.setString(1, member.getMem_pw());
			pstmt.setString(2, member.getMem_name());
			pstmt.setString(3, member.getMem_phone());
			pstmt.setString(4, member.getMem_addr());
			pstmt.setString(5, member.getMem_email());
			pstmt.setString(6, member.getMem_id());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	

	public int updateMemberPm(Connection conn, Member member) {
		PreparedStatement pstmt= null;
		int result = 0;
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("updateMemberPm"));
			pstmt.setDate(1, member.getPm_birth());
			pstmt.setInt(2, member.getMem_no());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
		
	}

	public int updateMemberGm(Connection conn, Member member) {
		PreparedStatement pstmt= null;
		int result = 0;
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("updateMemberGm"));
			pstmt.setString(1, member.getGm_regno());
			pstmt.setString(2, member.getGm_name());
			pstmt.setInt(3, member.getMem_no());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}

	public Member findId(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member findUser = null;
		
		String query = prop.getProperty("findId");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMem_name());
			pstmt.setString(2, member.getMem_email());
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				findUser = new Member(0, 
									  rset.getString("mem_id"), 
									  rset.getString("mem_name"), 
									  rset.getString("mem_email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return findUser;
	}

	public Member findPwd(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member findUser = null;
		
		String query = prop.getProperty("findPwd");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMem_id());
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				findUser = new Member(0, 
									  rset.getString("mem_id"), 
									  rset.getString("mem_email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return findUser;
	}

	public int changePwd(Connection conn, String id, String temporaryPwd) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("changPwd");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, temporaryPwd);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int confirmPw(Connection conn, Member member) {
		// 오버로딩 ==> 회원가입할 때 사용
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("leaveUser");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMem_pw());
			pstmt.setString(2, member.getMem_id());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public ArrayList<Member> selectNotOkGroupMembers(Connection conn, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset= null;
		ArrayList<Member> memberList = new ArrayList<Member>();
		
		int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() +1;
		int endRow = startRow + pi.getBoardLimit() -1;
		
		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectNotOkGroupMembers"));
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				 Member member = new Member(
						 rset.getInt("MEM_NO"), 
						 null,
						 rset.getString("MEM_ID"), 
						 null, 
						 rset.getString("MEM_NAME"),
						 rset.getString("MEM_PHONE"), 
						 rset.getString("MEM_ADDR"), 
						 rset.getString("MEM_EMAIL"), 
						 rset.getString("GM_REGNO"), 
						 rset.getString("GM_NAME"));
				 
				 memberList.add(member);
				 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return memberList;
	}

	public int approveMember(Connection conn, int memNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt= conn.prepareStatement(prop.getProperty("approveMember"));
			pstmt.setInt(1, memNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int selectNotOkGroupMembersCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		int count = 0;
		
		try {
			stmt= conn.createStatement();
			rset= stmt.executeQuery(prop.getProperty("selectNotOkGroupMembersCount"));
			
			if(rset.next()) {
				count= rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		return count;
	}
}