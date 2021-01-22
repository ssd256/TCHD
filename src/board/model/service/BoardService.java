package board.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import board.model.dao.BoardDAO;
import board.model.vo.Adopt;
import board.model.vo.AdoptApply;
import board.model.vo.Board;
import board.model.vo.Comments;
import board.model.vo.Files;
import board.model.vo.Notice;
import board.model.vo.PageInfo;
import board.model.vo.Questions;
import board.model.vo.Volunteer;
import support.model.vo.Support;

public class BoardService {

	public ArrayList selectTList(int i, PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList list = null;
		BoardDAO dao = new BoardDAO();
		
		if(i == 1) {
			list = dao.selectAList(conn, pi);
		} else { 
			list = dao.selectFList(conn, pi);
		}
		
		close(conn);
		
		return list;
	}
	
	

	public int getListCount(int boType) {
		Connection conn = getConnection();
		
		int result = new BoardDAO().getListCount(conn,boType);
		
		close(conn);
		
		return result;
	}



	public ArrayList<Questions> selectQList(PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<Questions> Qlist = new BoardDAO().selectQList(conn, pi);
		
		close(conn);
		return Qlist;
	}
	



	public ArrayList<Board> selectMyBoard(int mem_no, PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<Board> boardList = new BoardDAO().selectMyBoard(conn,mem_no,pi);
		
		close(conn);
		
		return boardList;
	}



	public ArrayList<Volunteer> selectVList(PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<Volunteer> volunteerList = new BoardDAO().selectVList(conn, pi);
		
		close(conn);
		
		return volunteerList;
	}
	
	public int insertBoard(Board b, Adopt a, ArrayList<Files> fileList) {
		Connection conn = getConnection();
		
		int result = new BoardDAO().insertBoard(conn, b);
		int result1 = 0;
		int result2 = 0;
		int finalResult = 0;
		
		if(result > 0) {
			result1 = new BoardDAO().insertAdoptBoard(conn, a);	// 내용 저장 객체
			finalResult = result1;
			if(result1 > 0) {
				result2 = new BoardDAO().insertAdoptFiles(conn, fileList);	// 파일 객체
				finalResult = result2;
			}
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return finalResult;
	}

	public ArrayList<Questions> selectAnswerQuestions(PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<Questions> questionsList = new BoardDAO().selectAnswerQuestions(conn,pi);
		
		close(conn);
		
		return questionsList;
	}

	public int answerQuestion(int qNo, String answer) {
		Connection conn = getConnection();
		
		int result = new BoardDAO().answerQuestion(conn,qNo,answer);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int selectMyBoardCount(int mem_no) {
		Connection conn = getConnection();
		
		int count = new BoardDAO().selectMyBoardCount(conn,mem_no);
		
		close(conn);
		
		return count;
		
	}

	public int selectAnswerQuestionsCount() {
		Connection conn = getConnection();
		
		int count = new BoardDAO().selectAnswerQuestionsCount(conn);
		
		close(conn);
		
		return count;
	}

	public Questions selectBoard(int bNo) {
		Connection conn = getConnection();
		BoardDAO dao = new BoardDAO();
		int result = dao.updateCount(conn, bNo);
		Questions qBoard = null;
		
		if(result > 0) {
			qBoard = dao.selectBoard(conn, bNo);
			
		}
		close(conn);
		
		
		return qBoard;
	}



	public int insertNotice(Notice notice, Files uploadFile) {
		Connection conn = getConnection();
		BoardDAO bDAO = new BoardDAO();
		int finalResult = 0;
		int result1 = bDAO.insertNoticeBoard1(conn, notice);

		if(result1>0) {
			int result2 = bDAO.insertNoticeBoard2(conn, notice.getNoticeSubject());
			finalResult = result2;
			if(result2>0 && uploadFile.getOrignName()!=null) {

				int result3 = bDAO.insertNoticeFile(conn,uploadFile);		

				finalResult = result3;

				commit(conn);
			}
		}else {
			rollback(conn);
		}


		close(conn);

		return finalResult;

	}



	public Notice selectNotice(int bNo) {
		Connection conn = getConnection();
		BoardDAO bDAO = new BoardDAO();
		int result=bDAO.updateCount(conn, bNo);
		Notice notice =null;
		
		if(result>0) {

			notice = bDAO.selectNotice(conn,bNo);
		}

		close(conn);

		return notice;
	}



	public ArrayList<Files> selectNoticeFile(int bNo) {
		Connection conn = getConnection();
		
	
	
		ArrayList<Files> fileList = null;
		
		fileList = new BoardDAO().selectFile(conn, bNo);

		close(conn);

		return fileList;
	}

	public int updateNotice(Notice notice, Files file) {
		Connection conn = getConnection();
		
		BoardDAO bDAO = new BoardDAO();
		
		int result1 = bDAO.updateNotice1(conn,notice);
		int finalResult=result1;
		if(result1>0) {  // BOARD테이블 UPDATE성공
			int result2 = bDAO.updateNotice2(conn, notice);
			finalResult=result2;
			if(result2>0) {  // NOTICE테이블 UPDATE성공
				if(file.getFileNo()!=0) { //원본이 사진이 있을때
					if(file.getOrignName()!=null) { // 수정페이지에서 사진을 추가할경우 = 사진을 변경할 경우
						int result3 = bDAO.updateNoticeFile1(conn,file);						
						finalResult=result3;													
					}else { // 사진을 뺄 경우
						int result3 = bDAO.updateNoticeFile3(conn,file);						
						finalResult=result3;	
					}
					
				}else {  //원본이 사진이 없을 때 
					if(file.getOrignName()!=null) { // 수정페이지에서 사진을 추가할경우 = 사진을 변경할 경우
						int result3= bDAO.updateNoticeFile2(conn, file);						
						finalResult=result3;												
					}
				
				}
			}
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return finalResult;
	}

	public Adopt selectedAdopt(int bNo) {
		Connection conn = getConnection();
		BoardDAO dao = new BoardDAO();
		
		int result = dao.updateCount(conn, bNo);
		Adopt adopt = null;
		
		if(result > 0) {
			adopt = dao.selectAdopt(conn, bNo);
		}
		
		close(conn);
		
		return adopt;
	}



	public int insertQuestions(Questions q, Files uploadFile) {
		Connection conn = getConnection();
		BoardDAO DAO = new BoardDAO();
		int finalResult = 0;
		int result1 = DAO.insertQuestions1(conn, q);

		if(result1>0) {
			int result2 = DAO.insertQuestions2(conn, q.getBoPwd(), q.getQuSub());
			finalResult = result2;
			if(result2>0 && uploadFile.getOrignName()!=null) {

				int result3 = DAO.insertQuestionsFile(conn,uploadFile);		

				finalResult = result3;

				commit(conn);
			}
		}else {
			rollback(conn);
		}


		close(conn);

		return finalResult;
		
	}


	
	public int insertApply(Board b, Adopt a, Files f , AdoptApply apply) {
		Connection conn = getConnection();
		BoardDAO dao = new BoardDAO();
		
		int result1 = dao.insertBoard(conn, b);
		int result2 = dao.insertApply(conn, apply);
		int result3 = 0;
		int result4 = 0;
		int finalResult = result2;
		
		if(result1 > 0 && result2 > 0) {
			result3 = dao.updateAdoptYn(conn, a);
			if(result3 > 0) {
				result4 = dao.updateFilesYn(conn, f);
				finalResult = result4;
			}
			finalResult = result3;
			commit(conn);
		} else { 
			rollback(conn);
		}
		
		close(conn);
		return finalResult;
	}
	
	
	
	public Volunteer selectVolunteer(int bNo) {
		Connection conn = getConnection();
		
		BoardDAO dao = new BoardDAO();
		
		int result = dao.updateCount(conn, bNo);
		
		Volunteer volunteer = null;
		if(result > 0) {
			volunteer = dao.selectVolunteer(conn, bNo);
			
			if(volunteer != null) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return volunteer;
	}

	public ArrayList<Comments> selectCommentsList(int bNo) {
		Connection conn = getConnection();
		
		ArrayList<Comments> commentsList = new BoardDAO().selectCommentsList(conn, bNo);
		close(conn);
		return commentsList;
	}
	


	public ArrayList<Notice> searchNotice(String search, PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<Notice> noticeList = new BoardDAO().searchNotice(conn,search,pi);
		
		close(conn);
		
		return noticeList;
	}



	public int searchNoticeCount(String search) {
		Connection conn = getConnection();
		
		int count = new BoardDAO().searchNoticeCount(conn,search);
		
		close(conn);
		
		return count;
		
		
	}



	public ArrayList<Notice> selectNoticeMainPage() {
		Connection conn = getConnection();
		
		ArrayList<Notice> noticeList = new BoardDAO().selectNoticeMainPage(conn);
		
		close(conn);
		
		return noticeList;
	}

	public ArrayList<Notice> selectNoticeList(PageInfo pi) {
		Connection conn = getConnection();

		ArrayList<Notice> noticeList = new BoardDAO().selectNoticeList(conn,pi);

		close(conn);

		return noticeList;
	}



	public int getMyVolunteerCount(int mem_no) {
		Connection conn= getConnection();
		
		int count = new BoardDAO().getMyVolunteerCount(conn,mem_no);
		
		close(conn);
		
		return count;
	}

	public ArrayList<Volunteer> selectMyVolunteerList(int mem_no,PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<Volunteer> volunteerList = new BoardDAO().selectMyVolunteer(conn,mem_no,pi);
		
		close(conn);
		
		return volunteerList;
		
	}



	public ArrayList<Adopt> selectAdoptMainPage() {
		Connection conn = getConnection();
		
		ArrayList<Adopt> adoptList = new BoardDAO().selectAdoptMainPage(conn);
		
		close(conn);
		
		return adoptList;
	}



	public ArrayList<Files> selectAdoptImageMainPage() {
		Connection conn = getConnection();
		
		ArrayList<Files> fileList = new BoardDAO().selectAdoptImageMainPage(conn);
		
		close(conn);
		
		return fileList;
	}

	public ArrayList<Comments> insertComments(Comments comments) {
		Connection conn = getConnection();
		
		BoardDAO dao = new BoardDAO();
		
		int result = dao.insertComments(conn, comments);
		
		ArrayList<Comments> commentsList = null;
		if(result > 0) {
			commit(conn);
			commentsList = dao.selectCommentsList(conn, comments.getBoNo());
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return commentsList;
	}



	public int updateAdopt(Board b, Adopt a) {
		Connection conn = getConnection();
		
		BoardDAO dao = new BoardDAO();
		
		int result = dao.updateBoard(conn, b);
		int result1 = 0;

		
		if(result > 0) {
			result1 = dao.updateAdopt(conn, a);

			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);

		return result1;
	}
	

	public HashMap<String, Integer> getEachBoardCount(int mem_no) {
		Connection conn= getConnection();
		
		HashMap<String, Integer> eachBoardCount = new BoardDAO().getEachBoardCount(conn,mem_no);
		
		close(conn);
		
		return eachBoardCount;
	}

	public ArrayList<Volunteer> selectMyVolunteerListGm(int mem_no,PageInfo pi) {
		Connection conn = getConnection();
		ArrayList<Volunteer> volunteerList = new BoardDAO().selectMyVolunteerGm(conn, mem_no,pi);
		close(conn);
		return volunteerList;
		
	}


	public int getMyVolunteerCountGm(int mem_no) {
		Connection conn = getConnection();
		int count = new BoardDAO().getMyVolunteerCountGm(conn, mem_no);
		close(conn);
		return count;
	}

	
public int insertVolunteer(Volunteer v, Files uploadFile) {
		
		Connection conn = getConnection();
		
		BoardDAO dao = new BoardDAO();
		
		int finalResult = 0;
		int result1 = dao.insertVolunteer1(conn, v);

		if(result1>0) {
			int result2 = dao.insertVolunteer2(conn, v, v.getVoMaxmember(), v.getVoApplymember(), v.getVoDeadline(), v.getVoDate(), v.getVoArea(), v.getVoPlace(), v.getVoComment());
			finalResult = result2;
			if(result2>0 && uploadFile.getOrignName()!=null) {

				int result3 = dao.insertVolunteerFile(conn,uploadFile);		

				finalResult = result3;

				commit(conn);
			}
			
		} else {
			
			rollback(conn);
		}

		close(conn);

		return finalResult;
	}

	public int updateVolunteer(Volunteer volunteer, Files file) {
		Connection conn = getConnection();
		
		BoardDAO dao = new BoardDAO();
		
		int result1 = dao.updateVolunteer1(conn,volunteer);
		int finalResult=result1;
		if(result1>0) {
			int result2 = dao.updateVolunteer2(conn, volunteer);
			finalResult=result2;
			if(result2>0) {
				if(file.getFileNo()!=0) {
					if(file.getOrignName()!=null) { 
						int result3 = dao.updateVolunteerFile1(conn,file);						
						finalResult=result3;													
					}else {
						int result3 = dao.updateVolunteerFile3(conn,file);						
						finalResult=result3;	
					}
					
				}else {
					if(file.getOrignName()!=null) {
						int result3= dao.updateVolunteerFile2(conn, file);						
						finalResult=result3;												
					}
				
				}
			}
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return finalResult;
	}
	
	public int applyVolunteer(int volBNo,int memNo) {
		Connection conn = getConnection();
		BoardDAO dao = new BoardDAO();
		int result = dao.applyVolunteer(conn, volBNo); // VOLUNTEER테이블의 APPLYMEMBER를 + 1
		int result2 = 0;
		int result3 = 0;
		int result4 = 0;
		int finalResult=0;
		if(result>0) { // Volunteer테이블에서 applyMember를 +1 
			result2= dao.applyJoinVolunteer(conn,volBNo,memNo); // JOIN_VOLUNTEER테이블에 INSERT
			if(result2>0) { 
				result3 = dao.checkVolunteer(conn,volBNo); // VOLUNTEER테이블에서 MAXMEMBER컬럼의 값과 APLLYMEMBER컬럼의 값이 일치한지 비교
				if(result3>0) { 
					result4 = dao.deadVolunteer(conn,volBNo); //VOLUNTEER테이블의 DEADLINE_YN컬럼을 Y로
					if(result4>0) {
						finalResult=result4;
						commit(conn);
					}else {
						rollback(conn);
					}
				}else {
					finalResult=1;
					commit(conn);
				}
			}else {
				rollback(conn);
			}
			
		}else {
			rollback(conn);
		}
		close(conn);
		return finalResult;
	}
	
	public int deleteVolunteer(int bNo) {
		Connection conn = getConnection();
		BoardDAO dao = new BoardDAO();
		int result = dao.deleteBoard(conn, bNo);
		int result1 = 0;
		int result2 = 0;
		if(result > 0) {
			result1 = dao.deleteVolunteer(conn, bNo);
			result2 = dao.deleteFiles(conn, bNo);
			
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result1;
	}


	public ArrayList<Support> selectManageSupport(Date searchDate,PageInfo pi) {
		Connection conn =getConnection();
		ArrayList<Support> supportList = new BoardDAO().selectManageSupport(conn,searchDate,pi);
		close(conn);
		return supportList;
		
	}



	public int[] getManageSupportCount(Date searchDate) {
		Connection conn= getConnection();
		int[] count = new BoardDAO().getManageSupportCount(conn,searchDate);
		close(conn);
		return count;
	}
	
	public int deleteAdopt(int bNo) {
		Connection conn = getConnection();
		BoardDAO dao = new BoardDAO();
		int result = dao.deleteBoard(conn, bNo);
		int result1 = 0;
		int result2 = 0;
		if(result > 0) {
			result1 = dao.deleteAdopt(conn, bNo);
			result2 = dao.deleteFiles(conn, bNo);
			
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result1;
	}



	public ArrayList searchMyBoard(int mem_no,String searchBoard,PageInfo pi) {
		Connection conn= getConnection();
		ArrayList resultList = new BoardDAO().searchMyBoard(conn,mem_no,searchBoard,pi);
		close(conn);
		return resultList;
	}



	public int getSearchMyBoardCount(int mem_no, String searchBoard) {
		Connection conn= getConnection();
		int count = new BoardDAO().getSearchMyBoardCount(conn,mem_no,searchBoard);
		close(conn);
		return count;
	}



	public ArrayList<Volunteer> searchVolunteer(String select, String cate,PageInfo pi) {
		Connection conn = getConnection();
		 ArrayList<Volunteer> volunteerList = new BoardDAO().searchVolunteer(conn,select,cate,pi);
		 close(conn);
		 return volunteerList;
	}



	public int getSearchVolunteerCount(String select, String cate) {
		Connection conn= getConnection();
		int count = new BoardDAO().getSearchVolunteerCount(conn,select,cate);
		close(conn);
		return count;
	}
	
	public int deleteQuestions(int bNo) {
		Connection conn = getConnection();
		BoardDAO dao = new BoardDAO();
		int result = dao.deleteBoard(conn, bNo);
		int result1 = 0;
		int result2 = 0;
		if(result > 0) {
			result1 = dao.deleteQuestions(conn, bNo);
			result2 = dao.deleteFiles(conn, bNo);
			
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result1;
		
	}

	
	public int updateQuestions(Questions qu, Files file) {
		Connection conn = getConnection();
		
		BoardDAO bDAO = new BoardDAO();
		
		int result1 = bDAO.updateQuestions1(conn,qu);
		int finalResult=result1;
		if(result1>0) {  // BOARD테이블 UPDATE성공
			int result2 = bDAO.updateQuestions2(conn, qu);
			finalResult=result2;
			if(result2>0) {  // questions테이블 UPDATE성공
				if(file.getFileNo()!=0) { //원본이 사진이 있을때
					if(file.getOrignName()!=null) { // 수정페이지에서 사진을 추가할경우 = 사진을 변경할 경우
						int result3 = bDAO.updateQFile1(conn,file);						
						finalResult=result3;													
					}else { // 사진을 뺄 경우
						int result3 = bDAO.updateQFile3(conn,file);						
						finalResult=result3;	
					}
					
				}else {  //원본이 사진이 없을 때 
					if(file.getOrignName()!=null) { // 수정페이지에서 사진을 추가할경우 = 사진을 변경할 경우
						int result3= bDAO.updateQFile2(conn, file);						
						finalResult=result3;												
					}
				
				}
			}
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return finalResult;
	}



	public ArrayList selectApplyMember(int bNo) {
		Connection conn = getConnection();
		
		ArrayList applyMemberList = new BoardDAO().selectApplyMember(conn,bNo);
		close(conn);
		return applyMemberList;
	}



	public int deleteNotice(int bNo) {
		Connection conn= getConnection();
		int result = new BoardDAO().deleteNotice(conn,bNo);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	
	
} // class end