package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import object.CategoryVO;

public class CategoryInfo {
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	public static void main(String[] args) {
		CategoryInfo ci=new CategoryInfo();
		ci.createTable();
		CategoryVO vo=new CategoryVO("南京大学", "软件学院", "计算机与操作系统");
		ci.add(vo);
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
			String query = "select cid from user where school='" + school
					+ "' and department='" + department + "' and course='"
					+ course + "'";
			resultSet = statement.executeQuery(query);
			if (!resultSet.next()) {
				result = true;
				query = "select max(cid) as categoryNum from user";
				ResultSet rs = statement.executeQuery(query);
				rs.next();
				int count = rs.getInt("categoryNum");
				rs.close();
				
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO user category(?,?,?,?)");
				statement.setInt(1, ++count);
				statement.setString(2, vo.getSchool());
				statement.setString(3, vo.getDepartment());
				statement.setString(4, vo.getCourse());
				statement.addBatch();
				statement.executeBatch();
				connection.commit();
				statement.close();
			}

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

	private void createTable() {
		try {
			connection=SqlManager.getConnection();
			statement=connection.createStatement();
			statement.execute("drop table if exists category");
			statement
					.execute("create table category(cid int not null auto_increment,"
							+ "school varchar(40) not null default 'null',"
							+ "department varchar(40) not null default 'null',"
							+ "course varchar(40) not null default 'null',"
							+ "primary key(cid));");
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeMySql();
		}
	}

	/**
	 * 根据分类的编号得到该分类对象
	 * @param id 分类编号
	 * @return 分类对象，该编号的对象不存在就返回null
	 */
	public static CategoryVO getVoByID(int id) {
		return null;
	}

	/**
	 * 根据分类的具体信息得到该对象的编号
	 * @param school 学校
	 * @param department 院系 
	 * @param course 课程
	 * @return 该分类的编号，不存在返回-1 
	 */
	public static int getVoID(String school, String department, String course) {
		return -1;
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
