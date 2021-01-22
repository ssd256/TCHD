package member.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;


@WebServlet("/join.me")
public class JoinMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public JoinMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberType = request.getParameter("selectMemberType");
		String id = request.getParameter("joinId");
		String pwd = request.getParameter("joinPwd");
		String name = request.getParameter("joinName");
		String tel = request.getParameter("joinTel");
		String zonecode= request.getParameter("joinZoneCode");
		String address = request.getParameter("joinAddress");
		String address2 = request.getParameter("joinAddress2");
		String addressTotal =null;
		if(!zonecode.equals("")) {
			addressTotal = zonecode+","+address+","+address2;
		}
		String email = request.getParameter("joinEmail");
		String email2 = request.getParameter("joinEmail2");
		String emailTotal = email+"@"+email2;
	
		String birth = request.getParameter("joinBirth");
		int year=0;
		int month=0;
		int day=0;
		
		
		if(birth!=null) {
			
			String[] birthArr =  birth.split("-");
			year =Integer.parseInt(birthArr[0]);
			month=Integer.parseInt(birthArr[1])-1;
			day= Integer.parseInt(birthArr[2]);
		}
		Date birth2 = new Date(new GregorianCalendar(year,month,day).getTimeInMillis());
		
	
		String gmname = request.getParameter("joinGmName");
		String regno = request.getParameter("joinRegNo");
	
		Member member=null;
		if(memberType.equals("PM")) {
			member = new Member(0,memberType,id,pwd,name,tel,addressTotal,emailTotal,birth2);
		}else {
			member = new Member(0,memberType,id,pwd,name,tel,addressTotal,emailTotal,regno,gmname);
		}
		
		int result=new MemberService().insertMember(member);
		
		if(result>0) {
			request.setAttribute("section", "WEB-INF/views/member/joinComplete.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath());
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
