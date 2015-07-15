package server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

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

import object.DocumentVO;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import sql.DocumentInfo;

/**
 * Servlet implementation class DocumentServlet
 */
@WebServlet("/DocumentServlet")
public class DocumentOverViewServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Integer total = (Integer) request.getSession().getAttribute("total");
		int temp = 0;
		if (total == null) {
			temp = 1;
			total = 0;
		} else {
			temp = total.intValue() + 1;
		}
		request.getSession().setAttribute("total", total.intValue() + temp);
		response.setContentType("text/xml;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		ArrayList<DocumentVO> documents = new ArrayList<DocumentVO>();
		DocumentInfo documentInfo = new DocumentInfo();
		documents = documentInfo.getDocuments();

		// 创建XML文件
		String xmlStr = writeXMLString(documents);
		out.println(xmlStr);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
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
	public String writeXMLString(ArrayList<DocumentVO> documents) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = dbf.newDocumentBuilder();
		} catch (Exception e) {
		}
		Document doc = builder.newDocument();
        doc.setXmlVersion("1.0");
		Element root = doc.createElement("documents");
		doc.appendChild(root); // 将根元素添加到文档上

		// 获取文件信息
		for (int i = 0; i < documents.size(); i++) {
			DocumentVO vo = documents.get(i);
			// 创建一个文件
			Element file = doc.createElement("document");
			// 设置成形如这样的结果 <document 编号 = "1">
			// file.setAttribute("编号", "1");
			root.appendChild(file);// 添加属性

			// 创建文本姓名节点
			Element name = doc.createElement("name");
			file.appendChild(name);
			Text tname = doc.createTextNode(vo.getName());
			name.appendChild(tname);

			// 创建文本存放路径节点
			Element path = doc.createElement("path");
			file.appendChild(path);
			Text tage = doc.createTextNode(String.valueOf(vo.getPath()));
			path.appendChild(tage);

			// 创建文件类型节点
			Element type = doc.createElement("type");
			file.appendChild(type);
			Text tType = doc.createTextNode(String.valueOf(vo.getType()));
			path.appendChild(tType);
			
			//创建文件简介节点
			Element profile = doc.createElement("profile");
			file.appendChild(profile);
			Text tProfile = doc.createTextNode(String.valueOf(vo.getProfile()));
			path.appendChild(tProfile);
			
			//创建文件标签节点
			Element tag = doc.createElement("tag");
			file.appendChild(tag);
			Text tTag= doc.createTextNode(String.valueOf(vo.getTag()));
			path.appendChild(tTag);
			
			//创建文件是否是考研资料节点
			Element pg = doc.createElement("postgraduate");
			file.appendChild(pg);
			Text tPg= doc.createTextNode(String.valueOf(vo.getPostgraduateData()));
			path.appendChild(tPg);
			
			//创建文件学校节点
			Element school = doc.createElement("school");
			file.appendChild(school);
			Text tSchool= doc.createTextNode(String.valueOf(vo.getSchool()));
			path.appendChild(tSchool);
			
			//创建文件学院节点
			Element department = doc.createElement("department");
			file.appendChild(department);
			Text tDepartment= doc.createTextNode(String.valueOf(vo.getDepartment()));
			path.appendChild(tDepartment);
			
			//创建文件课程节点
			Element course = doc.createElement("course");
			file.appendChild(course);
			Text tCourse= doc.createTextNode(String.valueOf(vo.getCourse()));
			path.appendChild(tCourse);
			
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
		System.out.println(new DocumentInfo().getDocuments());
	}

}
