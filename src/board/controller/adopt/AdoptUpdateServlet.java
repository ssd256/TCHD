package board.controller.adopt;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import board.model.service.BoardService;
import board.model.vo.Adopt;
import board.model.vo.Board;
import board.model.vo.Files;
import common.MyFileRenamePolicy;
import member.model.vo.Member;

/**
 * Servlet implementation class AdoptUpdateServlet
 */
@WebServlet(urlPatterns="/adoptUpdate.bo", name="AdoptUpdateServlet")
public class AdoptUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdoptUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		int bNo = Integer.parseInt(request.getParameter("boNo"));
		
		Board b = new Board();
		Adopt a = new Adopt();
		
		// insert 했던 입양 동물 정보
		String petKind = request.getParameter("petKind");		// 동물 구분 체크박스
		String petGender = request.getParameter("petGender");	// 동물성별 체크박스;
		String unigender = "";
		if(request.getParameter("unigender") != null) {
			unigender = "O";		// 중성화 여부
		} else {
			unigender = "X";
		}
		String petSize = request.getParameter("petSize");			// 크기
		String petAge = request.getParameter("petAge") + "/" 		// 나이
						+ request.getParameter("petAgeDetail")
					    + request.getParameter("detailAge"); 		// 나이
		String petName = request.getParameter("petName");			// 이름
		String petCategory = request.getParameter("petCategory");	// 품종
		float petWeight = Float.valueOf(request.getParameter("petWeight"));	// 무게
		String petColor = request.getParameter("petColor");		// 색깔
		String rescue = request.getParameter("rescue");
		
		String lastMent = "";
		if(request.getParameter("lastMent") != null) {
			lastMent = request.getParameter("lastMent");		// 하고 싶은 말
		} else {
			lastMent = "";
		}
		String content = petKind + ", " + petGender + ", " + unigender + ", " + petSize + ", " + petAge + ", " + 
				petName + ", " + petCategory + ", " + petWeight + ", " + petColor + ", " + 
				rescue + ", " + lastMent;
			
			
			b.setBoNo(bNo);
			b.setBoTitle(petName);
			b.setBoContent(content);
			b.setMemNo(loginUser.getMem_no());
			
			a.setBoNo(bNo);
			a.setId(loginUser.getMem_id());
			a.setPetName(petName);
			a.setPetKinds(petKind);
			a.setPetCategory(petCategory);
			a.setPetGender(petGender);
			a.setPetUnigender(unigender);
			a.setPetAge(petAge);
			a.setPetWeight(petWeight);
			a.setPetSize(petSize);
			a.setPetColor(petColor);
			a.setRescueDate(rescue);
			a.setPetComment(lastMent);
			
			int result = new BoardService().updateAdopt(b, a);
			ArrayList<Files> fileList = new BoardService().selectNoticeFile(bNo);
			
			if(result > 0) {
				request.setAttribute("loginUser", loginUser);
				request.setAttribute("adopt", a);
				request.setAttribute("rescue", rescue);
				request.setAttribute("fileList", fileList);
				request.setAttribute("section", "WEB-INF/views/adopt/adoptDetail.jsp");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				
			} else {
				response.sendRedirect("adoptDetail.bo?boNo=" + bNo);
			}
			
		}
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
