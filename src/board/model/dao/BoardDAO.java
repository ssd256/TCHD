package board.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import board.model.vo.Adopt;
import board.model.vo.AdoptApply;
import board.model.vo.Board;
import board.model.vo.Files;
import board.model.vo.Notice;
import board.model.vo.PageInfo;
import board.model.vo.Questions;
import board.model.vo.Comments;
import board.model.vo.Volunteer;
import support.model.vo.Support;

public class BoardDAO {
	private Properties prop = new Properties();
	
	public BoardDAO() {
		String fileName = BoardDAO.class.getResource("/sql/board/board-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList selectAList(Connection conn, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Adopt> list = null;
		
		String query = prop.getProperty("selectAList");
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Adopt>();
			while(rset.next()) {
				list.add(new Adopt(rset.getInt("bo_no"),
						rset.getInt("bo_type"),
						rset.getString("cate_name"),
						rset.getString("mem_id"),
						rset.getString("pet_kinds"),
						rset.getString("pet_category"),
						rset.getString("pet_gender"),
						rset.getString("pet_unigender"),
						rset.getString("pet_name"),
						rset.getString("pet_age"),
						rset.getDate("pet_rescue_date"),
						rset.getFloat("pet_weight"),
						rset.getString("pet_color"),
						rset.getString("pet_size"),
						rset.getString("pet_comment"),
						rset.getString("adopt_yn")));
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList selectFList(Connection conn, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Files> list = null;
		
		String query = prop.getProperty("selectFList");
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setInt(3, 0);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Files>();
			while(rset.next()) {
				Files f = new Files(rset.getInt("file_no"),
									rset.getInt("bo_no"),
									rset.getInt("bo_type"),
									rset.getString("origin_name"),
									rset.getString("change_name"),
									rset.getString("file_path"),
									rset.getDate("upload_date"),
									rset.getInt("file_level"),
									rset.getString("status"));
				list.add(f);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int getListCount(Connection conn, int boType) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("getListCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boType);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}
	
	public ArrayList<Questions> selectQList(Connection conn, PageInfo pi) {	//목록
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Questions> Qlist = null;
		
		String query = prop.getProperty("selectQList");
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setInt(3, 2); //bo_type 2 문의게시판
			rset = pstmt.executeQuery();
			
			Qlist = new ArrayList<Questions>(); //db의 resultSet결과보고만들기
			while(rset.next()) {
				Questions q = new Questions(rset.getInt("bo_no"),
									rset.getInt("bo_type"),
									rset.getString("cate_name"),
									rset.getString("bo_title"),
									rset.getString("bo_content"),
									rset.getInt("bo_count"),
									rset.getDate("bo_date"),
									rset.getString("mem_id"),
									rset.getString("bo_delete_yn"),
									rset.getString("mem_leave"),
									rset.getString("qu_sub"),
									rset.getString("bo_pw"),
									rset.getString("com_content"));
				
				Qlist.add(q);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return Qlist;
	}
	
	
	public ArrayList<Board> selectMyBoard(Connection conn,int mem_no,PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> boardList = new ArrayList<Board>();
		
		int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() +1;
		int endRow = startRow + pi.getBoardLimit() -1;
		System.out.println(startRow +"/"+ endRow);
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectMyBoard"));
			pstmt.setInt(1, mem_no);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board board = new Board(
						rset.getInt("BO_NO"),
						rset.getString("CATE_NAME"),
						rset.getString("BO_TITLE"),
						rset.getDate("BO_DATE")
					);
				boardList.add(board);
			
		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return  boardList;
	}

	public ArrayList<Volunteer> selectVList(Connection conn, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Volunteer> volunteerList = null;
		
		String query = prop.getProperty("selectVList");
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);	// 첫번째 자리에 startRow
			pstmt.setInt(2, endRow);	// 두번째 자리에 endRow
			pstmt.setInt(3, 3);
			rset = pstmt.executeQuery();
			
			volunteerList = new ArrayList<Volunteer>();
			while(rset.next()) {
				Volunteer volunteer = new Volunteer(rset.getInt("bo_no"),
													rset.getInt("bo_type"),
													rset.getString("cate_name"),
													rset.getString("vo_area"),
													rset.getString("bo_title"),
													rset.getInt("mem_no"),
													rset.getString("mem_id"),
													rset.getDate("bo_date"),	
													rset.getInt("vo_maxmember"),
													rset.getInt("vo_applymember"),
													rset.getTimestamp("vo_date"),
													rset.getInt("bo_count"));
				volunteerList.add(volunteer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		// DB에 VLIST 뷰 짜고 다시 재도전 예정 ㅎ..
		
		return volunteerList;
	}

	public int insertBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, b.getMemNo());
			pstmt.setString(2, b.getBoTitle());
			pstmt.setString(3, b.getBoContent());
			pstmt.setInt(4, b.getCateNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			close(pstmt);
		}
		
		return result;
	}

	public int insertAdoptBoard(Connection conn, Adopt a) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertAdopt");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, a.getPetKinds());
			pstmt.setString(2, a.getPetCategory());
			pstmt.setString(3, a.getPetGender());
			pstmt.setString(4, a.getPetUnigender());
			pstmt.setString(5, a.getPetName());
			pstmt.setString(6, a.getPetAge());
			pstmt.setDate(7, a.getPetRescueDate());
			pstmt.setFloat(8, a.getPetWeight());
			pstmt.setString(9, a.getPetColor());
			pstmt.setString(10, a.getPetSize());
			pstmt.setString(11, a.getPetComment());
			
			result = pstmt.executeUpdate();
	} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			close(pstmt);
		}
		
		return result;
	}

	public int insertAdoptFiles(Connection conn, ArrayList<Files> fileList) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertFiles");
		
		try {
			for(int i = 0; i < fileList.size(); i++) {
				Files f = fileList.get(i);
				
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, f.getOrignName());
					pstmt.setString(2, f.getChangeName());
					pstmt.setString(3, f.getFilePath());
					pstmt.setInt(4, f.getFileLevel());
					
					result += pstmt.executeUpdate();	// 파일이 여러개일 수 있어서 +=
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Questions> selectAnswerQuestions(Connection conn,PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset =null;
		ArrayList<Questions> questionsList = new ArrayList<Questions>();
		
		int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() +1;
		int endRow = startRow + pi.getBoardLimit() -1;
		
		try {
			pstmt= conn.prepareStatement(prop.getProperty("selectAnswerQuestions"));
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Questions q = new Questions(rset.getInt("BO_NO"), 
						rset.getString("BO_TITLE"), 
						rset.getString("BO_CONTENT"), 
						rset.getString("MEM_ID"), 
						rset.getDate("BO_DATE"), 0, null, null); 
						questionsList.add(q);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		
		return questionsList;
	}

	public int answerQuestion(Connection conn, int qNo, String answer) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt =conn.prepareStatement(prop.getProperty("answerQuestion"));
			pstmt.setInt(1, qNo);
			pstmt.setString(2, answer);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
		
	}

	public int selectMyBoardCount(Connection conn, int mem_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int count =0;
		
		try {
			pstmt =conn.prepareStatement(prop.getProperty("getEachBoardCount"));
			pstmt.setInt(1, mem_no);
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				count+= rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return count;
	}

	public int selectAnswerQuestionsCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int count =0;
		
		try {
			pstmt =conn.prepareStatement(prop.getProperty("selectAnswerQuestionsCount"));
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				count= rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return count;
	}

	public int updateCount(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Questions selectBoard(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Questions qBoard = null;
		
		
		
		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectQuestions"));
			pstmt.setInt(1, bNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				qBoard  = new Questions(rset.getInt("bo_no"),
									rset.getInt("bo_type"),
									rset.getString("cate_name"),
									rset.getString("bo_title"),
									rset.getString("bo_content"),
									rset.getInt("bo_count"),
									rset.getDate("bo_date"),
									rset.getString("mem_id"),
									rset.getString("bo_delete_yn"),
									rset.getString("qu_sub"),
									rset.getString("bo_pw"),
									rset.getString("com_content"),
									rset.getString("com_date"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return qBoard;
	}
	

	public ArrayList<Notice> selectNoticeList(Connection conn,PageInfo pi) {
		PreparedStatement pstmt= null;
		ResultSet rset= null;
		ArrayList<Notice> noticeList = new ArrayList<Notice>();
		
		int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() +1;
		int endRow = startRow + pi.getBoardLimit() -1;
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectNoticeList"));
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Notice notice = new Notice(
						rset.getInt("BO_NO"),
						rset.getString("BO_TITLE"),
						rset.getString("BO_CONTENT"),
						rset.getDate("BO_DATE"),
						rset.getInt("BO_COUNT"),
						null,
						rset.getString("NOTICE_SUBJECT"),
						rset.getInt("RNUM"));
				noticeList.add(notice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return noticeList;
	}

	public int insertNoticeBoard1(Connection conn, Notice notice) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(prop.getProperty("insertNoticeBoard1"));
			pstmt.setString(1, notice.getBoTitle());
			pstmt.setString(2, notice.getBoContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int insertNoticeBoard2(Connection conn, String noticeSubject) {
		PreparedStatement pstmt = null;
		int result= 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("insertNoticeBoard2"));
			pstmt.setString(1, noticeSubject);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}

	public int insertNoticeFile(Connection conn, Files uploadFile) {
		PreparedStatement pstmt = null;
		int result=0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("insertNoticeFile"));
			pstmt.setString(1, uploadFile.getOrignName());
			pstmt.setString(2, uploadFile.getChangeName());
			pstmt.setString(3, uploadFile.getFilePath());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public Notice selectNotice(Connection conn, int bNo) {
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		Notice notice =null;

		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectNotice"));
			pstmt.setInt(1, bNo);
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				notice = new Notice(
						rset.getInt("BO_NO"),
						rset.getString("BO_TITLE"),
						rset.getString("BO_CONTENT"),
						rset.getDate("BO_DATE"),
						rset.getInt("BO_COUNT"),
						null,
						rset.getString("NOTICE_SUBJECT"),
						0
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}		
		return notice;
	}

	public ArrayList<Files> selectFile(Connection conn, int bNo) {
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		ArrayList<Files> fileList = null;
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectFiles"));
			pstmt.setInt(1, bNo);
			
			rset=pstmt.executeQuery();
			
			fileList = new ArrayList<Files>();
			while(rset.next()) {
				Files f = new Files();
				f.setFileNo(rset.getInt("file_no"));
				f.setOrignName(rset.getString("origin_name"));
				f.setChangeName(rset.getString("change_name"));
				f.setFilePath(rset.getString("file_path"));
				f.setUploadDate(rset.getDate("upload_date"));
				
				fileList.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}		
		
		return fileList;
	}

	public Adopt selectAdopt(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Adopt adopt = null;
		
		String query = prop.getProperty("selectAdopt");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				adopt = new Adopt(rset.getInt("bo_no"),
								rset.getInt("bo_type"),
								rset.getString("cate_name"),
								rset.getString("mem_id"),
								rset.getString("pet_kinds"),
								rset.getString("pet_category"),
								rset.getString("pet_gender"),
								rset.getString("pet_unigender"),
								rset.getString("pet_name"),
								rset.getString("pet_age"),
								rset.getDate("pet_rescue_date"),
								rset.getFloat("pet_weight"),
								rset.getString("pet_color"),
								rset.getString("pet_size"),
								rset.getString("pet_comment"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return adopt;
	}

	public int updateNotice1(Connection conn, Notice notice) {
		System.out.println("updateNotice1");
		PreparedStatement pstmt = null;
		int result=0;
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("updateNotice1"));
			pstmt.setString(1, notice.getBoTitle());
			pstmt.setString(2, notice.getBoContent());
			pstmt.setInt(3, notice.getBoNo());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateNotice2(Connection conn,  Notice notice) {
		System.out.println("updateNotice2");
		PreparedStatement pstmt=null;
		int result =0;
		try {
			pstmt =conn.prepareStatement(prop.getProperty("updateNotice2"));
			pstmt.setString(1, notice.getNoticeSubject());
			pstmt.setInt(2, notice.getBoNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateNoticeFile1(Connection conn, Files file) {
		System.out.println("updateNoticeFile1");
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(prop.getProperty("updateNoticeFile1"));
			pstmt.setString(1, file.getOrignName());
			pstmt.setString(2, file.getChangeName());
			pstmt.setInt(3, file.getFileNo());
			result= pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateNoticeFile2(Connection conn, Files file) {
		System.out.println("updateNoticeFile2");
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt= conn.prepareStatement(prop.getProperty("updateNoticeFile2"));
			pstmt.setInt(1, file.getBoNo());
			pstmt.setString(2,  file.getOrignName());
			pstmt.setString(3, file.getChangeName());
			pstmt.setString(4, file.getFilePath());
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateNoticeFile3(Connection conn, Files file) {
		System.out.println("updateNoticeFile3");
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt= conn.prepareStatement(prop.getProperty("updateNoticeFile3"));
			pstmt.setInt(1, file.getFileNo());
	
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}



	public int insertQuestions1(Connection conn, Questions q) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertQuestions1");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, q.getMemNo());
			pstmt.setString(2, q.getBoTitle());
			pstmt.setString(3, q.getBoContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
				e.printStackTrace();
			} finally { 
				close(pstmt);
			}
			
			return result;
		}

	
	public int insertQuestions2(Connection conn, String boPwd, String selectBoard) {
		PreparedStatement pstmt = null;
		int result= 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("insertQuestions2"));
			pstmt.setString(1, boPwd);
			pstmt.setString(2, selectBoard);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	
	public int insertApply(Connection conn, AdoptApply apply) {
		PreparedStatement pstmt = null; 
		int result = 0;
		
		String query = prop.getProperty("insertApply");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, apply.getMemNo());
			pstmt.setString(2, apply.getApplyContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int updateAdoptYn(Connection conn, Adopt a) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateAdoptYn");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, a.getBoNo());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	public int updateFilesYn(Connection conn, Files f) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateFilesYn");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, f.getBoNo());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	public Volunteer selectVolunteer(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Volunteer volunteer = null;
		
		String query = prop.getProperty("selectVolunteer");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				volunteer = new Volunteer(rset.getInt("bo_no"),
											rset.getInt("bo_type"),
											rset.getString("cate_name"),
											rset.getString("vo_area"),
											rset.getString("bo_title"),
											rset.getInt("mem_no"),
											rset.getString("mem_id"),
											rset.getDate("bo_date"),	
											rset.getInt("vo_maxmember"),
											rset.getInt("vo_applymember"),
											rset.getTimestamp("vo_date"),
											rset.getInt("bo_count"),
											rset.getString("vo_comment"),
											rset.getString("vo_place"),
											rset.getString("vo_deadline_yn"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return volunteer;
	}

	public ArrayList<Comments> selectCommentsList(Connection conn, int bNo) {
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		ArrayList<Comments> commentsList = null;
		
		String query = prop.getProperty("selectCommentsList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			
			rset = pstmt.executeQuery();
			commentsList = new ArrayList<Comments>();
			while(rset.next()) {
				commentsList.add(new Comments(rset.getInt("com_no"),
											  rset.getString("com_content"),
											  rset.getInt("bo_no"),
											  rset.getString("mem_id"),
											  rset.getDate("com_date"), 
											  rset.getString("com_delete_yn")));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return commentsList;
	}

	public ArrayList<Notice> searchNotice(Connection conn, String search, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Notice> noticeList = new ArrayList<Notice>();
		
		int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() +1;
		int endRow = startRow + pi.getBoardLimit() -1;
	  
		try {
			pstmt= conn.prepareStatement(prop.getProperty("searchNotice"));
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setString(3, search);
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				Notice notice = new Notice(
						rset.getInt("BO_NO"), 
						rset.getString("BO_TITLE"), 
						rset.getString("BO_CONTENT"), 
						rset.getDate("BO_DATE"), 
						rset.getInt("BO_COUNT"),
						null, 
						rset.getString("NOTICE_SUBJECT"), 
						rset.getInt("RNUM")
						);
				noticeList.add(notice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return noticeList;
	
	}

	public int searchNoticeCount(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int count = 0;
		try {
			pstmt= conn.prepareStatement(prop.getProperty("searchNoticeCount"));
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return count;
	}

	public ArrayList<Notice> selectNoticeMainPage(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset= null;
		ArrayList<Notice> noticeList = new ArrayList<Notice>();
		
		try {
			pstmt= conn.prepareStatement(prop.getProperty("selectNoticeMainPage"));
			pstmt.setString(1, "공지사항");
			rset= pstmt.executeQuery();
			int start=0;
			while(rset.next()) {
				Notice notice= new Notice(rset.getInt("BO_NO"), 
						rset.getString("BO_TITLE"), 
						null, 
						rset.getDate("BO_DATE"), 
						0,
						null, 
						rset.getString("NOTICE_SUBJECT"), 
						0);
				
				noticeList.add(notice);
				if(rset.getString("NOTICE_SUBJECT").equals("공지사항")) {
					start++;
				}
				if(start>=6) {break;}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			close(pstmt);
			close(rset);
		}
		
		return noticeList;
	}
	
	
	public int insertQuestionsFile(Connection conn, Files uploadFile) {
		PreparedStatement pstmt = null;
		int result=0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("insertQuestionsFile"));
			pstmt.setString(1, uploadFile.getOrignName());
			pstmt.setString(2, uploadFile.getChangeName());
			pstmt.setString(3, uploadFile.getFilePath());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	


	public ArrayList<Volunteer> selectMyVolunteer(Connection conn, int mem_no,PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Volunteer> voList = new ArrayList<Volunteer>();
		
		int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() +1;
		int endRow = startRow + pi.getBoardLimit() -1;
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectMyVolunteer"));
			pstmt.setInt(1, mem_no);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				Volunteer volunteer = new Volunteer(rset.getInt("BO_NO"), 0, 
						null, rset.getString("BO_TITLE"), 0, 
						null, 0, null, null,
						0, 0, null, 
						rset.getTimestamp("VO_DATE"), null, rset.getString("VO_PLACE"), null);
				
				voList.add(volunteer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			close(pstmt);
			close(rset);
		}
		
		return voList;
	}

	public int getMyVolunteerCount(Connection conn, int mem_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result=0;
		
		try {
			pstmt = conn.prepareStatement(prop.getProperty("getMyVolunteerCount"));
			pstmt.setInt(1, mem_no);
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			close(pstmt);
			close(rset);
		}
		
		return result;
	}

	public ArrayList<Adopt> selectAdoptMainPage(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Adopt> adoptList = new ArrayList<Adopt>();
		
		try {
			stmt =conn.createStatement();
			rset=stmt.executeQuery(prop.getProperty("selectAdoptMainPage"));
			
			while(rset.next()) {
				Adopt adopt= new Adopt(rset.getInt("BO_NO"), 0, null, null, 
						rset.getString("PET_KINDS"), rset.getString("PET_CATEGORY"), rset.getString("PET_GENDER"), 
						rset.getString("PET_UNIGENDER"), rset.getString("PET_NAME"), rset.getString("PET_AGE"),  null, rset.getFloat("PET_WEIGHT"), 
						rset.getString("PET_COLOR"), null, null);
				adoptList.add(adopt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rset);
		}
		
		return adoptList;
 	}

	public ArrayList<Files> selectAdoptImageMainPage(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Files> fileList = new ArrayList<Files>();
		
		try {
			stmt =conn.createStatement();
			rset=stmt.executeQuery(prop.getProperty("selectAdoptMainPage"));
			
			while(rset.next()) {
				Files file = new Files(rset.getInt("BO_NO"), rset.getString("CHANGE_NAME"));
				fileList.add(file);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return fileList;
	}

	public int insertComments(Connection conn, Comments comments) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertComments");
		
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, comments.getMemNo());
			pstmt.setInt(2, comments.getBoNo());
			pstmt.setString(3, comments.getComContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public HashMap<String, Integer> getEachBoardCount(Connection conn, int mem_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		HashMap<String, Integer> eachBoardCount = new HashMap<String, Integer>();
		
		try {
			pstmt = conn.prepareStatement(prop.getProperty("getEachBoardCount"));
			pstmt.setInt(1, mem_no);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				eachBoardCount.put(rset.getString("CATE_NAME"), rset.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return eachBoardCount;
	}

	public ArrayList<Volunteer> selectMyVolunteerGm(Connection conn, int mem_no, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Volunteer> volunteerList = new ArrayList<Volunteer>();
		
		int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() +1;
		int endRow = startRow + pi.getBoardLimit() -1;
		
		try {
			pstmt= conn.prepareStatement(prop.getProperty("selectMyVolunteerGm"));
			pstmt.setInt(1, mem_no);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Volunteer volunteer = new Volunteer(rset.getInt("BO_NO"), 0, 
						null, rset.getString("BO_TITLE"), 0, 
						null, 0, null, null,
						0, 0, null, 
						rset.getTimestamp("VO_DATE"), null, rset.getString("VO_PLACE"), null);
				volunteerList.add(volunteer);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return volunteerList;
	}

	public int getMyVolunteerCountGm(Connection conn, int mem_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int count = 0;
		
		try {
			pstmt = conn.prepareStatement(prop.getProperty("getMyVolunteerCountGm"));
			pstmt.setInt(1, mem_no);
			rset= pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt(1);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return count;
		
	}
	
	public int updateBoard(Connection conn, Board b) { // 수정내용 Board 테이블에 udpate
		PreparedStatement pstmt = null;
		int result = 0; 
		
		String query = prop.getProperty("updateBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getBoTitle());
			pstmt.setString(2, b.getBoContent());
			pstmt.setInt(3, b.getBoNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			close(pstmt);
		}
		
		return result;
	}

	public int updateAdopt(Connection conn, Adopt a) {	// 수정내용 Adopt 테이블에 update
		PreparedStatement pstmt = null;
		int result = 0; 
		
		String query = prop.getProperty("updateAdopt");
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, a.getPetKinds());
			pstmt.setString(2, a.getPetCategory());
			pstmt.setString(3, a.getPetGender());
			pstmt.setString(4, a.getPetUnigender());
			pstmt.setString(5, a.getPetName());
			pstmt.setString(6, a.getPetAge());
			pstmt.setString(7, a.getRescueDate());
			pstmt.setFloat(8, a.getPetWeight());
			pstmt.setString(9, a.getPetColor());
			pstmt.setString(10, a.getPetSize());
			pstmt.setString(11, a.getPetComment());
			pstmt.setInt(12, a.getBoNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateAdoptFile1(Connection conn, ArrayList<Files> fList) {	// 사진 추가 or 변경할 경우 Files 테이블에 update
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateAdoptFile1");
		try {
			for(int i = 0; i < fList.size(); i++) {
				Files f = fList.get(i);
				
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, f.getOrignName());
			pstmt.setString(2, f.getChangeName());
			pstmt.setString(3, f.getFilePath());
			pstmt.setInt(4, f.getBoNo());
			pstmt.setInt(5, f.getFileLevel());
			
			result += pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
	public int insertVolunteer1(Connection conn, Volunteer v) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(prop.getProperty("insertVolunteer1"));
			pstmt.setInt(1, v.getMemNo());
			pstmt.setString(2, v.getBoTitle());
			pstmt.setString(3, v.getVoComment());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int insertVolunteer2(Connection conn, Volunteer v, int voMaxmember, int voApplymember, String voDeadline, Timestamp voDate,
			String voArea, String voPlace, String voComment) {
		PreparedStatement pstmt = null;
		int result= 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("insertVolunteer2"));
			pstmt.setInt(1, v.getVoMaxmember());
			pstmt.setTimestamp(2, v.getVoDate());
			pstmt.setString(3, v.getVoArea());
			pstmt.setString(4, v.getVoPlace());
			pstmt.setString(5, v.getVoComment());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int insertVolunteerFile(Connection conn, Files uploadFile) {
		PreparedStatement pstmt = null;
		int result=0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("insertVolunteerFile"));
			pstmt.setString(1, uploadFile.getOrignName());
			pstmt.setString(2, uploadFile.getChangeName());
			pstmt.setString(3, uploadFile.getFilePath());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteVolunteer(Connection conn, int bNo) {
		PreparedStatement pstmt  =null;
		int result = 0;
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("deleteVolunteer"));
			pstmt.setInt(1, bNo);
			result= pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}
	
	public int updateVolunteer1(Connection conn, Volunteer volunteer) {
		System.out.println("updateVolunteer1");
		PreparedStatement pstmt = null;
		int result=0;
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("updateVolunteer1"));
			pstmt.setString(1, volunteer.getBoTitle());
			pstmt.setString(2, volunteer.getVoComment());
			pstmt.setInt(3, volunteer.getBoNo());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateVolunteer2(Connection conn, Volunteer volunteer) {
		System.out.println("updateVolunteer2");
		PreparedStatement pstmt=null;
		int result =0;
		System.out.println(volunteer);
		try {
			pstmt =conn.prepareStatement(prop.getProperty("updateVolunteer2"));
			pstmt.setInt(1, volunteer.getVoMaxmember());
			pstmt.setTimestamp(2, volunteer.getVoDate());
			pstmt.setString(3, volunteer.getVoArea());
			pstmt.setString(4, volunteer.getVoPlace());
			pstmt.setString(5, volunteer.getVoComment());
			pstmt.setInt(6, volunteer.getBoNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateVolunteerFile1(Connection conn, Files file) {
		System.out.println("updateVolunteerFile1");
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(prop.getProperty("updateVolunteerFile1"));
			pstmt.setString(1, file.getOrignName());
			pstmt.setString(2, file.getChangeName());
			pstmt.setInt(3, file.getFileNo());
			result= pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int updateVolunteerFile2(Connection conn, Files file) {
		System.out.println("updateVolunteerFile2");
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt= conn.prepareStatement(prop.getProperty("updateVolunteerFile2"));
			pstmt.setInt(1, file.getBoNo());
			pstmt.setString(2,  file.getOrignName());
			pstmt.setString(3, file.getChangeName());
			pstmt.setString(4, file.getFilePath());
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateVolunteerFile3(Connection conn, Files file) {
		System.out.println("updateVolunteerFile3");
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt= conn.prepareStatement(prop.getProperty("updateVolunteerFile3"));
			pstmt.setInt(1, file.getFileNo());
	
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
		
	public int applyVolunteer(Connection conn, int volBNo) {
		PreparedStatement pstmt  =null;
		int result = 0;
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("applyVolunteer"));
			pstmt.setInt(1, volBNo);
			result= pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Support> selectManageSupport(Connection conn,Date searchDate, PageInfo pi ) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Support> supportList = new ArrayList<Support>();
		
		int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() +1;
		int endRow = startRow + pi.getBoardLimit() -1;
		System.out.println(startRow+"/"+endRow+"/"+searchDate+"/"+pi);
		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectManageSupport"));
			pstmt.setDate(1, searchDate);
			pstmt.setDate(2, searchDate);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Support support = new  Support(
						rset.getInt("MEM_NO"), 
						rset.getString("MEM_ID"), 
						rset.getInt("SUP_NO"), 						
						rset.getInt("SUP_PRICE"),
						rset.getDate("SUP_DATE"), 
						rset.getString("MEM_NAME"),
						rset.getString("MEM_TYPE"));
				supportList.add(support);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return supportList;
	}

	public int[] getManageSupportCount(Connection conn, Date searchDate) {
		PreparedStatement pstmt= null;
		ResultSet rset= null;
		int[] count = new int[2];
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("getManageSupportCount"));
			pstmt.setDate(1, searchDate);
			pstmt.setDate(2, searchDate);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count[0]=rset.getInt(1);
				count[1]=rset.getInt("TOTALPRICE");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return count;
	}
	
	public int deleteBoard(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteAdopt(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteAdopt");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteFiles(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteFiles");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList searchMyBoard(Connection conn, int mem_no, String searchBoard, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList resultList = new ArrayList();
		
		int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() +1;
		int endRow = startRow + pi.getBoardLimit() -1;
		System.out.println(startRow +"/"+ endRow+"/"+mem_no+"/"+searchBoard);
		try {
			pstmt= conn.prepareStatement(prop.getProperty("searchMyBoard"));
			pstmt.setInt(1, mem_no);
			pstmt.setString(2, searchBoard);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board board = new Board(
						rset.getInt("BO_NO"),
						rset.getString("CATE_NAME"),
						rset.getString("BO_TITLE"),
						rset.getDate("BO_DATE")
					);
				resultList.add(board);

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return resultList;
		
	}

	public int getSearchMyBoardCount(Connection conn, int mem_no, String searchBoard) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int count = 0;
		
		try {
			pstmt = conn.prepareStatement(prop.getProperty("getSearchMyBoardCount"));
			pstmt.setInt(1, mem_no);
			pstmt.setString(2, searchBoard);
			rset= pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return count;
	}

	public ArrayList<Volunteer> searchVolunteer(Connection conn, String select, String cate,PageInfo pi) {
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		ArrayList<Volunteer> volunteerList = new ArrayList<Volunteer>();
		if(cate.equals("boTitle")) {cate="BO_TITLE";}else {cate="VO_AREA";}
		String searchQuery ="SELECT * FROM (SELECT ROWNUM RNUM2, V.* FROM VLIST V WHERE "+cate+" LIKE '%"+select+"%') WHERE RNUM2 BETWEEN ? AND ?";
		int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() +1;
		int endRow = startRow + pi.getBoardLimit() -1;
		
		try {
			pstmt=conn.prepareStatement(searchQuery);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				Volunteer volunteer = new Volunteer(rset.getInt("bo_no"),
						rset.getInt("bo_type"),
						rset.getString("cate_name"),
						rset.getString("vo_area"),
						rset.getString("bo_title"),
						rset.getInt("mem_no"),
						rset.getString("mem_id"),
						rset.getDate("bo_date"),	
						rset.getInt("vo_maxmember"),
						rset.getInt("vo_applymember"),
						rset.getTimestamp("vo_date"),
						rset.getInt("bo_count"));
				volunteerList.add(volunteer);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return volunteerList;
		
	}

	public int getSearchVolunteerCount(Connection conn, String select, String cate) {
		Statement stmt = null;
		ResultSet rset = null;
		int count = 0;
		if(cate.equals("boTitle")) {cate="BO_TITLE";}else {cate="VO_AREA";}
		String query="SELECT COUNT(*) FROM VLIST WHERE "+cate+" LIKE '%"+select+"%'";
		
		try {
			stmt = conn.createStatement();
			rset= stmt.executeQuery(query);
			
			while(rset.next()) {
				count= rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(stmt);
		}
		
		return count;
	}
	
	public int deleteQuestions(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteQuestions");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}

	public int updateQuestions1(Connection conn, Questions qu) {
		System.out.println("updateQ1");
		PreparedStatement pstmt = null;
		int result=0;
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("updateQuestions1"));
			pstmt.setString(1, qu.getBoTitle());
			pstmt.setString(2, qu.getBoContent());
			pstmt.setInt(3, qu.getBoNo());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int updateQuestions2(Connection conn, Questions qu) {
		System.out.println("updateQ2");
		PreparedStatement pstmt=null;
		int result =0;
		try {
			pstmt =conn.prepareStatement(prop.getProperty("updateQuestions2"));
			pstmt.setString(1, qu.getBoPwd());
			pstmt.setString(2, qu.getQuSub());
			pstmt.setInt(3, qu.getBoNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int updateQFile1(Connection conn, Files file) {
		System.out.println("updateQFile1");
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(prop.getProperty("updateQFile1"));
			pstmt.setString(1, file.getOrignName());
			pstmt.setString(2, file.getChangeName());
			pstmt.setInt(3, file.getFileNo());
			result= pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int updateQFile2(Connection conn, Files file) {
		System.out.println("updateQFile2");
		PreparedStatement pstmt = null;
		int result = 0;
	
		try {
			pstmt= conn.prepareStatement(prop.getProperty("updateQFile2"));
			pstmt.setInt(1, file.getBoNo());
			pstmt.setString(2,  file.getOrignName());
			pstmt.setString(3, file.getChangeName());
			pstmt.setString(4, file.getFilePath());
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	public int updateQFile3(Connection conn, Files file) {
		System.out.println("updateQFile3");
		PreparedStatement pstmt = null;
		int result = 0;
	
		try {
			pstmt= conn.prepareStatement(prop.getProperty("updateQFile3"));
			pstmt.setInt(1, file.getFileNo());
	
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int applyJoinVolunteer(Connection conn, int volBNo, int memNo) {
		PreparedStatement pstmt = null;
		int result=0;
		
		try {
			pstmt= conn.prepareStatement(prop.getProperty("applyJoinVolunteer"));
			pstmt.setInt(1, volBNo);
			pstmt.setInt(2, memNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int checkVolunteer(Connection conn, int volBNo) {
		PreparedStatement pstmt = null;
		ResultSet rset= null;
		int result=0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("checkVolunteer"));
			pstmt.setInt(1, volBNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				if(rset.getInt("VO_MAXMEMBER")==rset.getInt("VO_APPLYMEMBER")) {
					result = 1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public int deadVolunteer(Connection conn, int volBNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt= conn.prepareStatement(prop.getProperty("deadVolunteer"));
			pstmt.setInt(1, volBNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}

	public ArrayList selectApplyMember(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList applyMemberList = new ArrayList();
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectApplyMember"));
			pstmt.setInt(1, bNo);
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				applyMemberList.add(rset.getInt("MEM_NO"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		
		return applyMemberList;
	}

	public int deleteNotice(Connection conn, int bNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("deleteBoard"));
			pstmt.setInt(1, bNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}


}