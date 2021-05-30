package DAO;

import java.util.ArrayList;
import java.util.List;

import Models.College;

public class StaticDataCollegeDao implements CollegeDao {
	
	private List<College> colleges;
	
	public StaticDataCollegeDao() {
		this.colleges = new ArrayList<College>();
		College college1 = new College();
		college1.setId(1);
		college1.setName("College1");
		college1.setMaxStrength(2);
		
		College college2 = new College();
		college2.setId(2);
		college2.setName("College2");
		college2.setMaxStrength(3);
		
		colleges.add(college1);
		colleges.add(college2);
	}

	@Override
	public List<College> getColleges() {
		return colleges;
	}

	
	@Override
	public void setCollege(College college) {
		college.setId(colleges.size() + 1);
		colleges.add(college);
	}

}
