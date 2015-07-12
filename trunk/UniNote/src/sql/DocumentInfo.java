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
		int count=0;
		boolean istrue=false;
		try {
			Connection con = SqlManager.getConnection();
			con.setAutoCommit(false);
			Statement sql = con.createStatement();
			String name=vo.getName();
			name.replace("'", "''");
			String query = "select documentID from document where name='"+name+"'";
			ResultSet resultSet1 = sql.executeQuery(query);
			if(!resultSet1.next()){
			   istrue=true;
			   resultSet1.close();
			   query = "select count(*) as documentnum from document";
			   ResultSet resultSet = sql.executeQuery(query);
			   resultSet.next();
			   count=resultSet.getInt("documentnum");
			   PreparedStatement statement = con
					.prepareStatement("INSERT INTO document VALUES(?, ?,?)");
			   statement.setInt(1, ++count);
			   statement.setString(2, vo.getName());
			   statement.setString(3, vo.getPath());
			   statement.addBatch();
//			   System.out.println(count);

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
		String path=null;
		File file=null;
		try {
			Connection con = SqlManager.getConnection();
			con.setAutoCommit(false);
			Statement sql = con.createStatement();
			String name=s;
			name.replace("'", "''");
			String query = "select path from document where name='"+name+"' limit 1";
			ResultSet resultSet1 = sql.executeQuery(query);
			if(resultSet1.next()){
			    path=resultSet1.getString("path");
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
		if(path!=null){
            file=new File(path);  
            if(!file.exists())
            	file=null;
		}
		return file;		
	}
	
	/**
	 * 得到数据库中全部的文件对象
	 * @return
	 */
	public ArrayList<DocumentVO> getDocuments(){
		ArrayList<DocumentVO> documents=new ArrayList<DocumentVO>();
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String query = "select * from document";
			ResultSet resultSet = sql.executeQuery(query);
			while(resultSet.next()){
				String name=resultSet.getString("name");
				String path=resultSet.getString("path");
				DocumentVO vo=new DocumentVO(name, path);
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
	
	public void createTable(){
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			sql.execute("drop table if exists document");
			sql.execute("create table document(documentID int not null auto_increment,"
					+ "name varchar(40) not null default 'null',"
					+ "path varchar(40) not null default 'null',"
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
		DocumentInfo ui=new DocumentInfo();
//		ui.createTable();
//		DocumentVO vo=new DocumentVO("hehe", "C:/1.c");
//		System.out.println(ui.add(vo));
//		System.out.println(ui.search("hehe"));
		System.out.println(ui.getDocuments().size());
	} 
}
