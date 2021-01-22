package board.controller.notice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.vo.Notice;


@WebServlet("/updateForm.no")
public class NoticeUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public NoticeUpdateFormServlet() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("noticeNo"));
		String title = request.getParameter("noticeTitle");
		String content = request.getParameter("noticeContent");
		String subject  = request.getParameter("noticeSubject");

		int fileNo=0;
		if(request.getParameter("noticeFileNo")!=null) {
			fileNo = Integer.parseInt(request.getParameter("noticeFileNo"));
			
		}
		
		Notice notice = new Notice(no, title, content, null, 0, null, subject, 0);
		System.out.println(notice);
		request.setAttribute("fileNo", fileNo);
		request.setAttribute("notice", notice);
		request.setAttribute("section", "WEB-INF/views/notice/noticeUpdate.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
