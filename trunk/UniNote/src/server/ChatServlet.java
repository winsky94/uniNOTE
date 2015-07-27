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

import object.ChatVO;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import sql.ChatInfo;

/**
 * Servlet implementation class ChatServlet
 */
@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChatServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

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

		int documentID = Integer.parseInt(request.getParameter("documentID"));

		ChatInfo ci = new ChatInfo();
		ArrayList<ChatVO> chats = ci.getChats(documentID);
		// 创建XML文件
		String xmlStr = writeXMLString(chats);
		out.println(xmlStr);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

	/**
	 * 功能：生成XML格式的字符串
	 */
	public String writeXMLString(ArrayList<ChatVO> chats) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = dbf.newDocumentBuilder();
		} catch (Exception e) {
		}
		Document doc = builder.newDocument();
		doc.setXmlVersion("1.0");
		Element root = doc.createElement("chats");
		doc.appendChild(root); // 将根元素添加到文档上

		// 获取文件信息
		for (int i = 0; i < chats.size(); i++) {
			ChatVO vo = chats.get(i);
			// 创建一个文件
			Element file = doc.createElement("chat");
			// 设置成形如这样的结果 <document 编号 = "1">
			// file.setAttribute("编号", "1");
			root.appendChild(file);// 添加属性

			// 创建评论者姓名节点
			Element name = doc.createElement("name");
			file.appendChild(name);
			Text tname = doc.createTextNode(vo.getNickName());
			name.appendChild(tname);

			// 创建评论日期节点
			Element dateName = doc.createElement("date");
			file.appendChild(dateName);
			Text tDateName = doc.createTextNode(vo.getDate());
			dateName.appendChild(tDateName);

			// 创建评论内容节点
			Element content = doc.createElement("content");
			file.appendChild(content);
			Text tContent = doc.createTextNode(vo.getContent());
			content.appendChild(tContent);
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
