package at.edu.uas.fm.webapp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import at.edu.uas.fm.db.DBHelper;

@WebServlet("/Test")
public class FacilityManagerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public FacilityManagerServlet() {
		// nothing to do
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String[] tasks = DBHelper.getTasks();
	    request.setAttribute("tasks", tasks);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("taskAssignView.jsp");
	    dispatcher.forward(request, response);
	}

}
