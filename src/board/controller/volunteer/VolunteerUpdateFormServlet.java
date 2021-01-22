package board.controller.volunteer;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.vo.Volunteer;
import member.model.vo.Member;

/**
 * Servlet implementation class VolunteerUpdateFormServlet
 */
@WebServlet("/volunteerUpdateForm.bo")
public class VolunteerUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VolunteerUpdateFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		String volMemId = request.getParameter("volMemId");
		String volMemNo = request.getParameter("volMemNo");
		
		
		int volBNo = Integer.parseInt(request.getParameter("volBNo"));
		
		String volCateName = request.getParameter("volCateName");
		
		String volBoTitle = request.getParameter("volBoTitle");
		
		String voArea = request.getParameter("voArea");
		
		// 봉사 일시.
		String voDate2 = request.getParameter("voDate");

		String[] vo_dateArr = voDate2.split("-");
		int year = Integer.parseInt(vo_dateArr[0]);
		int month = Integer.parseInt(vo_dateArr[1]);
		String month2 = null;
		if(month<10) {month2="0"+month;}else {month2=month+"";}
		int day = Integer.parseInt(vo_dateArr[2].split(" ")[0]);
		String day2 = null;
		if(day<10) {day2="0"+day;}else {day2=day+"";}
		int hour = Integer.parseInt(vo_dateArr[2].split(" ")[1].split(":")[0]);
		String hour2= null;
		if(hour<10) { hour2="0"+hour;}else { hour2=hour+"";}
		int min = Integer.parseInt(vo_dateArr[2].split(" ")[1].split(":")[1]);
		String min2 =null;
		if(min<10) { min2="0"+min;}else {min2=min+"";}
		String inputDate = year+"-"+month2+"-"+day2+" "+hour2+":"+min2+":00";
		Timestamp voDate = Timestamp.valueOf(inputDate);
		
		// 봉사지.
		String zonecode= request.getParameter("zoneCode");
		String address = request.getParameter("joinAddress");
		String address2 = request.getParameter("joinAddress2");
		String voPlace= request.getParameter("voPlace");
		
		// 봉사 정원.
		String voMaxmember2 = request.getParameter("voMaxmember");
		int voMaxmember = Integer.parseInt(voMaxmember2);
		
		// 내용.
		String voComment = request.getParameter("voComment");
		
		int fileNo=0;
		if(request.getParameter("volunteerFileNo")!=null) {
			fileNo = Integer.parseInt(request.getParameter("volunteerFileNo"));
		}
		
		Volunteer volunteer = new Volunteer(volBNo, 0, volCateName, volBoTitle, 0, null, 0, null, null, voMaxmember, 0, null, voDate, voArea, voPlace, voComment);
		
		request.setAttribute("fileNo", fileNo);
		request.setAttribute("volunteer", volunteer);
		request.setAttribute("section", "WEB-INF/views/volunteer/volunteerUpdate.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
