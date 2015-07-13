package object;


public class DocumentVO {
     private String name;
     private String path;
     private String type;
     
     public DocumentVO(String name,String path) {
 		this.name=name;
 		this.path=path;
 		String[] buffer=path.split("\\.");
 		if(buffer.length==2){
 			this.type=buffer[1];
 		}
 		else{
 			this.type="";
 		}
 	 }
     
     public DocumentVO(String name,String path,String type) {
		this.name=name;
		this.path=path;
		this.type=type;
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
     
     public void setName(String name){
    	 this.name=name;
     }
     
     public void setPath(String path){
    	 this.path=path;
     }
     
     public void setType(String type){
    	 this.type=type;
     }
     
}
