package Exceptions;

@SuppressWarnings("serial")
public class CollegeNotFoundException extends Exception {
	private String collegeName;

	public CollegeNotFoundException(String collegeName) {
		this.collegeName = collegeName;
	}
	
	public String getMessage() {
		return "No such college exists with name: " + collegeName;
	}
	
}
