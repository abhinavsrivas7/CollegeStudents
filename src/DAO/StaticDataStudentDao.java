package DAO;

import java.util.ArrayList;
import java.util.List;

import Models.Student;
import Models.Subject;

public class StaticDataStudentDao implements StudentDao {
	
	private List<Student> students;
	
	public StaticDataStudentDao() {
		students = new ArrayList<Student>();
		
		Student student1 = new Student();
		student1.setId(1);
		student1.setName("Student1");
		student1.setAge(20);
		student1.setSubject(Subject.Science);
		student1.setCollegeID(1);
		
		Student student2 = new Student();
		student2.setId(2);
		student2.setName("Student2");
		student2.setAge(21);
		student2.setSubject(Subject.Commerce);
		student2.setCollegeID(1);
		
		Student student3 = new Student();
		student3.setId(3);
		student3.setName("Student3");
		student3.setAge(20);
		student3.setSubject(Subject.Arts);
		student3.setCollegeID(2);
		
		Student student4 = new Student();
		student4.setId(4);
		student4.setName("Student4");
		student4.setAge(21);
		student4.setSubject(Subject.Science);
		student4.setCollegeID(2);
		
		students.add(student1);
		students.add(student2);
		students.add(student3);
		students.add(student4);
	}
	
	@Override
	public List<Student> getStudents() {
		return students;
	}

	@Override
	public void setStudent(Student student) {
		student.setId(students.size() + 1);
		students.add(student);
	}

}
