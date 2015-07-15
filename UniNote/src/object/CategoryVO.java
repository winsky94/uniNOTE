package object;

public class CategoryVO {
	private int id;
	private String school;// 学校
	private String department;// 院系
	private String course;// 课程

	public CategoryVO(String school, String department, String course) {
		super();
		this.school = school;
		this.department = department;
		this.course = course;
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

}
