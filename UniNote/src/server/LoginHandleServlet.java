package server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import object.UserVO;
import sql.UserInfo;

/**
 * Servlet implementation class LoginHandleServlet
 */
@WebServlet("/LoginHandleServlet")
public class LoginHandleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginHandleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		Integer total = (Integer) request.getSession().getAttribute("total");
		int temp = 0;
		if (total == null) {
			temp = 1;
			total = 0;
		} else {
			temp = total.intValue() + 1;
		}
		request.getSession().setAttribute("total", total.intValue() + temp);
		try {
			// 1.取参数
			PrintWriter out = response.getWriter();
			StringBuilder builder = new StringBuilder();

			String username = request.getParameter("username");
			String password = request.getParameter("password");

			// 2、检查参数是否有问题
			builder.append("<message>");
			if ("".equals(username) || username == null) {
				builder.append("请输入用户名").append("</message>");
				out.println(builder.toString());
			} else {
				// 检查用户名、密码是否正确
				UserInfo user = new UserInfo();
				boolean result = user.login(username, password);
				UserVO vo = user.getVoByName(username);
				if (vo == null) {
					builder.append("您输入的用户名不存在").append("</message>");
					out.println(builder.toString());
				} else {

					if (result) {
						String school = vo.getSchool();
						builder.append("h&" + school).append("</message>");
						out.println(builder.toString());
						// response.sendRedirect("/UniNote/list.html");
					} else {
						builder.append("用户名或密码错误").append("</message>");
						out.println(builder.toString());
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			// 3.检验操作
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
