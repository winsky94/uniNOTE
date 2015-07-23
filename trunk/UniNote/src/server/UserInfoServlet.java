package server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

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

import object.UserVO;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import sql.UserInfo;

/**
 * Servlet implementation class UserInfoServlet
 */
@WebServlet("/UserInfoServlet")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
  //个人资料
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

		String name = request.getParameter("nickname");
		String nickname = new String(name.getBytes("iso-8859-1"), "utf-8");

		UserInfo userInfo = new UserInfo();
		UserVO user = userInfo.getVoByName(nickname);

		// 创建XML文件
		String xmlStr = writeXMLString(user);
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
	public String writeXMLString(UserVO user) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = dbf.newDocumentBuilder();
		} catch (Exception e) {
		}
		Document doc = builder.newDocument();
		doc.setXmlVersion("1.0");
		
		Element root = doc.createElement("user");
		doc.appendChild(root); // 将根元素添加到文档上
		
		//创建文本邮箱节点
		Element email = doc.createElement("email");
		root.appendChild(email); // 将根元素添加到文档上
		Text tRootText = doc.createTextNode(user.getEmail());
		email.appendChild(tRootText);

		// 创建文本学校节点
		Element school = doc.createElement("school");
		root.appendChild(school); // 将根元素添加到文档上
		Text tSchool= doc.createTextNode(user.getSchool());
		school.appendChild(tSchool);
		
		//创建文本电话节点
		Element phone = doc.createElement("phoneNumber");
		root.appendChild(phone); // 将根元素添加到文档上
		Text tPhone= doc.createTextNode(user.getPhoneNumber());
		phone.appendChild(tPhone);
		
		//创建积分节点
		Element point = doc.createElement("point");
		root.appendChild(point); // 将根元素添加到文档上
		Text tPoint= doc.createTextNode(user.getPoint()+"");
		point.appendChild(tPoint);
		
		// 创建上传量节点
		Element uploadNum = doc.createElement("uploadNum");
		root.appendChild(uploadNum); // 将根元素添加到文档上
		Text tUploadNum = doc.createTextNode(user.getUpLoadNum()+ "");
		uploadNum.appendChild(tUploadNum);
		
		// 创建下载量节点
		Element downloadNum = doc.createElement("downloadNum");
		root.appendChild(downloadNum); // 将根元素添加到文档上
		Text tDownloadNum = doc.createTextNode(user.getDownLoadNum() + "");
		downloadNum.appendChild(tDownloadNum);
		
		try {
			String result = callWriteXmlString(doc, "utf-8");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		
	}
}
