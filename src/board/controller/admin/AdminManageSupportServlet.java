package board.controller.admin;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.PageInfo;
import member.model.vo.Member;
import support.model.vo.Support;


@WebServlet("/manageSupport.bo")
public class AdminManageSupportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdminManageSupportServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if((Member)request.getSession().getAttribute("loginUser")==null) {
			request.setAttribute("section", "WEB-INF/views/common/errorPage.jsp");
			request.setAttribute("errorMsg", "세션이 만료되었습니다. 다시 로그인해주세요.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}	
		
		String date = request.getParameter("search"); 
		Date searchDate = null;
		if(date==null) {
			Calendar today = Calendar.getInstance();
			int year = today.get(Calendar.YEAR);
			int month = today.get(Calendar.MONTH);
			int day= 1;			
			searchDate = new Date(new GregorianCalendar(year,month,day).getTimeInMillis());
		}else {
			String[] dateArr = date.split("-");
			int year = Integer.parseInt(dateArr[0]);
			int month = Integer.parseInt(dateArr[1])-1;
			int day= 1;
			searchDate = new Date(new GregorianCalendar(year,month,day).getTimeInMillis());
		}
		///////////////////////////////////////////////////////
		
		int[] listCount;   // 총 게시글 개수
		int currentPage;  // 현재 페이지
		int pageLimit;   // 한 페이지에서 표시될 페이지수
		int boardLimit;  // 한 페이지에서 표시될 게시글 최대 개수 
		int maxPage;   //  전체페이지 중 가장 마지막 페이지
		int startPage;  // 페이징 된 페이지 중 시작 페이지
		int endPage;   // 페이징 된 페이 중 마지막 페이지
		
		listCount = new BoardService().getManageSupportCount(searchDate);
		
		currentPage =1;
		if(request.getParameter("currentPage")!=null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		

		pageLimit = 3;
		boardLimit = 10;
		
		maxPage = (int) Math.ceil((double)listCount[0]/boardLimit);
		startPage = pageLimit*((currentPage-1)/pageLimit) + 1; //  1, 11, 21, 31, .... //   10*n + 1(n>=0)
		endPage =  startPage + pageLimit - 1;// 10,20,30,40
		
		if(maxPage == endPage) {
			endPage=maxPage;
		}
		
		PageInfo pi = new PageInfo(currentPage,listCount[0],pageLimit,boardLimit,maxPage,startPage, endPage);
		
		///////////////////////////////////////////////////////
		ArrayList<Support> supportList = new BoardService().selectManageSupport(searchDate,pi);
		request.setAttribute("totalPrice", listCount[1]);
	
		request.setAttribute("search", date);
		request.setAttribute("supportList", supportList);
		request.setAttribute("pi", pi);
		request.setAttribute("section", "WEB-INF/views/admin/manageSupport_admin.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
