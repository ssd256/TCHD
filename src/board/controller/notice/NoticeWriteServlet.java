package board.controller.notice;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

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


@WebServlet("/write.no")
public class NoticeWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public NoticeWriteServlet() {
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
		
			String selectBoard = multiRequest.getParameter("selectBoard");
			String title= multiRequest.getParameter("input_title");
			String content = multiRequest.getParameter("input_content");
		
	
			Notice notice = new Notice(0,title, content, null, 0, null, selectBoard, 0);
		
			Files uploadFile =  new Files();
			uploadFile.setFilePath(savePath);
			uploadFile.setOrignName(originFile);
			uploadFile.setChangeName(saveFile);
			
			
			
			int result = new BoardService().insertNotice(notice, uploadFile);
			
			if(result>0) {
				response.sendRedirect("list.no");
			}else {
				
				File failedFil = new File(savePath+saveFile);
				failedFil.delete();
				
				request.setAttribute("errorMsg", "공지사항 등록에 실패하였습니다.");
				request.setAttribute("section","WEB-INF/views/common/errorPage.jsp");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
