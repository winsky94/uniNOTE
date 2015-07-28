package object;

import sql.CategoryInfo;

public class DocumentVO {
	private int ID;
	private String name;
	private String customName;
	private String path;
	private String type;
	private String profile;// 简介
	private String tag;// 标签
	private String postgraduateData;
	private int categoryID;
	private String school;
	private String department;
	private String course;
	private String uploader;
	private int praise;
	private int criticism;
	private int downloadNum;

	public DocumentVO(String name, String customName, String path,
			String profile, String tag, String postgraduateData, String school,
			String department, String course, String uploader) {
		this.name = name;
		this.customName = customName;
		this.path = path;
		int index=path.lastIndexOf(".");		
		if(index!=-1)
		     this.type = path.substring(index+1,path.length());
		else 
			 this.type="";
		this.profile = profile;
		this.tag = tag;
		this.postgraduateData = postgraduateData;
		this.categoryID = CategoryInfo.getVoID(school, department, course);
		this.uploader = uploader;
	}

	public DocumentVO(int i, String name, String customName, String path,
			String type, String profile, String tag, String postgraduateData,
			int id, String uploader, int praise, int criticism, int downloadNum) {
		this.ID = i;
		this.name = name;
		this.customName = customName;
		this.path = path;
		this.type = type;
		this.profile = profile;
		this.tag = tag;
		this.postgraduateData = postgraduateData;
		CategoryVO temp = CategoryInfo.getVoByID(id);
		this.school = temp.getSchool();
		this.department = temp.getDepartment();
		this.course = temp.getCourse();
		this.uploader = uploader;
		this.praise = praise;
		this.criticism = criticism;
		this.downloadNum = downloadNum;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public String getType() {
		return type;
	}

	public String getProfile() {
		return profile;
	}

	public String getTag() {
		return tag;
	}

	public String getPostgraduateData() {
		return postgraduateData;
	}

	public String getSchool() {
		return school;
	}

	public String getDepartment() {
		return department;
	}

	public String getCourse() {
		return course;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public String getCustomName() {
		return customName;
	}

	public int getID() {
		return ID;
	}

	public String getUploader() {
		return uploader;
	}

	public int getPraise() {
		return praise;
	}

	public int getCriticism() {
		return criticism;
	}

	public int getDownloadNum() {
		return downloadNum;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setPostgraduateData(String postgraduateData) {
		this.postgraduateData = postgraduateData;
	}

	public void setCategoryID(int id) {
		this.categoryID = id;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public void setPraise(int praise) {
		this.praise = praise;
	}

	public void setCriticism(int criticism) {
		this.criticism = criticism;
	}

	public void setDownloadNum(int downloadNum) {
		this.downloadNum = downloadNum;
	}

}
