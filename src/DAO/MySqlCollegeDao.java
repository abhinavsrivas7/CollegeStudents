package DAO;

import java.util.ArrayList;
import java.util.List;
import Models.College;
import java.sql.*;

public class MySqlCollegeDao implements CollegeDao {

	@Override
	public List<College> getColleges() {
		List<College> colleges = new ArrayList<College>();
		try {
			Connection c = DriverManager.getConnection("com.mysql.cj.jdbc.driver");
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery("select * from Colleges");
			while(r.next()) {
				College college = new College();
				college.setId(r.getInt(1));
				college.setName(r.getString(2));
				college.setMaxStrength(r.getInt(3));
				colleges.add(college);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return colleges;
	}

	@Override
	public void setCollege(College college) {
		// TODO Auto-generated method stub

	}

}
