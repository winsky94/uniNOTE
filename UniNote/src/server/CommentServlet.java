package server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.DocumentInfo;

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

		DocumentInfo di = new DocumentInfo();
		int[] result = {};
		if (flag == 0) {
			// 点赞
			result = di.addPraise(doucmentID);
		} else if (flag == 1) {
			// 踩
			result = di.addCriticism(doucmentID);
		}
		out.println(result[0] + "," + result[1]);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
