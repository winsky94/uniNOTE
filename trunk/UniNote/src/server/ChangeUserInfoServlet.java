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
 * Servlet implementation class ChangeUserInfoServlet
 */
@WebServlet("/ChangeUserInfoServlet")
public class ChangeUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeUserInfoServlet() {
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
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String flag = request.getParameter("flag");
		if (flag.equals("userinfo")) {
			// 修改用户信息
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
			try {
				// 1.取参数
				PrintWriter out = response.getWriter();
				StringBuilder builder = new StringBuilder();

				String nickname = request.getParameter("nickname");
				String email = request.getParameter("email");
				String school = request.getParameter("school");
				String phoneNumber = request.getParameter("phonenumber");
				
				builder.append("<message>");
				
				// 2、检查参数是否有问题
				if (email==null||email.equals("")) {
					builder.append("请输入邮箱").append("</message>");
				}else if (school==null||school.equals("")) {
					builder.append("请输入学校").append("</message>");
				}else if (phoneNumber==null||phoneNumber.equals("")) {
					builder.append("请输入联系电话").append("</message>");
				} else {
					UserInfo ui = new UserInfo();
					String password = ui.getPassword(nickname);
					if (password != null) {
						UserVO vo = new UserVO(nickname, password, email, school,
								phoneNumber);
						boolean result=ui.modify(vo, nickname);
						if (result) {
							builder.append("h").append("</message>");
						} else {
							builder.append("修改用户信息失败").append("</message>");
						}
					}
				}
				out.println(builder.toString());
			} catch (Exception e) {
				e.printStackTrace();
				// 3.检验操作
			}
			
		} else if (flag.equals("password")) {
			// 修改用户密码
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
			try {
				// 1.取参数
				PrintWriter out = response.getWriter();
				StringBuilder builder = new StringBuilder();

				String nickname = request.getParameter("nickname");
				String oldPassword = request.getParameter("password");
				String newPassword = request.getParameter("newpassword");
				String confirmpassword = request
						.getParameter("confirmpassword");

				builder.append("<message>");
				UserInfo ui = new UserInfo();
				UserVO oldVo = ui.getVoByName(nickname);
				String pw = oldVo.getPassword();
				String email = oldVo.getEmail();
				String school = oldVo.getSchool();
				String phoneNumber = oldVo.getPhoneNumber();

				UserVO vo = new UserVO(nickname, pw, email, school, phoneNumber);
				// 2、检查参数是否有问题
				if (!pw.equals(oldPassword)) {
					builder.append("当前密码错误").append("</message>");
				} else if (newPassword == null||newPassword.equals("")) {
					builder.append("请输入新密码").append("</message>");
				} else {
					if (!newPassword.equals(confirmpassword)) {
						builder.append("两次输入的密码不一样").append("</message>");
					} else {
						vo.setPassword(newPassword);
						boolean result = ui.modify(vo, nickname);
						if (result) {
							builder.append("h").append("</message>");
						} else {
							builder.append("修改密码失败").append("</message>");
						}
					}
				}
				out.println(builder.toString());

			} catch (Exception e) {
				e.printStackTrace();
				// 3.检验操作
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
