package Models;

public class Student {
	private int id;
	private String name;
	private int Age;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public int getCollegeID() {
		return collegeID;
	}
	public void setCollegeID(int collegeID) {
		this.collegeID = collegeID;
	}
	private Subject subject;
	private int collegeID;
}
