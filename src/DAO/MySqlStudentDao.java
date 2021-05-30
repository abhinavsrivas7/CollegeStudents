package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Models.Student;
import Models.Subject;

public class MySqlStudentDao implements StudentDao {

	@Override
	public List<Student> getStudents() {
		List<Student> students = new ArrayList<Student>();
		try {
			Connection c = DriverManager.getConnection("com.mysql.cj.jdbc.driver");
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery("select * from Colleges");
			while(r.next()) {
				Student student = new Student();
				student.setId(r.getInt(1));
				student.setName(r.getString(2));
				student.setAge(r.getInt(3));
				student.setSubject(resolveSubject(r.getInt(4)));
				student.setCollegeID(r.getInt(5));
				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}

	private Subject resolveSubject(int i) {
		switch(i) {
		case 1: return Subject.Arts;
		case 2: return Subject.Science;
		case 3: return Subject.Commerce;
		}
		return null;
	}

	@Override
	public void setStudent(Student student) {
		// TODO Auto-generated method stub

	}

}
