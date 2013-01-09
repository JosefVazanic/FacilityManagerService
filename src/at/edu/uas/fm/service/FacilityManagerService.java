package at.edu.uas.fm.service;

import at.edu.uas.fm.db.DBHelper;

public class FacilityManagerService {

	public FacilityManagerService() {
		// nothing to do
	}

	public String[] getTaskList() {

		return DBHelper.getTasks();
	}
}
