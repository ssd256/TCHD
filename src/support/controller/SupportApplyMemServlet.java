package support.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import support.model.service.SupportService;
import support.model.vo.Support;

@WebServlet("/supportApplyMember.su")
public class SupportApplyMemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SupportApplyMemServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String select = request.getParameter("select"); // 콤보박스 선택 내용 (10000 / 30000 / 50000 / 100000 / 직접입력)
		String input_direct = request.getParameter("input_direct"); // 콤보박스에서 "직접입력"을 선택하고 직접 적은 금액(1000단위로 ,찍힘)
		
		if(select.equals("직접입력")) {
			// 직접입력을 선택한 경우, input_direct의 콤마 제거
			String[] input = input_direct.split(",");
			// 사용자가 입력한 금액을 select에 저장
			select = "";
			for(int i = 0; i < input.length; i ++) {
				select += input[i];
			}
		}
		
		int price = Integer.parseInt(select);
		
		HttpSession session = request.getSession();
		String loginUserId = ((Member)session.getAttribute("loginUser")).getMem_id();
		
		Support support = new Support(loginUserId, price);
		
		int result = new SupportService().applyMem(support);
		
		if(result != 0) {
			response.getWriter().println("1");
		} else {
			response.getWriter().println("0");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
