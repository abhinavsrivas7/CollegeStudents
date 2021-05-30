package Service;

import Models.*;

public interface CollegeStudentService {
	public ServiceResponse createCollege(College college);
	public ServiceResponse createStudent(Student student);
	public ServiceResponse getCollegeStudents();
	public ServiceResponse getStudents(String collegeName, Subject subject);
}
