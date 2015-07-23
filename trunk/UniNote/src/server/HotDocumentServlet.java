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
 * Servlet implementation class HotDocumentServlet
 */
@WebServlet("/HotDocumentServlet")
public class HotDocumentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HotDocumentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		PrintWriter out = response.getWriter();

		String s = request.getParameter("school");
		String school = new String(s.getBytes("iso-8859-1"), "utf-8");

		DocumentInfo di = new DocumentInfo();
		ArrayList<DocumentVO> documents = new ArrayList<DocumentVO>();
		documents = di.getHotDocuments(school);

		// 创建XML文件
		String xmlStr = writeXMLString(documents);
		out.println(xmlStr);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
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

			// 创建文本ID节点
			Element ID = doc.createElement("ID");
			file.appendChild(ID);
			Text tID = doc.createTextNode(vo.getID() + "");
			ID.appendChild(tID);

			// 创建文本姓名节点
			Element name = doc.createElement("name");
			file.appendChild(name);
			Text tname = doc.createTextNode(vo.getName());
			name.appendChild(tname);

			// 创建自定义文件名节点
			Element customName = doc.createElement("filename");
			file.appendChild(customName);
			Text tCustomName = doc.createTextNode(String.valueOf(vo
					.getCustomName()));
			customName.appendChild(tCustomName);

			// 创建文本存放路径节点
			Element path = doc.createElement("path");
			file.appendChild(path);
			Text tage = doc.createTextNode(String.valueOf(vo.getPath()));
			path.appendChild(tage);

			// 创建文件类型节点
			Element type = doc.createElement("type");
			file.appendChild(type);
			Text tType = doc.createTextNode(String.valueOf(vo.getType()));
			type.appendChild(tType);

			// 创建文件简介节点
			Element profile = doc.createElement("profile");
			file.appendChild(profile);
			Text tProfile = doc.createTextNode(String.valueOf(vo.getProfile()));
			profile.appendChild(tProfile);

			// 创建文件标签节点
			Element tag = doc.createElement("tag");
			file.appendChild(tag);
			Text tTag = doc.createTextNode(String.valueOf(vo.getTag()));
			tag.appendChild(tTag);

			// 创建文件是否是考研资料节点
			Element pg = doc.createElement("postgraduate");
			file.appendChild(pg);
			Text tPg = doc.createTextNode(String.valueOf(vo
					.getPostgraduateData()));
			pg.appendChild(tPg);

			// 创建文件学校节点
			Element school = doc.createElement("school");
			file.appendChild(school);
			Text tSchool = doc.createTextNode(String.valueOf(vo.getSchool()));
			school.appendChild(tSchool);

			// 创建文件学院节点
			Element department = doc.createElement("department");
			file.appendChild(department);
			Text tDepartment = doc.createTextNode(String.valueOf(vo
					.getDepartment()));
			department.appendChild(tDepartment);

			// 创建文件课程节点
			Element course = doc.createElement("course");
			file.appendChild(course);
			Text tCourse = doc.createTextNode(String.valueOf(vo.getCourse()));
			course.appendChild(tCourse);

			// 创建文件上传者节点
			Element uploader = doc.createElement("uploader");
			file.appendChild(uploader);
			Text tUploader = doc
					.createTextNode(String.valueOf(vo.getUploader()));
			uploader.appendChild(tUploader);

			// 创建文件点赞数节点
			Element praise = doc.createElement("praise");
			file.appendChild(praise);
			Text tPraise = doc.createTextNode(String.valueOf(vo.getPraise()));
			praise.appendChild(tPraise);

			// 创建文件被踩数节点
			Element criticism = doc.createElement("criticism");
			file.appendChild(criticism);
			Text tCriticism = doc.createTextNode(String.valueOf(vo
					.getCriticism()));
			criticism.appendChild(tCriticism);

			// 创建文件下载量节点
			Element downloadNum = doc.createElement("downloadNum");
			file.appendChild(downloadNum);
			Text tDownloadNum = doc.createTextNode(String.valueOf(vo
					.getDownloadNum()));
			downloadNum.appendChild(tDownloadNum);

		}
		try {
			String result = callWriteXmlString(doc, "utf-8");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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
}
