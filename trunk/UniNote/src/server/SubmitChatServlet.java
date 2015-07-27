package server;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import object.ChatVO;
import sql.ChatInfo;

/**
 * Servlet implementation class SubmitChatServlet
 */
@WebServlet("/SubmitChatServlet")
public class SubmitChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SubmitChatServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/xml;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		int documentID = Integer.parseInt(request.getParameter("documentID"));
		String name = request.getParameter("nickname");
		String nickname = new String(name.getBytes("iso-8859-1"), "utf-8");
		String cont = request.getParameter("content");
		String content = new String(cont.getBytes("iso-8859-1"), "utf-8");

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date());
		ChatVO vo = new ChatVO(documentID, nickname, date, content);

		ChatInfo ci = new ChatInfo();
		ci.addChat(vo);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
