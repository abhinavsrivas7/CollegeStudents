package DAO;

import java.util.List;

import Models.College;

public interface CollegeDao {
	public List<College> getColleges();
	public void setCollege(College college);
}
