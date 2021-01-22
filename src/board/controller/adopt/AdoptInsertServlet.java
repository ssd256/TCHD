package board.controller.adopt;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import board.model.service.BoardService;
import board.model.vo.Adopt;
import board.model.vo.Board;
import board.model.vo.Files;
import common.MyFileRenamePolicy;
import member.model.vo.Member;

/**
 * Servlet implementation class AdoptInsertServlet
 */
@WebServlet(urlPatterns="/adoptInsert.bo", name="AdoptInsertServlet")
public class AdoptInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdoptInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginUser");
	
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024 * 1024* 10;
			String root = request.getSession().getServletContext().getRealPath("/"); // C:\5_JSPServlet_workspace\TCHD\WebContent\
			String savePath = root + "upload_imageFiles/";	// 파일 저장경로
			
			File f = new File(savePath);
			if(!f.exists()) {		// 폴더 없으면 만들고 시작
				f.mkdirs();
			}
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			ArrayList<String> saveFiles = new ArrayList<String>();	 // 바뀐 파일 이름 저장
			ArrayList<String> originFiles = new ArrayList<String>(); // 원본파일 이름 저장
			
			Enumeration<String> files = multiRequest.getFileNames();	// form에서 전송되는 파일이름
			while(files.hasMoreElements()) {
				String name = files.nextElement();
				
				if(multiRequest.getFilesystemName(name) != null) {
					saveFiles.add(multiRequest.getFilesystemName(name));	 // 바뀐 파일명
					originFiles.add(multiRequest.getOriginalFileName(name)); // 실제 업로드했던 파일명
				}
			}
			
			
			String kirr[] = multiRequest.getParameterValues("petKind");		// 동물 구분 체크박스
			String petKind = "";
			if(kirr != null) {
				for(int i = 0; i < kirr.length; i++) {
					if(i == kirr.length - 1) {
						petKind = kirr[i];
					} else {
						petKind = kirr[i];
					}
				}
			}
			
			
			String girr[] = multiRequest.getParameterValues("petGender");	// 동물성별 체크박스
			String petGender = "";
			if(girr != null) {
				for(int i = 0; i < girr.length; i++) {
					if(i == girr.length - 1) {
						petGender = girr[i];
					} else {
						petGender = girr[i];
					}
				}
			}
			String uirr[] = multiRequest.getParameterValues("unigender");	// 중성화 여부
			String unigender = "";
			if(uirr != null) {
				for(int i = 0; i < uirr.length; i++) {
					unigender = "O";
				}
			} else {
				unigender ="X";
			}
			
			String petSize = multiRequest.getParameter("petSize");			// 크기
			String petAge = multiRequest.getParameter("petAge") + "/" 		// 나이
						+ multiRequest.getParameter("petAgeDetail")
					    + multiRequest.getParameter("detailAge");
			
			
			String petName = multiRequest.getParameter("petName");			// 이름
			String petCategory = multiRequest.getParameter("petCategory");	// 품종
			float petWeight = Float.valueOf(multiRequest.getParameter("petWeight"));	// 무게
			String petColor = multiRequest.getParameter("petColor");		// 색깔
			String rescue = multiRequest.getParameter("rescue");			// 구조일자
			
			String lastMent = "";
			if(multiRequest.getParameter("lastMent") != null) {
				lastMent = multiRequest.getParameter("lastMent");		// 하고 싶은 말
			} else {
				lastMent = "";
			}
			
			Date rescueDate = null;
			if(rescue != "") {
				String[] dateArr = rescue.split("-");
				int year = Integer.parseInt(dateArr[0]);
				int month = Integer.parseInt(dateArr[1]) - 1;
				int day = Integer.parseInt(dateArr[2]);
				
				rescueDate = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
			} else {
				rescueDate = new Date(new GregorianCalendar().getTimeInMillis());
			}
			
			// DB에 저장할 객체 - Board 테이블
			Board b = new Board();
			
			b.setBoTitle(petName);
			b.setBoContent(petKind + ", " + petGender + ", " + unigender + ", " + petSize + ", " + petAge + ", " + 
						petName + ", " + petCategory + ", " + petWeight + ", " + petColor + ", " + 
						rescueDate + ", " + lastMent);
			b.setMemNo(member.getMem_no());		
			b.setCateNo(1);
			
		
			// DB에 저장할 객체 - Adopt 테이블
			Adopt a = new Adopt();
			
			a.setPetKinds(petKind);
			a.setPetGender(petGender);
			a.setPetUnigender(unigender);
			a.setPetSize(petSize);
			a.setPetAge(petAge);
			a.setPetName(petName);
			a.setPetCategory(petCategory);
			a.setPetWeight(petWeight);
			a.setPetColor(petColor);
			a.setPetRescueDate(rescueDate);
			a.setPetComment(lastMent);
			
			ArrayList<Files> fileList = new ArrayList<Files>();
			for(int i = originFiles.size() - 1; i >= 0; i--) {	// originFiles.size() : 원본파일 개수
				Files ft = new Files();
				ft.setFilePath(savePath);				// 사용자 정의 파일경로
				ft.setOrignName(originFiles.get(i));	// 원본파일 이름
				ft.setChangeName(saveFiles.get(i));		// 사용자 정의파일 이름
				
				if(i == originFiles.size() -1) {	
					ft.setFileLevel(0);			// 썸네일
				} else {
					ft.setFileLevel(1);			// 내용사진
				}
				
				fileList.add(ft);		// 파일 저장순서 순차적으로 적용
			}
			
			int reslut = new BoardService().insertBoard(b, a, fileList);
			if(reslut > 0) {
				response.sendRedirect("adopt.bo");
			} else {
				for(int i = 0; i < saveFiles.size(); i++) {
					File failedFile = new File(savePath + saveFiles.get(i));
					failedFile.delete();
				}
				request.setAttribute("msg", "사진 게시판 등록에 실패하였습니다.");
				request.setAttribute("section", "WEB-INF/views/common/errorPage.jsp");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
