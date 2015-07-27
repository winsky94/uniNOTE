package server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import object.DocumentVO;
import sql.DocumentInfo;
import sql.DownloadInfo;
import sql.UserInfo;

/**
 * Servlet implementation class DownLoadServlet
 */
@WebServlet("/DownLoadServlet")
public class DownLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DownLoadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		// 下载文件
		String nickname = request.getParameter("nickname");
		int documentID = Integer.parseInt(request.getParameter("ID"));
		
		// 扣除下载者的积分
		int point = 1;
		UserInfo userInfo = new UserInfo();
		boolean result=userInfo.minusPoint(nickname, point);
			// 只有成功扣除积分才可以进行下载
		if(result){
			System.out.println("可以下载！我进来了！");
			DocumentInfo documentInfo = new DocumentInfo();
			DocumentVO vo = documentInfo.getDocumentByID(documentID);
			// 增加上传者的积分
			String uploader = vo.getUploader();
			userInfo.addPoint(uploader, point);
			// 下载文件
			String fileName = vo.getCustomName();
			String type = vo.getType();
			String tempName = URLEncoder.encode(fileName, "utf-8");
			tempName = tempName.replace("+", "%20");
			response.setHeader("Content-Disposition",
					"attachment;filename*=utf-8''" + tempName + "." + type);
			// 说明一下web站点下载文件的原理
			// 1.把文件读入到内存
			// 2.把文件一点一点地发送给浏览器端

			// 打开文件
			// (1)获取到要下载文件的全路径
			// String path =
			// this.getServletContext().getRealPath("D:/web_server_file/"+temp);
			String path = vo.getPath();
			// (2)创建文件输入流
			FileInputStream fis = new FileInputStream(path);
			// 做一个缓冲字节数组
			byte buffer[] = new byte[1024];
			int len = 0;// 表示实际每次读取了多少个字节
			OutputStream os = response.getOutputStream();
			while ((len = fis.read(buffer)) > 0) {
				os.write(buffer, 0, len);
			}
			os.close();
			fis.close();
			// <a href="/web/DowFileServlet?filename=xx.mp3">点击下载</a>
			DownloadInfo downloadInfo = new DownloadInfo();
			downloadInfo.add(nickname, documentID);
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
