package at.edu.uas.fm.webapp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import at.edu.uas.fm.db.DBHelper;

import com.mysql.jdbc.StringUtils;

@WebServlet("/Test")
public class FacilityManagerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public FacilityManagerServlet() {
		// nothing to do
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response, false);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response, true);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response, boolean isPostMethod)
			throws ServletException, IOException {

		String message = null;

		if (isPostMethod) {

			boolean isDeleteAction = "Delete".equals(request
					.getParameter("deleteAction"));
			boolean isSaveAction = "Save".equals(request
					.getParameter("saveAction"));

			if (isDeleteAction) {

				String deleteId = request.getParameter("deleteAssignmentId");
				boolean succesfulDeleted = false;

				if (!StringUtils.isNullOrEmpty(deleteId)) {

					try {
						succesfulDeleted = DBHelper.deleteAssignment(Long
								.valueOf(deleteId));
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}

				}

				if (succesfulDeleted) {
					message = "Successful deleted!";
				} else {
					message = "Error at deleting! Try again.";
				}

			} else if (isSaveAction) {

				String workObjectId = request
						.getParameter("selectedWorkObjectId");
				String taskId = request.getParameter("selectedTaskId");
				String frequency = request.getParameter("selectedFrequency");
				String workerId = request.getParameter("selectedWorkerId");
				String frequencyInformation = request
						.getParameter("frequencyInformation");

				if (!StringUtils.isNullOrEmpty(workObjectId)
						&& !StringUtils.isNullOrEmpty(taskId)
						&& !StringUtils.isNullOrEmpty(frequency)
						&& !StringUtils.isNullOrEmpty(workerId)
						&& !StringUtils.isNullOrEmpty(frequencyInformation)) {

					boolean successfulInserted = false;

					try {
						successfulInserted = DBHelper.insertAssignment(
								Long.valueOf(workObjectId),
								Long.valueOf(taskId), frequency,
								frequencyInformation, Long.valueOf(workerId));
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}

					if (successfulInserted) {
						message = "Successful inserted!";
					} else {
						message = "Error at inserting! Try again.";
					}

				} else {
					message = "Please provide all necessary information!";
				}

			}
		}

		Object[] workObjects = DBHelper.getAllWorkObjects();
		Object[] tasks = DBHelper.getTasks();
		Object[] workers = DBHelper.getAllWorkers();
		String[] frequencies = DBHelper.getFrequencies();

		Object[] assignments = DBHelper.getAllAssignments();
		Object[] assignmentsView = prepareAssignmentsForView(assignments,
				workObjects, tasks, workers);

		// for TESTING only
		//
		// Object[] workObjects = new Object[] { new Object[] { 1234L, 4444,
		// "Objektname" } };
		// Object[] tasks = new Object[] {
		// new Object[] { 1234L, "Putzen", "bla" },
		// new Object[] { 1235L, "Waschen", "bla" } };
		// Object[] workers = new Object[] {
		// new Object[] { 1234L, "", "Peter", "Stehter" },
		// new Object[] { 1235L, "", "Ge", "org" } };
		// String[] frequencies = DBHelper.getFrequencies();
		//
		// Object[] assignmentsView = new Object[] {
		// new Object[] { 1234L, "Once", "Info1", "Putzen", "Objektname",
		// "Peter Stehter" },
		// new Object[] { 1235L, "Once", "Info1", "Putzen", "Objektname",
		// "Peter Stehter" } };

		request.setAttribute("assignments", assignmentsView);
		request.setAttribute("workObjects", workObjects);
		request.setAttribute("tasks", tasks);
		request.setAttribute("workers", workers);
		request.setAttribute("frequencies", frequencies);
		request.setAttribute("message", message);

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
