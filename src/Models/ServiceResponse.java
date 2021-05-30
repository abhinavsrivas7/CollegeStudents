package Models;

import java.util.List;

public class ServiceResponse {
	private String errorMessage;
	private boolean isError;
	private String successMessage;
	private List<CollegeStudentsViewModel> collegeStudents;
	private List<Student> students;
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public boolean isError() {
		return isError;
	}
	public void setError(boolean isError) {
		this.isError = isError;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	public List<CollegeStudentsViewModel> getCollegeStudents() {
		return collegeStudents;
	}
	public void setCollegeStudents(List<CollegeStudentsViewModel> collegeStudents) {
		this.collegeStudents = collegeStudents;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
}
