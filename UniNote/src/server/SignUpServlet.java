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

	/**
	 * @see HttpServlet#HttpServlet()
	 */
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
//		response.setCharacterEncoding("utf-8");
//		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();  
		StringBuilder builder = new StringBuilder();  
		 
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String school = request.getParameter("school");
		String phoneNumber = request.getParameter("phonenumber");

		// System.out.println(nickname);
		boolean isfull=true;
		
		if ("".equals(nickname) || nickname == null) {
			isfull=false;
			builder.append("<message>昵称必须输入</message>");
		}
//		else{
//			builder.append("<message></message>");
//		}
//		if ("".equals(password) || password == null) {
//			isfull=false;
//			builder.append("<message>密码必须输入</message>");
//		} 
//		else{
//			builder.append("<message></message>");
//		}
//		if ("".equals(email) || email == null) {
//			isfull=false;
//			builder.append("<message>邮箱必须输入</message>");
//		}
//		else{
//			builder.append("<message></message>");
//		}
        if(isfull==true){
			UserVO vo = new UserVO(nickname, password, email, school,
					phoneNumber);
			UserInfo user = new UserInfo();
			boolean result = user.add(vo);

			if (result) {
				response.sendRedirect("/UniNote/list.html");
			} else {
				// 提示注册失败
				builder.delete( 0, builder.length() );
				builder.append("<message>该昵称已被注册</message>");
//				builder.append("<message></message>");
//				builder.append("<message></message>");
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
