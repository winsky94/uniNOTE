package sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


import object.UserVO;

public class UserInfo {
	
	public boolean add(UserVO vo) {
		return true;
	}

	public boolean delete(UserVO vo) {
		return true;
	}

	public boolean modify(UserVO vo) {
		return true;
	}

	public UserVO search(String s) {
		return null;
	}
	
	public boolean login(String nickName,String password){
		return false;
	}
	
	public void createTable(){
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			sql.execute("drop table if exists user");
			sql.execute("create table user(userID int not null auto_increment,nickname varchar(40) not null default 'null',"
					+ "password varchar(40) not null default 'null',email varchar(40) not null default 'null',"
					+ "phoneNumber varchar(40) not null default 'null',primary key(userID));");
			sql.close();
			con.close();
		} catch (java.lang.ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("ClassNotFoundException:" + e.getMessage());
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.err.println("SQLException:" + ex.getMessage());
		}
	}
	
	public static void main(String[] args) {
		UserInfo ui=new UserInfo();
		ui.createTable();
	} 
}
