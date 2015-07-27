package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import object.ChatVO;

public class ChatInfo {
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	public static void main(String[] args) {
		ChatInfo ci = new ChatInfo();
		ci.createTable();
		
		// SimpleDateFormat format = new
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// String date = format.format(new Date());
		// ChatVO vo1=new ChatVO(1, "1", date, "测试1");
		// ci.addChat(vo1);
		// ChatVO vo2=new ChatVO(1, "2", date, "测试2");
		// ci.addChat(vo2);
		// ChatVO vo3=new ChatVO(2, "1", date, "测试3");
		// ci.addChat(vo3);
		// System.out.println(ci.getChats(1).size());
	}

	/**
	 * 获取文章的评论
	 * 
	 * @param documentID
	 *            文章编号
	 */
	public ArrayList<ChatVO> getChats(int documentID) {
		ArrayList<ChatVO> result = new ArrayList<ChatVO>();
		try {
			connection = SqlManager.getConnection();
			statement = connection.createStatement();
			String sql = "select * from chat where documentID=" + documentID;
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String nickName = resultSet.getString("nickname");
				String date = resultSet.getString("date");
				String content = resultSet.getString("content");
				ChatVO vo = new ChatVO(documentID, nickName, date, content);
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

	/**
	 * 存储某位用户对谋篇文章的评论
	 * 
	 * @param vo
	 *            一个评论对象
	 */
	public void addChat(ChatVO vo) {
		try {
			connection = SqlManager.getConnection();
			statement=connection.createStatement();
			PreparedStatement preStatement = connection
					.prepareStatement("INSERT INTO chat VALUES(?,?,?,?,?)");
			
			String sql="select max(chatID) as count from chat";
			resultSet=statement.executeQuery(sql);
			int count=0;
			while(resultSet.next()){
				count=resultSet.getInt("count");
			}
			preStatement.setInt(1, ++count);
			preStatement.setString(2, vo.getNickName());
			preStatement.setString(3, vo.getDate());
			preStatement.setInt(4, vo.getDocumentID());
			preStatement.setString(5, vo.getContent());
			preStatement.execute();
			preStatement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeMySql();
		}
	}

	private void createTable() {
		try {
			connection = SqlManager.getConnection();
			statement = connection.createStatement();
			statement.execute("drop table if exists chat");
			statement
					.execute("create table chat(chatID int not null auto_increment,"
							+ "nickname varchar(40) not null default 'null',"
							+ "date varchar(40) not null default 'null',"
							+ "documentID int not null default 0,"
							+ "content varchar(100) not null default 'null',"
							+ "primary key(chatID));");
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
}
