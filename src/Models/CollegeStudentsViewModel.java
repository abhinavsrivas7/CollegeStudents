package Models;

import java.util.List;

public class CollegeStudentsViewModel {
	private College college;
	private List<Student> students;
	public College getCollege() {
		return college;
	}
	public void setCollege(College college) {
		this.college = college;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
}
