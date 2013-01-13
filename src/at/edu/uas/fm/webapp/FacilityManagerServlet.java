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

		Object[] workObjects = DBHelper.getAllWorkObjects();
		Object[] tasks = DBHelper.getTasks();
		Object[] workers = DBHelper.getAllWorkers();
		String[] frequencies = DBHelper.getFrequencies();

		Object[] assignments = DBHelper.getAllAssignments();
		Object[] assignmentsView = prepareAssignmentsForView(assignments,
				workObjects, tasks, workers);

		request.setAttribute("assignments", assignmentsView);
		request.setAttribute("workObjects", workObjects);
		request.setAttribute("tasks", tasks);
		request.setAttribute("workers", workers);
		request.setAttribute("frequencies", frequencies);

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("assignmentView.jsp");
		dispatcher.forward(request, response);
	}

	private Object[] prepareAssignmentsForView(Object[] assignments,
			Object[] workObjects, Object[] tasks, Object[] workers) {
		Object[] assignmentsView = new Object[assignments.length];
		int index = 0;

		for (Object assignmentDataObject : assignments) {

			Object[] assignmentData = (Object[]) assignmentDataObject;
			Object[] assignmentView = new Object[assignmentData.length];

			assignmentView[0] = assignmentData[0];
			assignmentView[1] = assignmentData[1];
			assignmentView[2] = assignmentData[2];

			Long taskId = (Long) assignmentData[3];
			for (Object taskData : tasks) {
				Object[] task = (Object[]) taskData;
				if (taskId.equals(task[0])) {
					assignmentView[3] = task[1];
					break;
				}
			}

			Long workObjectId = (Long) assignmentData[4];
			for (Object workObjectData : workObjects) {
				Object[] workObject = (Object[]) workObjectData;
				if (workObjectId.equals(workObject[0])) {
					assignmentView[4] = workObject[1] + "; " + workObject[2];
					break;
				}
			}

			Long workerId = (Long) assignmentData[5];
			for (Object workerData : workers) {
				Object[] worker = (Object[]) workerData;
				if (workerId.equals(worker[0])) {
					assignmentView[5] = worker[2] + " " + worker[3];
					break;
				}
			}

			assignmentsView[index] = assignmentView;
			index++;
		}

		return assignmentsView;
	}

}
