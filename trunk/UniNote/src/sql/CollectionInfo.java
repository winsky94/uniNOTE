package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class CollectionInfo {
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	private void changeState(String nickname,int documentID) {
		try {
			connection = SqlManager.getConnection();
			statement = connection.createStatement();
			String name=nickname.replace("'", "''");
			String query = "select * from collection where nickname='" +name+"' and documentID="+ documentID +" limit 1";
			resultSet= statement.executeQuery(query);
			if (!resultSet.next()) {				
				query = "select max(collectionID) as collectionNum from collection";
				ResultSet rs = statement.executeQuery(query);
				rs.next();
				int count = rs.getInt("collectionNum");
				rs.close();
				PreparedStatement statement1 = connection
						.prepareStatement("INSERT INTO collection VALUES(?,?,?)");
				statement1.setInt(1, ++count);
				statement1.setString(2, nickname);
				statement1.setInt(3, documentID);
				statement1.execute();
				statement1.close();
			}
			else{
				query = "delete from collection where nickname='" + name+"' and documentID="+documentID+" limit 1";
				statement.executeUpdate(query);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeMySql();
		}
	}

	public Map<Integer,Byte> followYourHeart(String nickname){
		Map<Integer,Byte> map = new HashMap<Integer,Byte>();
		try {
			connection = SqlManager.getConnection();
			statement = connection.createStatement();
			String name=nickname.replace("'", "''");
			String query = "select documentID from collection where nickname='"
					+ name + "'";
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int ID = resultSet.getInt("documentID");
				map.put(ID, Byte.parseByte("1"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeMySql();
		}
		return map;
	}
	
	@SuppressWarnings("unused")
	private void createTable() {
		try {
			connection = SqlManager.getConnection();
			statement = connection.createStatement();
			statement.execute("drop table if exists collection");
			statement
					.execute("create table collection(collectionID int not null auto_increment,"
							+ "nickname varchar(40) not null default 'null',"
							+ "documentID int not null default 0,"
							+ "primary key(collectionID));");
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeMySql();
		}
	}

	private void closeMySql() {
		try {
			if (resultSet != null) {
				resultSet.close();
				resultSet = null;
			}
			if (statement != null) {
				statement.close();
				statement = null;
			}
			if (connection != null) {
				connection.close();
				connection = null;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		CollectionInfo ci=new CollectionInfo();
//		ci.createTable();
		ci.changeState("1", 1);
		ci.changeState("2", 1);
		ci.followYourHeart("1");
	}
}
