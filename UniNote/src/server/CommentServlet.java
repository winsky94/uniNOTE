package server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.CommentInfo;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommentServlet() {
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

		int flag = Integer.parseInt(request.getParameter("flag"));
		int doucmentID = Integer.parseInt(request.getParameter("documentID"));
		String name = (String) request.getParameter("nickname");
		String nickname = new String(name.getBytes("ISO-8859-1"),"utf-8");

		CommentInfo di = new CommentInfo();
		int[] result = {};// 返回值第0位表示点赞数，第1位表示踩数，第2位表示操作成功与否，1为成功，0为失败，2表示自己给自己点赞
		if (flag == 0) {
			// 点赞
			result = di.addPraise(nickname, doucmentID);
		} else if (flag == 1) {
			// 踩
			result = di.addCriticism(nickname, doucmentID);
		}
		out.println(result[0] + "," + result[1] + "," + result[2]);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
