package support.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import support.model.service.SupportService;

@WebServlet("/supportCheckSupNo.su")
public class SupportCheckSupNoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SupportCheckSupNoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String input_supNo = request.getParameter("input_supNo");
		int supNo = 0;
		
		if(input_supNo != null) {
			supNo = Integer.parseInt(input_supNo);
		}
		
		int check = new SupportService().checkSupNo(supNo);
		
		if(check == 1) {
			request.getSession().setAttribute("checkSupNo", "checked");
			response.getWriter().println("1");
		} else {
			response.getWriter().println("0");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
