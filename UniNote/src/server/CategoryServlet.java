package server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import sql.CategoryInfo;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer total = (Integer) request.getSession().getAttribute("total");
		int temp = 0;
		if (total == null) {
			temp = 1;
			total = 0;
		} else {
			temp = total.intValue() + 1;
		}
		request.getSession().setAttribute("total", total.intValue() + temp);
		response.setContentType("application/xml;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String parameter = request.getParameter("school");
//		String school = new String(parameter.getBytes("ISO-8859-1"),"utf-8");
//		String school = parameter;
		String school = URLDecoder.decode(parameter,"utf-8");
		
		Map<String, ArrayList<String>> categorys = new HashMap<String, ArrayList<String>>();
		CategoryInfo ci = new CategoryInfo();
		System.out.println(school);
		categorys = ci.getCategorysBySchool(school);

		// 创建XML文件
		String xmlStr = writeXMLString(categorys);
		out.println(xmlStr);
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

	// 将Document内容 写入XML字符串并返回
	private String callWriteXmlString(Document doc, String encoding) {
		try {
			Source source = new DOMSource(doc);
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			OutputStreamWriter write = new OutputStreamWriter(outStream);
			Result result = new StreamResult(write);

			Transformer xformer = TransformerFactory.newInstance()
					.newTransformer();
			xformer.setOutputProperty(OutputKeys.ENCODING, encoding);

			xformer.transform(source, result);
			return outStream.toString();

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			return null;
		} catch (TransformerException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 功能：生成XML格式的字符串
	 */
	public String writeXMLString(Map<String, ArrayList<String>> categorys) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = dbf.newDocumentBuilder();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Document doc = builder.newDocument();
		doc.setXmlVersion("1.0");
		Element root = doc.createElement("categorys");
		doc.appendChild(root); // 将根元素添加到文档上

		// 获取文件信息
		Iterator<Entry<String, ArrayList<String>>> iter = categorys.entrySet()
				.iterator();
		while (iter.hasNext()) {
			Map.Entry<String, ArrayList<String>> entry = (Map.Entry<String, ArrayList<String>>) iter
					.next();
			String department = entry.getKey();
			// 创建一个文件
			Element dep = doc.createElement("department");
			// 设置成形如这样的结果 <document 编号 = "1">
			dep.setAttribute("院系", department);
			root.appendChild(dep);// 添加属性

			ArrayList<String> courses = new ArrayList<String>();
			courses = entry.getValue();
			for (String course : courses) {
				// 创建课程节点
				Element courseElement = doc.createElement("course");
				dep.appendChild(courseElement);
				Text tcourseElement = doc.createTextNode(course);
				courseElement.appendChild(tcourseElement);
			}
		}

		try {
			String result = callWriteXmlString(doc, "utf-8");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		CategoryServlet categoryServlet = new CategoryServlet();
		Map<String, ArrayList<String>> categorys = new HashMap<String, ArrayList<String>>();
		CategoryInfo ci = new CategoryInfo();
		categorys = ci.getCategorysBySchool("南京大学");
		System.out.println(categoryServlet.writeXMLString(categorys));
	}

}
