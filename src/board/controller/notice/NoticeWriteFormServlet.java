package board.controller.notice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;


@WebServlet("/writeForm.no")
public class NoticeWriteFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public NoticeWriteFormServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if((Member)request.getSession().getAttribute("loginUser")==null) {
			request.setAttribute("section", "WEB-INF/views/common/errorPage.jsp");
			request.setAttribute("errorMsg", "세션이 만료되었습니다. 다시 로그인해주세요.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		
		request.setAttribute("section", "WEB-INF/views/notice/noticeWrite.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
