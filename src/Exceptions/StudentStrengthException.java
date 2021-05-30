package Exceptions;

@SuppressWarnings("serial")
public class StudentStrengthException extends Exception {
	private String collegeName;

	public StudentStrengthException(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getMessage() {
		return "Exceeding total strength of students for the specified college: " + collegeName;
	}
}
