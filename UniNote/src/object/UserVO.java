package object;

public class UserVO {
     private String nickname;
     private String password;
     private String email;
     private String school;
     private String phoneNumber;
     private int point;
     
     public UserVO(String nickname,String password,String email,String school,String phoneNumber,int point){
    	 this.nickname=nickname;
    	 this.password=password;
    	 this.email=email;
    	 this.school=school;
    	 this.phoneNumber=phoneNumber;
    	 this.point=point;
     }
     

	public String getNickname(){
    	 return nickname;
     }
     
     public String getPassword(){
    	 return password;
     }
     
     public String getEmail(){
    	 return email;
     }
     
     public String getSchool(){
    	 return school;
     }
     
     public String getPhoneNumber(){
    	 return phoneNumber;
     }
     
     public int getPoint() {
 		return point;
 	}

 	
     
     
     
     
     public void setNickname(String nickname){
    	this.nickname=nickname;
     }
     
     public void setPassword(String password){
    	 this.password=password;
     }
     
     public void setEmail(String email){
    	 this.email=email;
     }
     
     public void setSchool(String school){
    	 this.school=school;
     }
     
     public void setPhoneNumber(String phoneNumber){
    	 this.phoneNumber=phoneNumber;
     }
     
     public void setPoint(int point) {
  		this.point = point;
  	}
}
