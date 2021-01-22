package board.controller.notice;

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
import common.MyFileRenamePolicy;


@WebServlet("/update.no")
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public NoticeUpdateServlet() {
        super();
    }


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
			
			int no = Integer.parseInt(multiRequest.getParameter("noticeNo"));
			String selectBoard = multiRequest.getParameter("selectBoard");
			String title= multiRequest.getParameter("input_title");
			String content = multiRequest.getParameter("input_content");
			Notice notice = new Notice(no, title, content, null, 0, null, selectBoard, 0);
			
			int fileNo =0;
	
			if(multiRequest.getParameter("noticeFileNo")!=null) {
				fileNo = Integer.parseInt(multiRequest.getParameter("noticeFileNo"));
		
			}
			Files file = new Files(fileNo, no, originFile, saveFile, savePath, null, 0, 0, null);
			
			int result = new BoardService().updateNotice(notice, file);
			
			
			if(result>0) {
				response.sendRedirect("detail.no?bNo="+no);
			}else {
				File failedFil = new File(savePath+saveFile);
				failedFil.delete();
				
				request.setAttribute("errorMsg", "공지사항 수정에 실패하였습니다.");
				request.setAttribute("section", "WEB-INF/views/common/errorPage.jsp");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
