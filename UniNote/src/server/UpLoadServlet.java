package server;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import object.DocumentVO;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import sql.DocumentInfo;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/UpLoadServlet")
public class UpLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String uploadPath = "D:\\web_server_file"; // 上传文件的目录
	private String tempPath = "d:\\web_server_file\\buffer\\"; // 临时文件目录
	File tempPathFile;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpLoadServlet() {
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
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	@SuppressWarnings("null")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String profile = "";
		String tag = "";
		String postgraduate = "";
		String school = "";
		String department = "";
		String course = "";
		String fileName = "";
		String cuntomName = "";
		String uploader = "";

		String isAJAX = request.getHeader("content-type");
		// System.out.println(isAJAX);

		if (isAJAX.equals("application/x-www-form-urlencoded")) {

			Integer total = (Integer) request.getSession()
					.getAttribute("total");
			int temp = 0;
			if (total == null) {
				temp = 1;
				total = 0;
			} else {
				temp = total.intValue() + 1;
			}
			request.getSession().setAttribute("total", total.intValue() + temp);

			String filename = request.getParameter("fileName");

			StringBuilder builder = new StringBuilder();
			builder.append("<message>");

			DocumentInfo documentInfo = new DocumentInfo();

			boolean isOK = documentInfo.isFileNameOK(filename);

			if (isOK) {
				builder.append("您可以上传该文件").append("</message>");
				out.println(builder.toString());
				// System.out.println("shide:"+filename);
			} else {
				builder.append("您已上传过该文件").append("</message>");
				out.println(builder.toString());
			}

		}

		else {
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
				factory.setRepository(tempPathFile);// 设置缓冲区目录

				// Create a new file upload handler

				// Set overall request size constraint
				upload.setSizeMax(4194304 * 10); // 设置最大文件尺寸，这里是40MB
				upload.setHeaderEncoding("UTF-8");
				List<?> fileItems = upload.parseRequest(request);
				Iterator<?> iter = fileItems.iterator();
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
					item.getInputStream();
					if (!item.isFormField()) {
						fileName = item.getName().replaceAll(" ", "");
						if (fileName != null || !fileName.equals("")) {
							File fullFile = new File(item.getName());
							File savedFile = new File(uploadPath,
									fullFile.getName().replaceAll(" ", ""));
							item.write(savedFile);
						} else {
							return;
						}
						// 文件流
					} else {
						// 非文件流
						if (item.getFieldName().equals("filename")) {
							cuntomName = new String(item.getString().getBytes(
									"ISO-8859-1"), "utf-8");
						}
						if (item.getFieldName().equals("profile")) {
							profile = new String(item.getString().getBytes(
									"ISO-8859-1"), "utf-8");
						}
						if (item.getFieldName().equals("tag")) {
							tag = new String(item.getString().getBytes(
									"ISO-8859-1"), "utf-8");
						}
						if (item.getFieldName().equals("postgraduate")) {
							postgraduate = new String(item.getString()
									.getBytes("ISO-8859-1"), "utf-8");
						}
						if (item.getFieldName().equals("university")) {
							school = new String(item.getString().getBytes(
									"ISO-8859-1"), "utf-8");
							System.out.println("school=" + school);
						}
						if (item.getFieldName().equals("department")) {
							department = new String(item.getString().getBytes(
									"ISO-8859-1"), "utf-8");
							System.out.println("department=" + department);
						}
						if (item.getFieldName().equals("course")) {
							course = new String(item.getString().getBytes(
									"ISO-8859-1"), "utf-8");
						}
						if (item.getFieldName().equals("uploader")) {
							uploader = new String(item.getString().getBytes(
									"ISO-8859-1"), "utf-8");
							;
						}
					}
				}
			} catch (FileUploadException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (postgraduate.equals("on")) {
				postgraduate = "Y";
			} else {
				postgraduate = "N";
			}

			System.out.println("fileName:" + fileName);
			System.out.println("customName:" + cuntomName);
			System.out.println("profile:" + profile);
			System.out.println("tag:" + tag);
			System.out.println("postgraduate:" + postgraduate);
			System.out.println("school:" + school);
			System.out.println("department:" + department);
			System.out.println("course:" + course);
			DocumentInfo documentInfo = new DocumentInfo();
			DocumentVO vo = new DocumentVO(fileName, cuntomName, uploadPath
					+ "\\" + fileName, profile, tag, postgraduate, school,
					department, course, uploader);
			int id = documentInfo.add(vo);

			if (id != -1) {
				// 将文档转成swf备份
				// DocConverter dc = new DocConverter(
				// uploadPath + "\\" + fileName, id + "");
				// dc.conver();
				new SwitchThread(uploader, fileName, id).start();

			}
			response.sendRedirect("/UniNote/list.html");

		}

		// response.sendRedirect("/UniNote/DocumentOverViewServlet");

	}

	public void init() throws ServletException {
		File uploadFile = new File(uploadPath);
		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}
		File tempPathFile = new File(tempPath);
		if (!tempPathFile.exists()) {
			tempPathFile.mkdirs();
		}
	}
}

class SwitchThread extends Thread {
	private String name;
	private int id;
	private String uploadPath;

	SwitchThread(String uploadPath, String name, int id) {
		this.uploadPath = uploadPath;
		this.name = name;
		this.id = id;
	}

	public void run() {
		DocConverter dc = new DocConverter(uploadPath + "\\" + name, id + "");
		dc.conver();

		this.interrupt();
	}
}