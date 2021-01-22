package animalHospital.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import animalHospital.model.service.AnimalHospitalService;
import animalHospital.model.vo.AnimalHospital;
import board.model.vo.PageInfo;

@WebServlet("/hospitalList.ho")
public class AnimalHospitalListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AnimalHospitalListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<AnimalHospital> hospitalList = null;
		AnimalHospitalService service = new AnimalHospitalService();
		String addr = (String)request.getParameter("addr");
		String selectedAddr = addr;
		
		// 페이징
		PageInfo pi = null;
		
		int listCount;		// 총 게시글 개수
		int currentPage;	// 현재 페이지 번호
		int pageLimit;		// 한 페이지에서 표시될 페이징 수
		int hospitalLimit;	// 한 페이지에 보일 병원의 최대 개수
		int maxPage;		// 전체 페이지 중 가장 마지막 페이지
		int startPage;		// 페이징 된 페이지 중 시작 페이지
		int endPage;		// 페이징 된 페이지 중 마지막 페이지
		
		if(addr == null || addr.equals("전체보기")) {
			// 지역 선택하지 않은 경우 & 전체보기를 선택한 경우
			listCount = service.getAllListCount();
			
			currentPage = 1;
			
			if(request.getParameter("currentPage") != null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			
			pageLimit = 10;
			hospitalLimit = 10;
			
			maxPage = (int)Math.ceil((double)listCount/hospitalLimit);
			startPage = pageLimit * ((currentPage - 1) / pageLimit) + 1;
			endPage = startPage + pageLimit - 1;
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			
			pi = new PageInfo(currentPage, listCount, pageLimit, hospitalLimit, maxPage, startPage, endPage);
			
			hospitalList = service.allHospitalList(pi);
		} else {
			// 지역 선택한 경우
			listCount = service.getselectListCount(addr);
			
			currentPage = 1; // url에 page속성이 없으면 page=1 ==> 메인페이지
			
			if(request.getParameter("currentPage") != null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			
			pageLimit = 10;
			hospitalLimit = 10;
			
			maxPage = (int)Math.ceil((double)listCount/hospitalLimit);
			startPage = pageLimit * ((currentPage - 1) / pageLimit) + 1;
			endPage = startPage + pageLimit - 1;
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			
			pi = new PageInfo(currentPage, listCount, pageLimit, hospitalLimit, maxPage, startPage, endPage);
			
			hospitalList = service.selectAddr(pi, addr);
		}
		
		request.setAttribute("hospitalList", hospitalList);
		request.setAttribute("selectedAddr", selectedAddr);
		request.setAttribute("pi", pi);
		request.setAttribute("section", "WEB-INF/views/animalHospital/animalHospitalList.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}