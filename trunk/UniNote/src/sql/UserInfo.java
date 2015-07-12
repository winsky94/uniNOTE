package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.geometry.VPos;

import javax.management.Query;

import object.UserVO;

public class UserInfo {

	public boolean add(UserVO vo) {
		int count = 0;
		boolean istrue = false;
		try {
			Connection con = SqlManager.getConnection();
			con.setAutoCommit(false);
			Statement sql = con.createStatement();
			String name = vo.getNickname();
			name.replace("'", "''");
			String query = "select userID from user where nickname='" + name
					+ "'";
			ResultSet resultSet1 = sql.executeQuery(query);
			if (!resultSet1.next()) {
				istrue = true;
				resultSet1.close();
				query = "select count(*) as usernum from user";
				ResultSet resultSet = sql.executeQuery(query);
				resultSet.next();
				count = resultSet.getInt("usernum");
				PreparedStatement statement = con
						.prepareStatement("INSERT INTO user VALUES(?, ?,?,?,?,?)");
				statement.setInt(1, ++count);
				statement.setString(2, vo.getNickname());
				statement.setString(3, vo.getPassword());
				statement.setString(4, vo.getEmail());
				statement.setString(5, vo.getSchool());
				statement.setString(6, vo.getPhoneNumber());
				statement.addBatch();
				// System.out.println(count);

				statement.executeBatch();
				con.commit();
				statement.close();
				resultSet.close();
			}
			sql.close();
			con.close();
		} catch (java.lang.ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("ClassNotFoundException:" + e.getMessage());
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.err.println("SQLException:" + ex.getMessage());
		}
		return istrue;
	}

	public boolean delete(UserVO vo) {
		boolean isTrue=true;
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String name=vo.getNickname();
			name.replace("'", "''");
			String query = "select userID from user where nickname='"+name+"' limit 1";
			ResultSet resultSet1 = sql.executeQuery(query);
			if(!resultSet1.next()){
				isTrue=false;
			}
			else{
				query = "delete from user where nickname='"+name+"'";
				sql.executeUpdate(query);
			}
			resultSet1.close();
			sql.close();
			con.close();
		    }catch (java.lang.ClassNotFoundException e) {
				e.printStackTrace();
				System.err.println("ClassNotFoundException:" + e.getMessage());
			} catch (SQLException ex) {
				ex.printStackTrace();
				System.err.println("SQLException:" + ex.getMessage());
			}
		return isTrue;
	}

	public boolean modify(UserVO vo,String oldName) {
		boolean isTrue=true;
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String name1=oldName;
			name1.replace("'", "''");
			String query = "select userID from user where nickname='"+name1+"' limit 1";
			ResultSet resultSet1 = sql.executeQuery(query);
			int hisID=0;
			if(!resultSet1.next()){
				isTrue=false;
			}
			else{
				hisID=resultSet1.getInt("userID");
				String name2=vo.getNickname();
				name2.replace("'", "''");
				query = "select userID from user where nickname='"+name2+"' limit 1";
				ResultSet resultSet2 = sql.executeQuery(query);
				if(resultSet2.next()){
					int hisnextID=resultSet2.getInt("userID");
				    if(hisnextID!=hisID)
					    isTrue=false;
				    else{
				    	
				    }
				}
				else{
					query = "update user where nickname='"+name1+"' set nickname='"+name2+"'"+" password";
					sql.executeUpdate(query);
				}
				resultSet2.close();
			}
			resultSet1.close();
			sql.close();
			con.close();
		    }catch (java.lang.ClassNotFoundException e) {
				e.printStackTrace();
				System.err.println("ClassNotFoundException:" + e.getMessage());
			} catch (SQLException ex) {
				ex.printStackTrace();
				System.err.println("SQLException:" + ex.getMessage());
			}
		return isTrue;
	}

	public UserVO search(String s) {
		return null;
	}

	public boolean login(String nickName, String password) {
		boolean isTrue = false;
		try {
			Connection con = SqlManager.getConnection();
			con.setAutoCommit(false);
			Statement sql = con.createStatement();
			String name = nickName;
			name.replace("'", "''");
			String query = "select password from user where nickname='" + name
					+ "' limit 1";
			ResultSet resultSet1 = sql.executeQuery(query);
			if (resultSet1.next()
					&& resultSet1.getString("password").equals(password)) {
				isTrue = true;
			}
			resultSet1.close();
			sql.close();
			con.close();
		} catch (java.lang.ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("ClassNotFoundException:" + e.getMessage());
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.err.println("SQLException:" + ex.getMessage());
		}
		return isTrue;
	}

	public void createTable() {
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			sql.execute("drop table if exists user");
			sql.execute("create table user(userID int not null auto_increment,"
					+ "nickname varchar(40) not null default 'null',"
					+ "password varchar(40) not null default 'null',"
					+ "email varchar(40) not null default 'null',"
					+ "school varchar(40) not null default 'null',"
					+ "phoneNumber varchar(40) not null default 'null',"
					+ "primary key(userID));");
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
//		ui.createTable();
		UserVO vo1=new UserVO("1", "1", "1@qq.com", "nju", "13588888888");
		UserVO vo2=new UserVO("3", "3", "1@qq.com", "nju", "13588888888");
		System.out.println(ui.add(vo2));
//		System.out.println(ui.login("1", "1"));
//		System.out.println(ui.delete(vo2));
	}
}
