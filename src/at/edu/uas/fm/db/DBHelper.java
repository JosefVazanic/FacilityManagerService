package at.edu.uas.fm.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.StringUtils;

public class DBHelper {

	private static final String USER_TYPE_WORKER = "Worker";
	private static final String USER_TYPE_EMPLOYEE = "Employee";
	private static final String USER_TYPE_CONTACT_PERSON = "ContactPerson";

	private static final String STATUS_TO_DO = "ToDo";
	private static final String STATUS_IN_PROGRESS = "InProgress";
	private static final String STATUS_DONE = "Done";
	private static final String STATUS_NOT_POSSIBLE = "NotPossible";

	private static final String FREQUENCY_ONCE = "Once";
	private static final String FREQUENCY_DAILY = "Daily";
	private static final String FREQUENCY_WEEKLY = "Weekly";
	private static final String FREQUENCY_MONTHLY = "Monthly";

	public static String[] getTasks() {
		String[] tasks = new String[] { "Fenster putzen", "Glühbirne wechseln",
				"Stiegenhaus wischen" };

		return tasks;
	}

	public static Boolean authenticate(Long userId, String username,
			String password) {
		PreparedStatement statement = null;
		ResultSet queryResult = null;
		Boolean result = false;

		if (userId == null || !StringUtils.isNullOrEmpty(username)
				&& !StringUtils.isNullOrEmpty(username)) {

			try {
				// get user from database using the user name
				String query = "SELECT * FROM user WHERE UserId = ? AND UserName = ?";
				statement = getConnection().prepareStatement(query);
				statement.setLong(1, userId);
				statement.setString(2, username);

				queryResult = statement.executeQuery();
				// we've got results - and we only check the first one
				if (queryResult.first()
						&& (password.equals(queryResult
								.getString("UserPassword")))) {
					// at least one entry existed AND the password does match
					result = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (statement != null) {
						statement.close();
					}
					if (queryResult != null) {
						queryResult.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return result;
	}

//	public static Object[] getAllWorkers() {
//		Object[] worker = new Object[] { new Long(1234), "username", "Vorname",
//				"Nachname", new Date(System.currentTimeMillis()), null, null,
//				"a@gmx.at", null, null };
//
//		Object[] result = new Object[] { worker };
//		return result;
//	}

	public static Object[] getAllWorkers() {

		PreparedStatement statement = null;
		ResultSet queryResult = null;
		Object[] result = new Object[0];

		try {
			// get user from database using the user name

			String query = "SELECT * FROM user WHERE UserType = ?";
			statement = getConnection().prepareStatement(query);
			statement.setString(1, USER_TYPE_WORKER);
			queryResult = statement.executeQuery();

			List<Object> workerList = new ArrayList<Object>();

			// we've got results
			while (queryResult.next()) {
				Object[] worker = new Object[10];

				worker[0] = queryResult.getLong("UserId");
				worker[1] = queryResult.getString("UserName");
				worker[2] = queryResult.getString("FirstName");
				worker[3] = queryResult.getString("LastName");
				worker[4] = queryResult.getDate("DateOfBirth");
				worker[5] = queryResult.getString("MobilePhoneNumber");
				worker[6] = queryResult.getString("TelephoneNumber");
				worker[7] = queryResult.getString("EMailAddress");
				worker[8] = queryResult.getLong("Latitude");
				worker[9] = queryResult.getLong("Longitude");

				workerList.add(worker);
			}

			if (workerList.size() > 0) {
				result = workerList.toArray();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (queryResult != null) {
					queryResult.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private static Connection getConnection() {
		return MySQLConnector.getConnection();
	}

}
