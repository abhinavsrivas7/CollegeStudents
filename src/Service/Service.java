package Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import DAO.*;
import Exceptions.CollegeNotFoundException;
import Exceptions.StudentStrengthException;
import Models.College;
import Models.CollegeStudentsViewModel;
import Models.ServiceResponse;
import Models.Student;
import Models.Subject;

public class Service implements CollegeStudentService {
	
	private CollegeDao collegeDao;
	private StudentDao studentDao;
	
	public Service(CollegeDao collegeDao, StudentDao studentDao) {
		this.collegeDao = collegeDao;
		this.studentDao = studentDao;
	}

	@Override
	public ServiceResponse createCollege(College college) {
		collegeDao.setCollege(college);
		ServiceResponse response = new ServiceResponse();
		response.setSuccessMessage("College added successfully");
		return response;
	}

	@Override
	public ServiceResponse createStudent(Student student) {
		ServiceResponse response = new ServiceResponse();
		try {
			List<College> colleges = collegeDao.getColleges();
			List<Student> students = studentDao.getStudents();
			College studentCollege = new College();
			for(College college : colleges)
				if(college.getId() == student.getCollegeID())
					studentCollege = college;
			int currentStrength = 0;
			for (Student s : students)
				if(s.getCollegeID() == studentCollege.getId()) 
					currentStrength++;
			if(currentStrength == studentCollege.getMaxStrength())
				throw new StudentStrengthException(studentCollege.getName());
			else {
				studentDao.setStudent(student);
				response.setError(false);
				response.setSuccessMessage("Student Added Successfully");
			}
		}
		catch(StudentStrengthException e) {
			response.setError(true);
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}

	@Override
	public ServiceResponse getCollegeStudents() {
		List<CollegeStudentsViewModel> collegeStudentsResult = new ArrayList<CollegeStudentsViewModel>();
		List<College> colleges = collegeDao.getColleges();
		List<Student> students = studentDao.getStudents();
		for (College college : colleges) {
			int collegeID = college.getId();
			List<Student> collegeStudents = new ArrayList<Student>();
			for (Student student : students) {
				if(student.getCollegeID() == collegeID)
					collegeStudents.add(student);
			}
			CollegeStudentsViewModel viewModel = new CollegeStudentsViewModel();
			viewModel.setCollege(college);
			viewModel.setStudents(collegeStudents);
			collegeStudentsResult.add(viewModel);
		}
		Collections.sort(collegeStudentsResult, new Comparator<CollegeStudentsViewModel>() {
			@Override
			public int compare(CollegeStudentsViewModel o1, CollegeStudentsViewModel o2) {
				return o1.getCollege().getId() - o2.getCollege().getId();
			}
		});
		Collections.sort(collegeStudentsResult, new Comparator<CollegeStudentsViewModel>() {
			@Override
			public int compare(CollegeStudentsViewModel o1, CollegeStudentsViewModel o2) {
				return o1.getCollege().getName().compareTo(o2.getCollege().getName());
			}
		});
		ServiceResponse response = new ServiceResponse();
		response.setCollegeStudents(collegeStudentsResult);
		return response;
	}

	@Override
	public ServiceResponse getStudents(String collegeName, Subject subject) {
		ServiceResponse response = new ServiceResponse();
		try {
			List<College> colleges = collegeDao.getColleges();
			List<Student> students = studentDao.getStudents();
			List<College> targetColleges = new ArrayList<College>();
			for (College college : colleges) {
				if(college.getName().equalsIgnoreCase(collegeName))
					targetColleges.add(college);
			}
			if(targetColleges.size() == 0) throw new CollegeNotFoundException(collegeName);
			else {
				List<Student> collegeStudents = new ArrayList<Student>();
				for (College college : targetColleges) {
					for (Student student : students) {
						if(student.getCollegeID() == college.getId())
							collegeStudents.add(student);
					}
				}
				List<Student> subjectStudents = new ArrayList<Student>();
				for (Student student : collegeStudents) {
					if(student.getSubject().equals(subject))
						subjectStudents.add(student);
				}
				Collections.sort(subjectStudents, new Comparator<Student>() {
					@Override
					public int compare(Student o1, Student o2) {
						return o2.getAge() - o1.getAge();
					}
				});
				Collections.sort(subjectStudents, new Comparator<Student>() {
					@Override
					public int compare(Student o1, Student o2) {
						return o2.getName().compareTo(o1.getName());
					}
				});
				response.setStudents(subjectStudents);
			}
		}
		catch(CollegeNotFoundException e) {
			response.setError(true);
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}

}
