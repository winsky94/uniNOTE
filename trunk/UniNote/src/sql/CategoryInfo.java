package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import object.CategoryVO;

public class CategoryInfo {
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	public static void main(String[] args) {

	}

	private boolean add(CategoryVO vo) {
		boolean result = false;
		try {
			connection = SqlManager.getConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			String school = vo.getSchool();
			String department = vo.getDepartment();
			String course = vo.getCourse();
			String query = "select id from user where school='" + school
					+ "' and department='" + department + "' and course='"
					+ course + "'";
			resultSet = statement.executeQuery(query);
			if (!resultSet.next()) {
				result=true;
				query = "select max(userID) as categoryNum from user";
				ResultSet rs = statement.executeQuery(query);
				rs.next();
				int count = resultSet.getInt("categoryNum");
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO user category(?, ?,?,?,?,?)");
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

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeMySql();
		}
		return result;
	}

	private boolean delete(int id) {
		boolean result = false;
		return result;
	}

	private boolean update(int id, CategoryVO newVo) {
		boolean result = false;
		return result;
	}

	private void createTable(){
		try {
			statement.execute("drop table if exists category");
			statement.execute("create table category(userID int not null auto_increment,"
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
	
	public CategoryVO getVoByID(int id){
		
	}
	
	public int getVoID(String school,String department,String course){
		
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
}
