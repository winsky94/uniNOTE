package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
				query = "select max(userID) as usernum from user";
				ResultSet resultSet = sql.executeQuery(query);
				resultSet.next();
				count = resultSet.getInt("usernum");
				PreparedStatement statement = con
						.prepareStatement("INSERT INTO user VALUES(?, ?,?,?,?,?,?)");
				statement.setInt(1, ++count);
				statement.setString(2, vo.getNickname());
				statement.setString(3, vo.getPassword());
				statement.setString(4, vo.getEmail());
				statement.setString(5, vo.getSchool());
				statement.setString(6, vo.getPhoneNumber());
				statement.setInt(7, vo.getPoint());
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
		boolean isTrue = true;
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String name = vo.getNickname();
			name.replace("'", "''");

			String query = "delete from user where nickname='" + name + "'";
			int re = sql.executeUpdate(query);
			if (re == 0)
				isTrue = false;

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

	public boolean modify(UserVO vo, String oldName) {
		boolean isTrue = true;
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String name1 = oldName;
			name1.replace("'", "''");
			String query = "select userID from user where nickname='" + name1
					+ "' limit 1";
			ResultSet resultSet1 = sql.executeQuery(query);
			int hisID = 0;
			if (!resultSet1.next()) {
				isTrue = false;
			} else {
				hisID = resultSet1.getInt("userID");
				String name2 = vo.getNickname();
				String password = vo.getPassword();
				String email = vo.getEmail();
				String school = vo.getSchool();
				String phoneNumber = vo.getPhoneNumber();
				name2.replace("'", "''");
				query = "select userID from user where nickname='" + name2
						+ "' limit 1";
				ResultSet resultSet2 = sql.executeQuery(query);

				if (resultSet2.next()) {
					int hisnextID = resultSet2.getInt("userID");
					if (hisnextID != hisID)
						isTrue = false;
					else {
						query = "update user set password='" + password
								+ "' , email='" + email + "' , school='"
								+ school + "' , phoneNumber='" + phoneNumber
								+ "' where nickname='" + name1 + "'";
						sql.executeUpdate(query);

					}
				} else {
					query = "update user set nickname='" + name2 + "'"
							+ ", password='" + password + "' , email='" + email
							+ "' , school='" + school + "' , phoneNumber='"
							+ phoneNumber + "' where nickname='" + name1 + "'";
					sql.executeUpdate(query);
				}
				resultSet2.close();
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

	public UserVO getVoByName(String name) {
		UserVO vo = null;
		try {
			Connection connection = SqlManager.getConnection();
			Statement statement = connection.createStatement();
			String query = "select * from user where nickname='" + name
					+ "' limit 1";
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				String nickname = rs.getString("nickname");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String school = rs.getString("school");
				String phoneNumber = rs.getString("phonenumber");
				int point = rs.getInt("point");
				vo = new UserVO(nickname, password, email, school, phoneNumber,
						point);

				Statement statement1 = connection.createStatement();
				query = "select count(documentID) as upLoadNum from document where uploader='"
						+ name + "'";
				ResultSet resultSet1 = statement1.executeQuery(query);
				while (resultSet1.next()) {
					int upLoadNum = resultSet1.getInt("upLoadNum");
					vo.setUpLoadNum(upLoadNum);
				}
				resultSet1.close();
				statement1.close();

				Statement statement2 = connection.createStatement();
				query = "select count(downloadID) as downLoadNum from download  where nickname='"
						+ name + "'";
				ResultSet resultSet2 = statement2.executeQuery(query);
				while (resultSet2.next()) {
					int downLoadNum = resultSet2.getInt("downLoadNum");
					vo.setDownLoadNum(downLoadNum);
				}
				resultSet2.close();
				statement2.close();

			}
			rs.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return vo;
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

	public void addPoint(String nickname, int point) {
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String name = nickname.replace("'", "''");
			String query = "update user set point=point+" + point
					+ " where nickname='" + name + "'";
			sql.executeUpdate(query);
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

	public boolean minusPoint(String nickname, int point) {
		boolean result = false;
		try {
			Connection con = SqlManager.getConnection();
			String name = nickname.replace("'", "''");
			String query = "select point from user where nickname='" + name
					+ "' limit 1";
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int originalPoint = resultSet.getInt("point");
				if (originalPoint >=point) {
					result = true;
				}
			}
			resultSet.close();
			statement.close();
			System.out.println("result="+result);
			if (result) {
				Statement sql = con.createStatement();
				query = "update user set point=point-" + point
						+ " where nickname='" + name + "'";
				sql.executeUpdate(query);
				sql.close();
			}
			con.close();
		} catch (java.lang.ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("ClassNotFoundException:" + e.getMessage());
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.err.println("SQLException:" + ex.getMessage());
		}
		return result;
	}
	
	public boolean canMinusPoint(String nickname, int point) {
		boolean result = false;
		try {
			Connection con = SqlManager.getConnection();
			String name = nickname.replace("'", "''");
			String query = "select point from user where nickname='" + name
					+ "' limit 1";
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int originalPoint = resultSet.getInt("point");
				if (originalPoint >=point) {
					result = true;
				}
			}
			resultSet.close();
			statement.close();
			con.close();
		} catch (java.lang.ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("ClassNotFoundException:" + e.getMessage());
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.err.println("SQLException:" + ex.getMessage());
		}
		return result;
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
					+ "point int not null default 0," + "primary key(userID));");
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

	public String getPassword(String name) {
		String password = null;
		try {
			Connection connection = SqlManager.getConnection();
			Statement statement = connection.createStatement();
			String query = "select password from user where nickname='" + name
					+ "' limit 1";
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				password = rs.getString("password");
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return password;
	}

	public static void main(String[] args) {
		UserInfo ui = new UserInfo();
		// ui.createTable();
		// UserVO vo1 = new UserVO("1", "1", "1@qq.com", "南京大学", "13588888888",
		// 10);
		// UserVO vo2 = new UserVO("3", "3", "1@qq.com", "南京大学", "13588888888",
		// 20);
		// System.out.println(ui.add(vo1));
		// System.out.println(ui.add(vo2));
		// System.out.println(ui.login("1", "1"));
		// System.out.println(ui.delete(vo2));
		UserVO vo = ui.getVoByName("1");
		System.out.println(vo.getDownLoadNum());
		System.out.println(vo.getUpLoadNum());

	}
}
