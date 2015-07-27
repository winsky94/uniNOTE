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

	public boolean isFileNameOK(String filename) {
		boolean istrue = true;
		try {
			Connection con = SqlManager.getConnection();
			con.setAutoCommit(false);
			Statement sql = con.createStatement();
			String name = filename;
			name.replace("'", "''");
			String query = "select documentID from document where name='"
					+ name + "'";
			ResultSet resultSet1 = sql.executeQuery(query);
			if (resultSet1.next()) {
				istrue = false;
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
		return istrue;
	}

	public int add(DocumentVO vo) {
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
				int categoryID = vo.getCategoryID();
				if (categoryID != -1) {
					istrue = true;
					resultSet1.close();
					query = "select max(documentID) as documentnum from document";
					ResultSet resultSet = sql.executeQuery(query);
					resultSet.next();
					count = resultSet.getInt("documentnum");
					PreparedStatement statement = con
							.prepareStatement("INSERT INTO document VALUES(?, ?,?,?,?,?,?,?,?,?,?,?,?)");
					statement.setInt(1, ++count);
					statement.setString(2, vo.getName());
					statement.setString(3, vo.getCustomName());
					statement.setString(4, vo.getPath());
					statement.setString(5, vo.getType());
					statement.setString(6, vo.getProfile());
					statement.setString(7, vo.getTag());
					statement.setString(8, vo.getPostgraduateData());
					statement.setInt(9, categoryID);
					statement.setString(10, vo.getUploader());
					statement.setInt(11, 0);
					statement.setInt(12, 0);
					statement.setInt(13, 0);
					statement.addBatch();
					// System.out.println(count);

					statement.executeBatch();
					con.commit();
					statement.close();
					resultSet.close();
				}
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
		if (istrue == false)
			return -1;
		return count;
	}

	public boolean delete(DocumentVO vo) {
		boolean result = false;
		try {
			Connection connection = SqlManager.getConnection();
			Statement statement = connection.createStatement();
			String query = "delete from document where documentID="
					+ vo.getID();
			int res = statement.executeUpdate(query);
			if (res != 0) {
				result = true;
			}
			statement.close();
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public boolean modify(int id, String profile, String tag) {
		boolean result = false;
		try {
			Connection connection = SqlManager.getConnection();
			Statement statement = connection.createStatement();

			String query = "update document set profile='" + profile
					+ "' , tag='" + tag + "' where documentID=" + id;
			int res = statement.executeUpdate(query);
			if (res != 0) {
				result = true;
			}
			statement.close();
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
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
	 * 检索某校某院系某课程的文档共有多少页，每页10个文档
	 * 
	 * @param school
	 *            学校
	 * @param department
	 *            院系
	 * @param course
	 *            课程
	 * @return
	 */
	public int getPageNum(String school, String department, String course) {
		int result = -1;
		String s = "";
		if (!school.equals("all")) {
			s += "and category.school='" + school + "' ";
		}
		if (!department.equals("all")) {
			s += "and category.department='" + department + "' ";
		}
		if (!course.equals("all")) {
			if (course.equals("考研资料")) {
				s += "and document.postgraduateData='Y'";
			} else {
				s += "and category.course='" + course + "' ";
			}

		}
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String query = "select count(*) as pageNum from document,category where document.categoryID=category.cid "
					+ s;
			ResultSet resultSet = sql.executeQuery(query);
			while (resultSet.next()) {
				result = resultSet.getInt("pageNum");
			}
			resultSet.close();
			sql.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result > 0) {
			int yushu = result % 10;
			result = result / 10;
			if (yushu != 0) {
				result = result + 1;
			}
		}
		return result;
	}

	/**
	 * 检索某校某院系某课程的文档
	 * 
	 * @param school
	 *            学校
	 * @param department
	 *            院系
	 * @param course
	 *            课程
	 * @param page
	 *            当前需要显示第几页
	 * @return 如果全部为all，则返回所有文档
	 */
	public ArrayList<DocumentVO> getDocuments(String school, String department,
			String course, int page) {
		ArrayList<DocumentVO> documents = new ArrayList<DocumentVO>();
		String s = "";
		if (!school.equals("all")) {
			s += "and category.school='" + school + "' ";
		}
		if (!department.equals("all")) {
			s += "and category.department='" + department + "' ";
		}
		if (!course.equals("all")) {
			if (course.equals("考研资料")) {
				s += "and document.postgraduateData='Y'";
			} else {
				s += "and category.course='" + course + "' ";
			}

		}
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String query = "select * from document,category where document.categoryID=category.cid "
					+ s + " limit " + ((page - 1) * 10) + ",10";
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
				String uploader = resultSet.getString("uploader");
				int praise = resultSet.getInt("praise");
				int criticism = resultSet.getInt("criticism");
				int downloadNum = resultSet.getInt("downloadNum");
				DocumentVO vo = new DocumentVO(idi, name, customName, path,
						type, profile, tag, postgraduateData, id, uploader,
						praise, criticism, downloadNum);
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
				String uploader = resultSet.getString("uploader");
				int praise = resultSet.getInt("praise");
				int criticism = resultSet.getInt("criticism");
				int downloadNum = resultSet.getInt("downloadNum");
				document = new DocumentVO(id, name, customName, path, type,
						profile, tag, postgraduateData, categoryID, uploader,
						praise, criticism, downloadNum);
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

	public ArrayList<DocumentVO> getUpLoadList(String nickname) {
		ArrayList<DocumentVO> documents = new ArrayList<DocumentVO>();
		try {
			Connection connection = SqlManager.getConnection();
			Statement statement = connection.createStatement();
			String query = "select * from document where uploader='" + nickname
					+ "'";
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int did = resultSet.getInt("documentID");
				String dname = resultSet.getString("name");
				String customName = resultSet.getString("customName");
				String path = resultSet.getString("path");
				String type = resultSet.getString("type");
				String profile = resultSet.getString("profile");
				String tag = resultSet.getString("tag");
				String postgraduateData = resultSet
						.getString("postgraduateData");
				int categaryID = resultSet.getInt("categoryID");
				String uploader = resultSet.getString("uploader");
				int praise = resultSet.getInt("praise");
				int criticism = resultSet.getInt("criticism");
				int downloadNum = resultSet.getInt("downloadNum");
				DocumentVO vo = new DocumentVO(did, dname, customName, path,
						type, profile, tag, postgraduateData, categaryID,
						uploader, praise, criticism, downloadNum);
				documents.add(vo);
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return documents;
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return 返回int[],int[0]是点赞数,int[1]是被踩数
	 */
	public int[] addPraise(int id) {
		int count1 = 0;
		int count2 = 0;
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String query = "update document set praise=praise+1 where documentID="
					+ id;
			sql.executeUpdate(query);
			query = "select praise,criticism from document where documentID="
					+ id + " limit 1";
			ResultSet rs = sql.executeQuery(query);
			rs.next();
			count1 = rs.getInt("praise");
			count2 = rs.getInt("criticism");
			rs.close();
			sql.close();
			con.close();
		} catch (java.lang.ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("ClassNotFoundException:" + e.getMessage());
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.err.println("SQLException:" + ex.getMessage());
		}
		int[] result = new int[] { count1, count2 };
		return result;
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return 返回int[],int[0]是点赞数,int[1]是被踩数
	 */
	public int[] addCriticism(int id) {
		int count1 = 0;
		int count2 = 0;
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String query = "update document set criticism=criticism+1 where documentID="
					+ id;
			sql.executeUpdate(query);
			query = "select praise,criticism from document where documentID="
					+ id + " limit 1";
			ResultSet rs = sql.executeQuery(query);
			rs.next();
			count1 = rs.getInt("praise");
			count2 = rs.getInt("criticism");
			rs.close();
			sql.close();
			con.close();
		} catch (java.lang.ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("ClassNotFoundException:" + e.getMessage());
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.err.println("SQLException:" + ex.getMessage());
		}
		int[] result = new int[] { count1, count2 };
		return result;
	}

	public void addDownloadNum(int id) {
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String query = "update document set downloadNum=downloadNum+1 where documentID="
					+ id;
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

	public ArrayList<DocumentVO> getDocuments(String keyword, String type) {
		ArrayList<DocumentVO> documents = new ArrayList<DocumentVO>();

		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String query = "select * from document where document." + type
					+ " like '%" + keyword + "%'";
			ResultSet resultSet = sql.executeQuery(query);
			while (resultSet.next()) {
				int idi = resultSet.getInt("documentID");
				String name = resultSet.getString("name");
				String customName = resultSet.getString("customname");
				String path = resultSet.getString("path");
				String type1 = resultSet.getString("type");
				String profile = resultSet.getString("profile");
				String tag = resultSet.getString("tag");
				String postgraduateData = resultSet
						.getString("postgraduateData");
				int id = resultSet.getInt("categoryID");
				String uploader = resultSet.getString("uploader");
				int praise = resultSet.getInt("praise");
				int criticism = resultSet.getInt("criticism");
				int downloadNum = resultSet.getInt("downloadNum");
				DocumentVO vo = new DocumentVO(idi, name, customName, path,
						type1, profile, tag, postgraduateData, id, uploader,
						praise, criticism, downloadNum);
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

	public ArrayList<DocumentVO> getHotDocuments(String school) {
		ArrayList<DocumentVO> documents = new ArrayList<DocumentVO>();

		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String query = "select * from document,category where document.categoryID=category.cid and category.school='"
					+ school + "' order by downloadNum desc limit 5";
			ResultSet resultSet = sql.executeQuery(query);
			while (resultSet.next()) {
				int idi = resultSet.getInt("documentID");
				String name = resultSet.getString("name");
				String customName = resultSet.getString("customname");
				String path = resultSet.getString("path");
				String type1 = resultSet.getString("type");
				String profile = resultSet.getString("profile");
				String tag = resultSet.getString("tag");
				String postgraduateData = resultSet
						.getString("postgraduateData");
				int id = resultSet.getInt("categoryID");
				String uploader = resultSet.getString("uploader");
				int praise = resultSet.getInt("praise");
				int criticism = resultSet.getInt("criticism");
				int downloadNum = resultSet.getInt("downloadNum");
				DocumentVO vo = new DocumentVO(idi, name, customName, path,
						type1, profile, tag, postgraduateData, id, uploader,
						praise, criticism, downloadNum);
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
					+ "uploader varchar(100) not null default 'null',"
					+ "praise int not null default 0,"
					+ "criticism int not null default 0,"
					+ "downloadNum int not null default 0,"
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
		// ui.createTable();
		// DocumentVO vo1 = new DocumentVO("hehe", "哼！", "C:/1.c", "这是一个c代码文件",
		// "c", "N", "南京大学", "软件学院", "计算机与操作系统", "王宁");
		// DocumentVO vo2 = new DocumentVO("时机+市场规模.docx", "23333",
		// "D:\\web_server_file\\时机+市场规模.docx", "呵呵哒", "营销,商业计划书", "Y",
		// "南京大学", "软件学院", "数据结构与算法", "严顺宽");
		//
		// System.out.println(ui.add(vo1));
		// System.out.println(ui.add(vo2));
		// System.out.println(ui.search("hehe"));
		// ArrayList<DocumentVO> vos = ui.getDocuments("all", "all", "all",1);
		// ArrayList<DocumentVO> vos = ui.getUpLoadList("严顺宽");
		// for (DocumentVO vo : vos) {
		// System.out.println(vo.getID());
		// System.out.println(vo.getName());
		// System.out.println(vo.getProfile());
		// System.out.println(vo.getTag());
		// System.out.println("-----------------------------");
		// }
		// DocumentVO vo = vos.get(0);
		// vo.setProfile("呵呵哒");
		// vo.setTag("喵喵哒");
		// System.out.println(ui.modify(vo));
		// vos = ui.getDocuments("南京大学", "软件学院", "all",1);
		// for (DocumentVO vo3 : vos) {
		// System.out.println(vo3.getID());
		// System.out.println(vo3.getName());
		// System.out.println(vo3.getProfile());
		// System.out.println(vo3.getTag());
		// System.out.println("-----------------------------");
		// }
		//
		// System.out.println(ui.delete(vo));
		// System.out.println(ui.addPraise(1)[0]+" "+ui.addPraise(1)[1]);
		// System.out.println(ui.addCriticism(1)[0]+" "+ui.addCriticism(1)[1]);
		// ui.getHotDocuments("南京大学");
		System.out.println(ui.getPageNum("南京大学", "软件学院", "all"));

	}
}
