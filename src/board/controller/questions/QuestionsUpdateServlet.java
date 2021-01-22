package board.controller.questions;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import board.model.service.BoardService;
import board.model.vo.Files;
import board.model.vo.Notice;
import board.model.vo.Questions;
import common.MyFileRenamePolicy;

/**
 * Servlet implementation class QuestionsUpdateServlet
 */
@WebServlet("/update.qu")
public class QuestionsUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionsUpdateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024*1024*10;
			String root = request.getSession().getServletContext().getRealPath("/");
			String savePath = root + "upload_imageFiles/";
			
			File f= new File(savePath);
			if(!f.exists()) {
				f.mkdirs();
				
			}
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize,"UTF-8", new MyFileRenamePolicy());
			
			String saveFile = multiRequest.getFilesystemName("input_file");	// form에서 전송되는 파일이름
			String originFile = multiRequest.getOriginalFileName("input_file");	// form에서 전송되는 파일이름
			
			System.out.println(saveFile);
			System.out.println(originFile);
			
			
			int no = Integer.parseInt(multiRequest.getParameter("qNo"));
			String subject = multiRequest.getParameter("input_subject");
			String title= multiRequest.getParameter("input_title");
			String content = multiRequest.getParameter("input_content");
			String pass = multiRequest.getParameter("q_password");
			Questions qu = new Questions(no, title, content, null, null, 0, subject, pass);
									//게시글번호, 제목,  내용      ,작성자이름, 날짜 , 조회수, 게시판분류 , 비밀번호
			int fileNo =0;
	
			if(multiRequest.getParameter("qFileNo")!=null) {
				fileNo = Integer.parseInt(multiRequest.getParameter("qFileNo"));
		
			}
			Files file = new Files(fileNo, no, originFile, saveFile, savePath, null, 0, 0, null);
															
			int result = new BoardService().updateQuestions(qu, file);
			
			
			if(result>0) {
				response.sendRedirect("detail.qu?bNo="+no);
			}else {
				File failedFil = new File(savePath+saveFile);
				failedFil.delete();
				
				request.setAttribute("errorMsg", "문의게시글 수정에 실패하였습니다.");
				request.setAttribute("section", "WEB-INF/views/common/errorPage.jsp");
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
