package board.controller.volunteer;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

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
import board.model.vo.Volunteer;
import common.MyFileRenamePolicy;

/**
 * Servlet implementation class VolunteerUpdateServlet
 */
@WebServlet("/volunteerUpdate.bo")
public class VolunteerUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VolunteerUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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

			int volBNo = Integer.parseInt(multiRequest.getParameter("volBNo"));
					
			String volCateName = multiRequest.getParameter("volCateName");
			
			String boTitle = multiRequest.getParameter("input_boTitle");
			
			String voArea = multiRequest.getParameter("voArea");
			
			String voDate2 = multiRequest.getParameter("input_voDate");
			System.out.println(voDate2);
			String[] vo_dateArr = voDate2.split("-");
			int year = Integer.parseInt(vo_dateArr[0]);
			int month = Integer.parseInt(vo_dateArr[1]);
			String month2 = null;
			if(month<10) {month2="0"+month;}else {month2=month+"";}
			int day = Integer.parseInt(vo_dateArr[2].split("T")[0]);
			String day2 = null;
			if(day<10) {day2="0"+day;}else {day2=day+"";}
			int hour = Integer.parseInt(vo_dateArr[2].split("T")[1].split(":")[0]);
			String hour2= null;
			if(hour<10) { hour2="0"+hour;}else { hour2=hour+"";}
			int min = Integer.parseInt(vo_dateArr[2].split("T")[1].split(":")[1]);
			String min2 =null;
			if(min<10) { min2="0"+min;}else {min2=min+"";}
			String inputDate = year+"-"+month2+"-"+day2+" "+hour2+":"+min2+":00";
			Timestamp voDate = Timestamp.valueOf(inputDate);
			
			// 봉사지.
			String zonecode= multiRequest.getParameter("input_zoneCode");
			String address = multiRequest.getParameter("input_joinAddress");
			String address2 = multiRequest.getParameter("input_joinAddress2");
			String voPlace = null;
			if(!zonecode.equals("")) {
//				voPlace = "("+zonecode+")"+" "+address+", "+address2;
				voPlace = zonecode+","+address+","+address2;
			}
			
			// 봉사 정원.
			String voMaxmember2 = multiRequest.getParameter("input_voMaxmember");
			int voMaxmember = Integer.parseInt(voMaxmember2);
			
			// 내용.
			String voComment = multiRequest.getParameter("input_voComment");
			
			Volunteer volunteer = new Volunteer(volBNo, 0, volCateName, boTitle, 0, null, 0, null, null, voMaxmember, 0, null, voDate, voArea, voPlace, voComment);
			int fileNo =0;
	
			if(multiRequest.getParameter("volunteerFileNo")!=null) {
				fileNo = Integer.parseInt(multiRequest.getParameter("volunteerFileNo"));
		
			}
			Files file = new Files(volBNo, originFile, saveFile, savePath, 0);
			
			int result = new BoardService().updateVolunteer(volunteer, file);
			
			
			if(result>0) {
				response.sendRedirect("volunteerDetail.bo?bNo="+volBNo);
			}else {
				File failedFile = new File(savePath+saveFile);
				failedFile.delete();
				
				request.setAttribute("errorMsg", "게시글 수정에 실패하였습니다.");
				request.setAttribute("section", "WEB-INF/views/common/errorPage.jsp");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
