package animalHospital.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import animalHospital.model.service.AnimalHospitalService;
import animalHospital.model.vo.AnimalHospital;

@WebServlet("/hospitalDetail.ho")
public class AnimalHospitalDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AnimalHospitalDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int hosNo = Integer.parseInt(request.getParameter("hosNo"));
		
		AnimalHospital hospital = new AnimalHospitalService().selectHospital(hosNo);
		
		request.setAttribute("hospital", hospital);
		request.setAttribute("section", "WEB-INF/views/animalHospital/animalHospitalDetail.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
