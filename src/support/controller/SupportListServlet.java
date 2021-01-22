package support.controller;

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
import javax.servlet.http.HttpSession;

import board.model.vo.PageInfo;
import member.model.vo.Member;
import support.model.service.SupportService;
import support.model.vo.Support;

@WebServlet("/supportList.su")
public class SupportListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SupportListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String checkSupNo = (String)request.getSession().getAttribute("checkSupNo");
		SupportService service = new SupportService();

		// 페이징
		PageInfo pi = null;
				
		int listCount;		// 총 게시글 개수
		int currentPage;	// 현재 페이지 번호
		int pageLimit;		// 한 페이지에서 표시될 페이징 수
		int supportLimit;	// 한 페이지에 보일 후원 내역의 최대 개수
		int maxPage;		// 전체 페이지 중 가장 마지막 페이지
		int startPage;		// 페이징 된 페이지 중 시작 페이지
		int endPage;		// 페이징 된 페이지 중 마지막 페이지
		
		if(session.getAttribute("loginUser") == null) {
		// 비회원
			if(checkSupNo == null) {
				// 후원 번호 체크 안됐으면 체크하러
				request.setAttribute("section", "WEB-INF/views/support/supportCheckSupNo.jsp");
			} else {
				// 후원 번호 체크 됐으면
				// 세션영역에 저장된 checkSupNo 지워서 새로고침하면 다시 입력하게
				request.getSession().setAttribute("checkSupNo", null);
				
				String supNo = (String)request.getParameter("supNo");
				request.setAttribute("supNo", supNo);
				Support support = service.selectListNonMem(supNo);
				request.setAttribute("support", support);
				request.setAttribute("section", "WEB-INF/views/support/supportList.jsp");
			}
		} else {
		// 회원 (로그인)
			int mem_no = ((Member)session.getAttribute("loginUser")).getMem_no();
			
			String date = request.getParameter("search"); 
			Date searchDate = null;
			if(date == null) {
				Calendar today = Calendar.getInstance();
				int year = today.get(Calendar.YEAR);
				int month = today.get(Calendar.MONTH);
				int day = 1;			
				searchDate = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
			}else {
				String[] dateArr = date.split("-");
				int year = Integer.parseInt(dateArr[0]);
				int month = Integer.parseInt(dateArr[1]) - 1;
				int day = 1;
				searchDate = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
			}
			
			// 페이징
			int[] listTotal = service.getListCount(mem_no, searchDate);
			listCount = listTotal[0];
			int totalPrice = listTotal[1];
			
			currentPage = 1;
			
			if(request.getParameter("currentPage") != null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			
			pageLimit = 10;
			supportLimit = 10;
			
			maxPage = (int)Math.ceil((double)listCount/supportLimit);
			startPage = pageLimit * ((currentPage - 1) / pageLimit) + 1;
			endPage = startPage + pageLimit - 1;
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			
			pi = new PageInfo(currentPage, listCount, pageLimit, supportLimit, maxPage, startPage, endPage);
			
			// 넘겨넘겨~~
			ArrayList<Support> supportList = service.selectListMem(mem_no, searchDate, pi);
			request.setAttribute("search", date);
			request.setAttribute("supportList", supportList);
			request.setAttribute("totalPrice", totalPrice);
			request.setAttribute("pi", pi);
			request.setAttribute("section", "WEB-INF/views/support/supportList.jsp");
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}