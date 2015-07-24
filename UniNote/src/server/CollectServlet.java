package server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.CollectionInfo;

/**
 * Servlet implementation class CollectServlet
 */
@WebServlet("/CollectServlet")
public class CollectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CollectServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/xml;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		String nickname = request.getParameter("nickname");
		String name=new String(nickname.getBytes("ISO-8859-1"),"utf-8");
		int documentID = Integer.parseInt(request.getParameter("documentID"));

		CollectionInfo ci = new CollectionInfo();
		ci.changeState(name, documentID);

		// response.sendRedirect(request.getHeader("Referer"));
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
