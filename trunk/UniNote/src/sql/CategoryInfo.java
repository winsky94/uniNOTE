package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import object.CategoryVO;

public class CategoryInfo {
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	public static void main(String[] args) {
		CategoryInfo ci = new CategoryInfo();
//		ci.createTable();

		CategoryVO vo1 = new CategoryVO("南京大学", "软件学院", "计算机与操作系统");
		CategoryVO vo2 = new CategoryVO("南京大学", "软件学院", "数据库系统");
		CategoryVO vo3 = new CategoryVO("南京大学", "软件学院", "软件工程与计算Ⅰ");
		CategoryVO vo4 = new CategoryVO("南京大学", "软件学院", "软件工程与计算Ⅱ");
		CategoryVO vo5 = new CategoryVO("南京大学", "软件学院", "软件工程与计算Ⅲ");
		CategoryVO vo6 = new CategoryVO("南京大学", "软件学院", "数据结构与算法");
		CategoryVO vo7 = new CategoryVO("南京大学", "软件学院", "计算机网络");
		CategoryVO vo8 = new CategoryVO("南京大学", "软件学院", "离散数学");
		CategoryVO vo9 = new CategoryVO("南京大学", "软件学院", "计算机组成原理");
		CategoryVO vo10 = new CategoryVO("南京大学", "软件学院", "计算系统基础");
		ArrayList<CategoryVO> categorys = new ArrayList<CategoryVO>();
		categorys.add(vo1);
		categorys.add(vo2);
		categorys.add(vo3);
		categorys.add(vo4);
		categorys.add(vo5);
		categorys.add(vo6);
		categorys.add(vo7);
		categorys.add(vo8);
		categorys.add(vo9);
		categorys.add(vo10);

		for (int i = 0; i < categorys.size(); i++) {
			CategoryVO vo = categorys.get(i);
			System.out.println(i + " " + ci.add(vo));
		}
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
			String query = "select cid from category where school='" + school
					+ "' and department='" + department + "' and course='"
					+ course + "'";
			resultSet = statement.executeQuery(query);
			if (!resultSet.next()) {
				result = true;
				query = "select max(cid) as categoryNum from category";
				ResultSet rs = statement.executeQuery(query);
				rs.next();
				int count = rs.getInt("categoryNum");
				rs.close();

				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO category VALUES(?,?,?,?)");
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

	@SuppressWarnings("unused")
	private boolean delete(int id) {
		boolean result = false;
		try {
			connection = SqlManager.getConnection();
			statement = connection.createStatement();
			String query = "delete from category where cid=" + id;
			int res = statement.executeUpdate(query);
			if (res != 0) {
				result = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeMySql();
		}
		return result;
	}

	@SuppressWarnings("unused")
	private boolean update(int id, CategoryVO newVo) {
		boolean result = false;
		String school = newVo.getSchool();
		String department = newVo.getDepartment();
		String course = newVo.getCourse();
		try {
			connection = SqlManager.getConnection();
			statement = connection.createStatement();
			String query = "update category set school='" + school
					+ "' , department='" + department + "' , course='" + course
					+ "' where cid=" + id;
			int res = statement.executeUpdate(query);
			if (res != 0) {
				result = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeMySql();
		}
		return result;
	}

	@SuppressWarnings("unused")
	private void createTable() {
		try {
			connection = SqlManager.getConnection();
			statement = connection.createStatement();
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
	 * 
	 * @param id
	 *            分类编号
	 * @return 分类对象，该编号的对象不存在就返回null
	 */
	public static CategoryVO getVoByID(int id) {
		CategoryVO vo = null;
		try {
			Connection con = SqlManager.getConnection();
			Statement sta = con.createStatement();
			String query = "select school,department,course from category where cid="
					+ id + " limit 1";
			ResultSet rs = sta.executeQuery(query);
			while (rs.next()) {
				String school = rs.getString("school");
				String department = rs.getString("department");
				String course = rs.getString("course");
				vo = new CategoryVO(school, department, course);
				vo.setID(id);
			}
			rs.close();
			sta.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return vo;
	}

	/**
	 * 根据分类的具体信息得到该对象的编号
	 * 
	 * @param school
	 *            学校
	 * @param department
	 *            院系
	 * @param course
	 *            课程
	 * @return 该分类的编号，不存在返回-1
	 */
	public static int getVoID(String school, String department, String course) {
		int id = -1;
		try {
			Connection con = SqlManager.getConnection();
			Statement sta = con.createStatement();
			String query = "select cid from category where school='" + school
					+ "' and department='" + department + "' and course='"
					+ course + " limit 1";
			ResultSet rs = sta.executeQuery(query);
			while (rs.next()) {
				id = rs.getInt("cid");
			}
			rs.close();
			sta.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return id;
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
