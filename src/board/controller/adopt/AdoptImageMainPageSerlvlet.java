package board.controller.adopt;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import board.model.service.BoardService;
import board.model.vo.Files;


@WebServlet("/mainImage.ad")
public class AdoptImageMainPageSerlvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdoptImageMainPageSerlvlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Files> fileList = new BoardService().selectAdoptImageMainPage();
		response.setContentType("application/json; charset=UTF-8");

		new Gson().toJson(fileList, response.getWriter());
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
