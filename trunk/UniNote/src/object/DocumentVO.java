package object;

import java.util.ArrayList;

import sql.CategoryInfo;


public class DocumentVO {
     private String name;
     private String path;
     private String type;
     private String profile;
     private String tag;
     private String postgraduateData;
     private int categoryID;
     private String school;
     private String department;
     private String course;
     
     public DocumentVO(String name,String path,String profile,String tag,String postgraduateData,String school,String dapartment,String course) {
 		this.name=name;
 		this.path=path;
 		String[] buffer=path.split("\\.");
 		if(buffer.length==2){
 			this.type=buffer[1];
 		}
 		else{
 			this.type="";
 		}
 		this.profile=profile;
 		this.tag=tag;
 		this.postgraduateData=postgraduateData;
 		this.categoryID=CategoryInfo.getVoID(school, dapartment, course);
 	 }
     
     public DocumentVO(String name,String path,String type,String profile,String tag,String postgraduateData,int id) {
  		this.name=name;
  		this.path=path;
  		this.type=type;
  		this.profile=profile;
  		this.tag=tag;
  		this.postgraduateData=postgraduateData;
  		CategoryVO temp=CategoryInfo.getVoByID(id);
  		this.school=temp.getSchool();
  		this.department=temp.getDepartment();
  		this.course=temp.getCourse(); 		
  	 }
     
    
     
     public String getName(){
    	 return name;
     }
     
     public String getPath(){
    	 return path;
     }
     
     public String getType(){
    	 return type;
     }
     
     public String getProfile(){
    	 return profile;
     }
     
     public String getTag(){
    	 return tag;
     }
     
     public String getPostgraduateData(){
    	 return postgraduateData;
     }
     
     public String getSchool(){
    	 return school;
     }
     
     public String getDepartment(){
    	 return department;
     }
     
     public String getCourse(){
    	 return course;
     }
     
     public int getCategoryID(){
    	 return categoryID;
     }
     
     
     
     
     public void setName(String name){
    	 this.name=name;
     }
     
     public void setPath(String path){
    	 this.path=path;
     }
     
     public void setType(String type){
    	 this.type=type;
     }
     
     public void setProfile(String profile){
    	 this.profile=profile;
     }
     
     public void setTag(String tag){
    	 this.tag=tag;
     }
     
     public void setPostgraduateData(String postgraduateData){
    	 this.postgraduateData=postgraduateData;
     }
     
     public void setCategoryID(int id){
    	 this.categoryID=id;
     }
     
    
}
