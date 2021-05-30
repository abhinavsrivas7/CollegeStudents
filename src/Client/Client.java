package Client;

import java.util.Scanner;
import Service.*;
import DAO.*;
import Models.College;
import Models.CollegeStudentsViewModel;
import Models.ServiceResponse;
import Models.Student;
import Models.Subject;

public class Client {
	static Scanner scanner = new Scanner(System.in);
	static CollegeStudentService service;
	public static void main(String[] args) {
		System.out.println("Welcome to College Management");
		System.out.println("Enter 1 for static data store");
		System.out.println("Enter 2 for DB data store");
		int dataStore = scanner.nextInt();
		scanner.nextLine();
		switch(dataStore) {
		case 1 : service = new Service(new StaticDataCollegeDao(), new StaticDataStudentDao());
			break;
		case 2: service = new Service(new MySqlCollegeDao(), new MySqlStudentDao());
			break;
		default: System.out.println("Wrong choice 1/2");
		}
		int flag = 0;
		while(flag != 1) {
			System.out.println("Enter your choice");
			System.out.println("Enter 1 to create new college");
			System.out.println("Enter 2 to create new student");
			System.out.println("Enter 3 to show all colleges and their students");
			System.out.println("Enter 4 to show all students by college and subject name");
			System.out.println("Enter 5 to exit");
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch(choice) {
			case 1: createNewCollege();
				break;
			case 2: createNewStudent();
				break;
			case 3: fetchAllData();
				break;
			case 4: showStudentsByCollegeAndSubject();
				break;
			case 5: flag = 1;
				break;
			default : System.out.println("Wrong choice. Try again");
				break;
			}
		}		
	}
	private static void showStudentsByCollegeAndSubject() {
		System.out.println("Enter the subject");
		String subject = scanner.nextLine();
		Subject studentSubject = resolveSubjectByName(subject);
		if(studentSubject == null) {
			System.out.println("Invalid subject, please try again. Allowed subjects: science, arts, commerce");
			return;
		}
		System.out.println("Enter college name");
		String college = scanner.nextLine();
		ServiceResponse response = service.getStudents(college, studentSubject);
		if(response.isError())
			System.out.println(response.getErrorMessage());
		else {
			for (Student student : response.getStudents()) {
				printStudent(student);
			}
		}
	}
	private static void fetchAllData() {
		ServiceResponse response = service.getCollegeStudents();
		for (CollegeStudentsViewModel viewModel : response.getCollegeStudents()) {
			printCollege(viewModel.getCollege());
			for (Student student : viewModel.getStudents()) {
				printStudent(student);
			}
			System.out.println("----------------------------------------------------");
		}
	}
	private static void printStudent(Student student) {
		System.out.println("Student ID: " + student.getId());
		System.out.println("Student Name: " + student.getName());
		System.out.println("Student Age: " + student.getAge());
		System.out.println("Student Subject: " + student.getSubject().name());
	}
	private static void printCollege(College college) {
		System.out.println("College ID: " + college.getId());
		System.out.println("College Name: " + college.getName());
		System.out.println("College Maximum Strength: " + college.getMaxStrength());
	}
	private static void createNewStudent() {
		Student student = new Student();
		System.out.println("Enter student name");
		student.setName(scanner.nextLine());
		System.out.println("Enter student age");
		student.setAge(scanner.nextInt());
		scanner.nextLine();
		System.out.println("Enter student subject");
		String subject = scanner.nextLine();
		Subject studentSubject = resolveSubjectByName(subject);
		if(studentSubject == null) {
			System.out.println("Invalid subject, please try again. Allowed subjects: science, arts, commerce");
			return;
		}
		student.setSubject(studentSubject);
		System.out.println("Enter the college ID of student");
		int collegeID = scanner.nextInt();
		scanner.nextLine();
		if(!isValidCollegeID(collegeID)) {
			System.out.println("The college ID doesnt exist. Please see all colleges using option 3 and try again.");
			return;
		}
		student.setCollegeID(collegeID);
		ServiceResponse response = service.createStudent(student);
		if(response.isError())
			System.out.println(response.getErrorMessage());
		else
			System.out.println(response.getSuccessMessage());
	}
	private static boolean isValidCollegeID(int collegeID) {
		ServiceResponse response = service.getCollegeStudents();
		for (CollegeStudentsViewModel viewModel : response.getCollegeStudents()) {
			if(viewModel.getCollege().getId() == collegeID)
				return true;
		}
		return false;
	}
	private static Subject resolveSubjectByName(String subject) {
		if(subject.equalsIgnoreCase(Subject.Science.name()))	return Subject.Science;
		else if(subject.equalsIgnoreCase(Subject.Commerce.name()))	return Subject.Commerce;
		else if(subject.equalsIgnoreCase(Subject.Arts.name()))	return Subject.Arts;
		return null;
	}
	private static void createNewCollege() {
		College college = new College();
		System.out.println("Enter college name");
		college.setName(scanner.nextLine());
		System.out.println("Enter max student strength of college");
		college.setMaxStrength(scanner.nextInt());
		scanner.nextLine();
		ServiceResponse response = service.createCollege(college);
		System.out.println(response.getSuccessMessage());
	}
}
