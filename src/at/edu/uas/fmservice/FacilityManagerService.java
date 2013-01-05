package at.edu.uas.fmservice;

public class FacilityManagerService {

	public FacilityManagerService() {
		// nothing to do
	}

	public String[] getTaskList() {
		String[] tasks = new String[] { "Fenster putzen", "Glühbirne wechseln",
				"Stiegenhaus wischen" };
		return tasks;
	}
}
