package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import object.DocumentVO;

public class DownloadInfo {
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	public void add(String nickname,int documentID) {
		try {
			connection = SqlManager.getConnection();
			statement = connection.createStatement();
			String name=nickname.replace("'", "''");
			String query = "select * from download where nickname='" +name+"' and documentID="+ documentID +" limit 1";
			resultSet= statement.executeQuery(query);
			if (!resultSet.next()) {				
				query = "select max(downloadID) as downloadNum from download";
				ResultSet rs = statement.executeQuery(query);
				rs.next();
				int count = rs.getInt("downloadNum");
				rs.close();
				PreparedStatement statement1 = connection
						.prepareStatement("INSERT INTO download VALUES(?,?,?)");
				statement1.setInt(1, ++count);
				statement1.setString(2, nickname);
				statement1.setInt(3, documentID);
				statement1.execute();
				statement1.close();
			}
			query = "update document set downloadNum=downloadNum+1 where documentID="
					+ documentID;
			statement.executeUpdate(query);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeMySql();
		}
	}

	public ArrayList<DocumentVO> getDownloads(String nickname){
		ArrayList<DocumentVO> result=new ArrayList<DocumentVO>();
		try {
			connection = SqlManager.getConnection();
			statement = connection.createStatement();
			String name=nickname.replace("'", "''");
			String query = "select * from download,document where nickname='"
					+ name + "' and download.documentID=document.documentID";
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int ID = resultSet.getInt("documentID");
                String name1=resultSet.getString("name");
                String customname=resultSet.getString("customName");
                String path=resultSet.getString("path");
                String type=resultSet.getString("type");
                String profile=resultSet.getString("profile");
                String tag=resultSet.getString("tag");
                String postgraduate=resultSet.getString("postgraduateData");
                int categoryID=resultSet.getInt("categoryID");
                String uploader=resultSet.getString("uploader");
                int praise=resultSet.getInt("praise");
                int criticism=resultSet.getInt("criticism");
                int downloadNum=resultSet.getInt("downloadNum");
                DocumentVO vo=new DocumentVO(ID, name1, customname, path, type, profile, tag, postgraduate, categoryID, uploader, praise, criticism, downloadNum);
                result.add(vo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeMySql();
		}
		return result;
	}
	
	private void createTable() {
		try {
			connection = SqlManager.getConnection();
			statement = connection.createStatement();
			statement.execute("drop table if exists download");
			statement
					.execute("create table download(downloadID int not null auto_increment,"
							+ "nickname varchar(40) not null default 'null',"
							+ "documentID int not null default 0,"
							+ "primary key(downloadID));");
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
          DownloadInfo dl=new DownloadInfo();
          dl.createTable();
          dl.add("1", 1);
          dl.add("1", 1);
          dl.getDownloads("1");
          dl.getDownloads("2");
	}
}
