package server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");;//告诉浏览器用utf-8 的格式显示网页
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		//接收用户提交的用户名和密码
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		//这里看看有没有接收到
		System.out.println(username+" "+password);
		//这里先简单验证
		if("123".equals(username)&&"123".equals(password)){
			System.out.println("true");
			response.sendRedirect("/UniNote/filelist.html");
		}else{
			//跳回
			//System.out.println("不成功");
			System.out.println("false");
			response.sendRedirect("/UniNote/not-login.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
