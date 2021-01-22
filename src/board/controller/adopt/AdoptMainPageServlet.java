package board.controller.adopt;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.google.gson.Gson;

import board.model.service.BoardService;
import board.model.vo.Adopt;
import board.model.vo.Files;



@WebServlet("/main.ad")
public class AdoptMainPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdoptMainPageServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Adopt> adoptList = new BoardService().selectAdoptMainPage();		
		response.setContentType("application/json; charset=UTF-8");

		new Gson().toJson(adoptList, response.getWriter());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
