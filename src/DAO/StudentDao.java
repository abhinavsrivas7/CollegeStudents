package DAO;

import java.util.List;

import Models.Student;

public interface StudentDao {
	public List<Student> getStudents();
	public void setStudent(Student student);
}
