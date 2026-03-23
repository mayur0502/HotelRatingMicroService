package practice;

import java.util.List;

/**
 * 
 */
public class EmployeeSecond {

	String name;
	List<String> skills;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	public EmployeeSecond(String name, List<String> skills) {
		super();
		this.name = name;
		this.skills = skills;
	}
	public EmployeeSecond() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "EmployeeSecond [name=" + name + ", skills=" + skills + "]";
	}

	
}
