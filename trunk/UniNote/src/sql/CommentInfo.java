package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CommentInfo {
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	/**
	 * 
	 * 
	 * @param nickname
	 *            ,id
	 * @return 
	 *         返回int[],int[0]是点赞数,int[1]是被踩数,int[3]是否成功:0代表不成功，1代表成功,2代表是该用户上传的文件
	 */
	public int[] addPraise(String nickname, int documentID) {
		int count1 = 0;
		int count2 = 0;
		int isTrue = 1;
		try {
			connection = SqlManager.getConnection();
			statement = connection.createStatement();
			String name = nickname.replace("'", "''");
			String query = "select uploader from document where documentID="
					+ documentID + " limit 1";
			ResultSet rs1 = statement.executeQuery(query);
			rs1.next();
			String n = rs1.getString("uploader");
			if (n.equals(nickname))
				isTrue = 2;
			else {
				query = "select * from comment where nickname='" + name
						+ "' and documentID=" + documentID + " limit 1";
				resultSet = statement.executeQuery(query);
				if (resultSet.next()) {
					isTrue = 0;
				} else {
					query = "select max(commentID) as commentNum from comment";
					ResultSet rs = statement.executeQuery(query);
					rs.next();
					int count = rs.getInt("commentNum");
					rs.close();
					PreparedStatement statement1 = connection
							.prepareStatement("INSERT INTO comment VALUES(?,?,?,?)");
					statement1.setInt(1, ++count);
					statement1.setString(2, nickname);
					statement1.setInt(3, documentID);
					statement1.setString(4, "P");
					statement1.execute();
					statement1.close();
					query = "update document set praise=praise+1 where documentID="
							+ documentID;
					statement.executeUpdate(query);
				}
			}
			rs1.close();

			query = "select praise,criticism from document where documentID="
					+ documentID + " limit 1";
			ResultSet rs = statement.executeQuery(query);
			rs.next();
			count1 = rs.getInt("praise");
			count2 = rs.getInt("criticism");
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeMySql();
		}
		int[] result = new int[] { count1, count2, isTrue };
		return result;
	}

	/**
	 * 
	 * 
	 * @param nickname
	 *            ,id
	 * @return 
	 *         返回int[],int[0]是点赞数,int[1]是被踩数,int[3]是否成功:0代表不成功，1代表成功,2代表是该用户上传的文件
	 */
	public int[] addCriticism(String nickname, int documentID) {
		int count1 = 0;
		int count2 = 0;
		int isTrue = 1;
		try {
			connection = SqlManager.getConnection();
			statement = connection.createStatement();
			String name = nickname.replace("'", "''");
			String query = "select uploader from document where documentID="
					+ documentID + " limit 1";
			ResultSet rs1 = statement.executeQuery(query);
			rs1.next();
			String n = rs1.getString("uploader");
			if (n.equals(nickname))
				isTrue = 2;
			else {
				query = "select * from comment where nickname='" + name
						+ "' and documentID=" + documentID + " limit 1";
				resultSet = statement.executeQuery(query);
				if (resultSet.next()) {
					isTrue = 0;
				} else {
					query = "select max(commentID) as commentNum from comment";
					ResultSet rs = statement.executeQuery(query);
					rs.next();
					int count = rs.getInt("commentNum");
					rs.close();
					PreparedStatement statement1 = connection
							.prepareStatement("INSERT INTO comment VALUES(?,?,?,?)");
					statement1.setInt(1, ++count);
					statement1.setString(2, nickname);
					statement1.setInt(3, documentID);
					statement1.setString(4, "C");
					statement1.execute();
					statement1.close();
					query = "update document set criticism=criticism+1 where documentID="
							+ documentID;
					statement.executeUpdate(query);
				}
			}
			rs1.close();

			query = "select praise,criticism from document where documentID="
					+ documentID + " limit 1";
			ResultSet rs = statement.executeQuery(query);
			rs.next();
			count1 = rs.getInt("praise");
			count2 = rs.getInt("criticism");
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeMySql();
		}
		int[] result = new int[] { count1, count2, isTrue };
		return result;
	}

	@SuppressWarnings("unused")
	private void createTable() {
		try {
			connection = SqlManager.getConnection();
			statement = connection.createStatement();
			statement.execute("drop table if exists comment");
			statement
					.execute("create table comment(commentID int not null auto_increment,"
							+ "nickname varchar(40) not null default 'null',"
							+ "documentID int not null default 0,"
							+ "comment varchar(10) not null default 'null',"
							+ "primary key(commentID));");
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
		CommentInfo ci = new CommentInfo();
		// ci.createTable();
		int[] a = ci.addPraise("1", 1);
		System.out.println(a[0] + " " + a[1] + " " + a[2]);
		// int[] b=ci.addCriticism("1", 1);
		// System.out.println(b[0]+" "+b[1]+" "+b[2]);

	}

}
