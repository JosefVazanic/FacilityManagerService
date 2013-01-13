package at.edu.uas.fm.service;

import at.edu.uas.fm.db.DBHelper;

public class FacilityManagerService {

	public FacilityManagerService() {
		// nothing to do
	}

	// Important type restrictions!!!

	// Not working: Long; Map
	// Working & tested: Integer, String, Boolean, Date, ???[], List<???>
	// Changing types: List<???> -> Object[???]; ???[] -> Object[???]

	// ??? means any of the working & tested types

	public Object[] getTaskList(Object[] userId) {
		return DBHelper.getTasks();
	}

	public Object[] getWorkerList() {
		return DBHelper.getAllWorkers();
	}

	public Boolean authenticate(Object[] authenticationData) {
		return DBHelper.authenticate((Long) authenticationData[0],
				(String) authenticationData[1], (String) authenticationData[2]);
	}

	//
	// public Object[] getTypeTest(Object[] data) {
	// Integer a = (Integer) data[0];
	// String b = (String) data[1];
	// Boolean c = (Boolean) data[2];
	// Date d = (Date) data[3];
	//
	// Object[] result = new Object[] {a, b, c, d};
	// return result;
	// }
	//
	// public Object[] getTypeTest2(Object[] data) {
	// Object[] a = (Object[]) data[0];
	// Object[] b = (Object[]) data[1];
	//
	// Object[] result = new Object[] {a, b};
	// return result;
	// }

}
