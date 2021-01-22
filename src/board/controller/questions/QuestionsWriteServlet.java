package board.controller.questions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import board.model.service.BoardService;
import board.model.vo.Board;
import board.model.vo.Files;
import board.model.vo.Notice;
import board.model.vo.Questions;
import common.MyFileRenamePolicy;
import member.model.vo.Member;

/**
 * Servlet implementation class QuestionsWriteServlet
 */
@WebServlet("/write.qu")
public class QuestionsWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionsWriteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024*1024*10; // 최대용량 10MB
			String root = request.getSession().getServletContext().getRealPath("/"); //최상위 경로(WebContent)로 보낼때
			String savePath = root + "upload_imageFiles/";
	
			File f= new File(savePath);
			if(!f.exists()) {
				f.mkdirs();// 폴더 없으면 만들고 시작? 근데 왜 만들어지지
			}
			System.out.println(savePath); //콘솔엔 폴더주소 잘 찍힘..
		
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			//파일이름이 중복일 때 
			
			
		//파일 하나만 올릴수있으므로 arrayList 사용 x
		String saveFile = multiRequest.getFilesystemName("file");	// 바뀐 파일명
		String originFile = multiRequest.getOriginalFileName("file");	// 실제 업로드했던 파일명
	
		String selectBoard = multiRequest.getParameter("input_subject");
		String content = multiRequest.getParameter("input_content");
		String title= multiRequest.getParameter("input_title");
		int userNo = ((Member)session.getAttribute("loginUser")).getMem_no();
		String pass = multiRequest.getParameter("q_password"); 
	

		Questions q = new Questions(2, title,  content, pass, selectBoard, userNo);
		
		
		Files uploadFile =  new Files();
		uploadFile.setFilePath(savePath);
		uploadFile.setOrignName(originFile);
		uploadFile.setChangeName(saveFile);
		
		
		
		int result = new BoardService().insertQuestions(q, uploadFile);
		
		if(result>0) {
			response.sendRedirect("list.qu");
		}else {
			
			File failedFil = new File(savePath+saveFile);
			failedFil.delete();
			
			request.setAttribute("errorMsg", "문의사항 등록에 실패하였습니다.");
			request.setAttribute("section","WEB-INF/views/common/errorPage.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
