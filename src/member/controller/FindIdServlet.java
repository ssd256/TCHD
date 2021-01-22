package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.sendMail;
import member.model.service.MemberService;
import member.model.vo.Member;

@WebServlet("/findId.me")
public class FindIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FindIdServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("input_name");
		String email = request.getParameter("input_email");
		Member member = new Member(0, "",name, email); // 생성자가 겹쳐서 mem_no, mem_id, mem_name, mem_email로 사용
		
		Member findUser = new MemberService().findId(member);
		
		if(findUser != null) {
			// 이메일 전송
			try {
				String id =findUser.getMem_id();
				String cut_id = id.substring(0, 3);
				
				new sendMail().sendEmail("id", email, cut_id);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			response.getWriter().println("1");
			
		} else {
			response.getWriter().println("0");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
