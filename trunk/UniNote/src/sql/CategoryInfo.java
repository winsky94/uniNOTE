package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import object.CategoryVO;

public class CategoryInfo {
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	public static void main(String[] args) {
		CategoryInfo ci = new CategoryInfo();
		ci.createTable();

		CategoryVO vo1 = new CategoryVO("南京大学", "软件学院", "计算机与操作系统");
		CategoryVO vo2 = new CategoryVO("南京大学", "软件学院", "数据库系统");
		CategoryVO vo3 = new CategoryVO("南京大学", "软件学院", "软件工程与计算Ⅰ");
		CategoryVO vo4 = new CategoryVO("南京大学", "软件学院", "软件工程与计算Ⅱ");
		CategoryVO vo5 = new CategoryVO("南京大学", "软件学院", "软件工程与计算Ⅲ");
		CategoryVO vo6 = new CategoryVO("南京大学", "软件学院", "数据结构与算法");
		CategoryVO vo7 = new CategoryVO("南京大学", "软件学院", "计算机网络");
		CategoryVO vo8 = new CategoryVO("南京大学", "软件学院", "离散数学");
		CategoryVO vo9 = new CategoryVO("南京大学", "软件学院", "计算机组织结构");
		CategoryVO vo10 = new CategoryVO("南京大学", "软件学院", "计算系统基础");
		CategoryVO vo11 = new CategoryVO("南京大学", "软件学院", "软件工程统计方法");
		CategoryVO vo12 = new CategoryVO("南京大学", "计算机科学与技术系", "编译原理");
		CategoryVO vo13 = new CategoryVO("南京大学", "文学院", "中国古代文学");
		CategoryVO vo14 = new CategoryVO("南京大学", "文学院", "剧场与片场实践");
		CategoryVO vo15 = new CategoryVO("南京大学", "文学院", "古代汉语");
		CategoryVO vo16 = new CategoryVO("南京大学", "历史学院", "世界通史");
		CategoryVO vo17 = new CategoryVO("南京大学", "历史学院", "中国通史");
		CategoryVO vo18 = new CategoryVO("南京大学", "法学院", "刑法学");
		CategoryVO vo19 = new CategoryVO("南京大学", "法学院", "民法学");
		CategoryVO vo20 = new CategoryVO("南京大学", "哲学系", "中国哲学史");
		CategoryVO vo21 = new CategoryVO("南京大学", "哲学系", "欧洲哲学史");
		CategoryVO vo22 = new CategoryVO("南京大学", "新闻传播学院", "传播学概论");
		CategoryVO vo23 = new CategoryVO("南京大学", "新闻传播学院", "广告学概论");
		CategoryVO vo24 = new CategoryVO("南京大学", "政府管理学院", "公共政策基础");
		CategoryVO vo25 = new CategoryVO("南京大学", "政府管理学院", "国际政治学");
		CategoryVO vo26 = new CategoryVO("南京大学", "信息管理学院", "信息检索");
		CategoryVO vo27 = new CategoryVO("南京大学", "信息管理学院", "知识创新与学术规范");
		CategoryVO vo28 = new CategoryVO("南京大学", "社会学院", "社会工作概论");
		CategoryVO vo29 = new CategoryVO("南京大学", "社会学院", "社会政策概论");
		CategoryVO vo30 = new CategoryVO("南京大学", "商学院", "会计学");
		CategoryVO vo31 = new CategoryVO("南京大学", "商学院", "宏观经济学");
		CategoryVO vo32 = new CategoryVO("南京大学", "外国语学院", "英语文化选读");
		CategoryVO vo33 = new CategoryVO("南京大学", "外国语学院", "英语口语交际");
		CategoryVO vo34 = new CategoryVO("南京大学", "数学系", "数学分析");
		CategoryVO vo35 = new CategoryVO("南京大学", "数学系", "复变函数");
		CategoryVO vo36 = new CategoryVO("南京大学", "物理学院", "计算物理");
		CategoryVO vo37 = new CategoryVO("南京大学", "物理学院", "理论力学");
		CategoryVO vo38 = new CategoryVO("南京大学", "化学化工学院", "有机化学");
		CategoryVO vo39 = new CategoryVO("南京大学", "化学化工学院", "仪器分析");
		CategoryVO vo40 = new CategoryVO("南京大学", "生命科学学院", "细胞生物学");
		CategoryVO vo41 = new CategoryVO("南京大学", "生命科学学院", "分子生物学");
		CategoryVO vo42 = new CategoryVO("南京大学", "地球科学与工程学院", "构造地质学");
		CategoryVO vo43 = new CategoryVO("南京大学", "地球科学与工程学院", "火成岩石学");
		CategoryVO vo44 = new CategoryVO("南京大学", "地球与海洋科学学院", "海洋科学导论");
		CategoryVO vo45 = new CategoryVO("南京大学", "地球与海洋科学学院", "数字地面模型");
		CategoryVO vo46 = new CategoryVO("南京大学", "大气科学学院", "环境科学概论");
		CategoryVO vo47 = new CategoryVO("南京大学", "大气科学学院", "大气探测实验");
		CategoryVO vo48 = new CategoryVO("南京大学", "电子科学与工程学院", "高频电路");
		CategoryVO vo49 = new CategoryVO("南京大学", "电子科学与工程学院", "数字信号处理");
		CategoryVO vo50 = new CategoryVO("南京大学", "现代工程与应用科学学院", "应用工学");
		CategoryVO vo51 = new CategoryVO("南京大学", "现代工程与应用科学学院", "信号与系统");
		CategoryVO vo52 = new CategoryVO("南京大学", "环境学院", "给水排水");
		CategoryVO vo53 = new CategoryVO("南京大学", "环境学院", "环境工程原理");
		CategoryVO vo54 = new CategoryVO("南京大学", "天文与空间科学学院", "普通天文学");
		CategoryVO vo55 = new CategoryVO("南京大学", "天文与空间科学学院", "天文探秘");
		CategoryVO vo56 = new CategoryVO("南京大学", "计算机与科学技术系", "图论");
		CategoryVO vo57 = new CategoryVO("南京大学", "计算机与科学技术系", "博弈论及其应用");
		CategoryVO vo58 = new CategoryVO("南京大学", "医学院", "正常人体结构与功能");
		CategoryVO vo59 = new CategoryVO("南京大学", "医学院", "生物化学实验");
		CategoryVO vo60 = new CategoryVO("南京大学", "匡亚明学院", "近代应用数学");
		CategoryVO vo61 = new CategoryVO("南京大学", "匡亚明学院", "数学物理方法");
		CategoryVO vo62 = new CategoryVO("南京大学", "工程管理学院", "物流工程与管理");
		CategoryVO vo63 = new CategoryVO("南京大学", "工程管理学院", "高级运筹学");
		CategoryVO vo64 = new CategoryVO("南京大学", "建筑与城市规划学院", "中国建筑史");
		CategoryVO vo65 = new CategoryVO("南京大学", "建筑与城市规划学院", "建筑设计（一）");
		CategoryVO vo66 = new CategoryVO("南京大学", "海外教育学院", "跨文化交际");
		CategoryVO vo67 = new CategoryVO("南京大学", "海外教育学院", "语言学概论");
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
		categorys.add(vo11);
		categorys.add(vo12);
		categorys.add(vo13);
		categorys.add(vo14);
		categorys.add(vo15);
		categorys.add(vo16);
		categorys.add(vo17);
		categorys.add(vo18);
		categorys.add(vo19);
		categorys.add(vo20);
		categorys.add(vo21);
		categorys.add(vo22);
		categorys.add(vo23);
		categorys.add(vo24);
		categorys.add(vo25);
		categorys.add(vo26);
		categorys.add(vo27);
		categorys.add(vo28);
		categorys.add(vo29);
		categorys.add(vo30);
		categorys.add(vo31);
		categorys.add(vo32);
		categorys.add(vo33);
		categorys.add(vo34);
		categorys.add(vo35);
		categorys.add(vo36);
		categorys.add(vo37);
		categorys.add(vo38);
		categorys.add(vo39);
		categorys.add(vo40);
		categorys.add(vo41);
		categorys.add(vo42);
		categorys.add(vo43);
		categorys.add(vo44);
		categorys.add(vo45);
		categorys.add(vo46);
		categorys.add(vo47);
		categorys.add(vo48);
		categorys.add(vo49);
		categorys.add(vo50);
		categorys.add(vo51);
		categorys.add(vo52);
		categorys.add(vo53);
		categorys.add(vo54);
		categorys.add(vo55);
		categorys.add(vo56);
		categorys.add(vo57);
		categorys.add(vo58);
		categorys.add(vo59);
		categorys.add(vo60);
		categorys.add(vo61);
		categorys.add(vo62);
		categorys.add(vo63);
		categorys.add(vo64);
		categorys.add(vo65);
		categorys.add(vo66);
		categorys.add(vo67);
		for (int i = 0; i < categorys.size(); i++) {
			CategoryVO vo = categorys.get(i);
			System.out.println(i + " " + ci.add(vo));
		}
		// Map<String, ArrayList<String>> category = new HashMap<String,
		// ArrayList<String>>();
		// category = ci.getCategorysBySchool("南京大学");
		// Iterator<Entry<String, ArrayList<String>>> iter = category.entrySet()
		// .iterator();
		// while (iter.hasNext()) {
		// Map.Entry<String, ArrayList<String>> entry = (Map.Entry<String,
		// ArrayList<String>>) iter
		// .next();
		// String key = entry.getKey();
		// System.out.println(key);
		// ArrayList<String> val = entry.getValue();
		// for (String s : val) {
		// System.out.println(s);
		// }
		// System.out.println("---------------------------------------");
		// }

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
					+ course + "' limit 1";
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
		// System.out.println(school + "_" + department + "_" + course + "_"
		// + "分类编号=" + id);
		return id;
	}

	/**
	 * 得到某一学校的全部院系和院系下设的课程
	 * 
	 * @param school
	 *            学校
	 * @return 该校的院系和院系开设的课程
	 */
	public Map<String, ArrayList<String>> getCategorysBySchool(String school) {
		Map<String, ArrayList<String>> category = new HashMap<String, ArrayList<String>>();
		try {
			connection = SqlManager.getConnection();
			statement = connection.createStatement();
			String query = "select department,course from category where school='"
					+ school + "'";
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				String department = resultSet.getString("department");
				String course = resultSet.getString("course");
				ArrayList<String> courses = category.get(department);
				if (courses == null) {
					courses = new ArrayList<String>();
					courses.add(course);
				} else {
					courses.add(course);
				}
				category.put(department, courses);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeMySql();
		}
		return category;
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
