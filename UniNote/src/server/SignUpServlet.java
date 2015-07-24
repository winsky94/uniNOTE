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
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SignUpServlet() {
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

		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		StringBuilder builder = new StringBuilder();

		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String school = request.getParameter("school");
		String phoneNumber = request.getParameter("phonenumber");

		// System.out.println(nickname);

		builder.append("<message>");
		if ("".equals(nickname) || nickname == null || "".equals(password)
				|| password == null || "".equals(email) || email == null
				|| "".equals(school) || school == null
				|| "".equals(phoneNumber) || phoneNumber == null) {
			builder.append("请输入完整信息").append("</message>");
		} else {
			// 检查用户名、密码是否正确
			UserInfo user = new UserInfo();
			// 新注册用户默认有10个积分
			UserVO vo = new UserVO(nickname, password, email, school,
					phoneNumber, 10);
			boolean result = user.add(vo);
			if (result) {
				builder.append("h").append("</message>");
			} else {
				builder.append("该昵称已被人注册").append("</message>");
			}
		}
		out.println(builder.toString());

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
