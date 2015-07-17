package sql;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import object.DocumentVO;

public class DocumentInfo {

	public boolean add(DocumentVO vo) {
		int count = 0;
		boolean istrue = false;
		try {
			Connection con = SqlManager.getConnection();
			con.setAutoCommit(false);
			Statement sql = con.createStatement();
			String name = vo.getName();
			name.replace("'", "''");
			String query = "select documentID from document where name='"
					+ name + "'";
			ResultSet resultSet1 = sql.executeQuery(query);
			if (!resultSet1.next()) {
				istrue = true;
				resultSet1.close();
				query = "select max(documentID) as documentnum from document";
				ResultSet resultSet = sql.executeQuery(query);
				resultSet.next();
				count = resultSet.getInt("documentnum");
				PreparedStatement statement = con
						.prepareStatement("INSERT INTO document VALUES(?, ?,?,?,?,?,?,?,?)");
				statement.setInt(1, ++count);
				statement.setString(2, vo.getName());
				statement.setString(3, vo.getCustomName());
				statement.setString(4, vo.getPath());
				statement.setString(5, vo.getType());
				statement.setString(6, vo.getProfile());
				statement.setString(7, vo.getTag());
				statement.setString(8, vo.getPostgraduateData());
				statement.setInt(9, vo.getCategoryID());
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

	public boolean delete(DocumentVO vo) {
		return true;
	}

	public boolean modify(DocumentVO vo) {
		return true;
	}

	public File search(String s) {
		String path = null;
		File file = null;
		try {
			Connection con = SqlManager.getConnection();
			con.setAutoCommit(false);
			Statement sql = con.createStatement();
			String name = s;
			name.replace("'", "''");
			String query = "select path from document where name='" + name
					+ "' limit 1";
			ResultSet resultSet1 = sql.executeQuery(query);
			if (resultSet1.next()) {
				path = resultSet1.getString("path");
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
		if (path != null) {
			file = new File(path);
			if (!file.exists())
				file = null;
		}
		return file;
	}

	/**
	 * 得到数据库中全部的文件对象
	 * 
	 * @return
	 */
	public ArrayList<DocumentVO> getDocuments() {
		ArrayList<DocumentVO> documents = new ArrayList<DocumentVO>();
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String query = "select * from document";
			ResultSet resultSet = sql.executeQuery(query);
			while (resultSet.next()) {
				int idi = resultSet.getInt("documentID");
				String name = resultSet.getString("name");
				String customName = resultSet.getString("customname");
				String path = resultSet.getString("path");
				String type = resultSet.getString("type");
				String profile = resultSet.getString("profile");
				String tag = resultSet.getString("tag");
				String postgraduateData = resultSet
						.getString("postgraduateData");
				int id = resultSet.getInt("categoryID");
				DocumentVO vo = new DocumentVO(idi, name, customName, path,
						type, profile, tag, postgraduateData, id);
				documents.add(vo);
			}
			resultSet.close();
			sql.close();
			con.close();

		} catch (java.lang.ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return documents;
	}

	/**
	 * 找到编号为id的文件
	 * 
	 * @param id
	 *            文件编号
	 * @return 该id的文件对象
	 */
	public DocumentVO getDocumentByID(int id) {
		DocumentVO document = null;
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String query = "select * from document where documentID=" + id
					+ " limit 1";
			ResultSet resultSet = sql.executeQuery(query);
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				String customName = resultSet.getString("customname");
				String path = resultSet.getString("path");
				String type = resultSet.getString("type");
				String profile = resultSet.getString("profile");
				String tag = resultSet.getString("tag");
				String postgraduateData = resultSet
						.getString("postgraduateData");
				int categoryID = resultSet.getInt("categoryID");
				document = new DocumentVO(id, name, customName, path, type,
						profile, tag, postgraduateData, categoryID);
			}
			resultSet.close();
			sql.close();
			con.close();

		} catch (java.lang.ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return document;
	}

	public void createTable() {
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			sql.execute("drop table if exists document");
			sql.execute("create table document(documentID int not null auto_increment,"
					+ "name varchar(100) not null default 'null',"
					+ "customName varchar(100) not null default 'null',"
					+ "path varchar(100) not null default 'null',"
					+ "type varchar(20) not null default 'null',"
					+ "profile varchar(600) not null default 'null',"
					+ "tag varchar(100) not null default 'null',"
					+ "postgraduateData varchar(10) not null default 'null',"
					+ "categoryID int not null default 0,"
					+ "primary key(documentID));");
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
		DocumentInfo ui = new DocumentInfo();
		ui.createTable();
		DocumentVO vo1 = new DocumentVO("hehe", "哼！", "C:/1.c", "这是一个c代码文件",
				"c", "N", "南京大学", "软件学院", "计算机与操作系统");
		DocumentVO vo2 = new DocumentVO("时机+市场规模.docx", "23333",
				"D:\\web_server_file\\时机+市场规模.docx", "呵呵哒", "营销,商业计划书", "Y",
				"南京大学", "软件学院", "数据结构与算法");

		System.out.println(ui.add(vo1));
		System.out.println(ui.add(vo2));
		System.out.println(ui.search("hehe"));
		System.out.println(ui.getDocuments().size());
	}
}
