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
 * Servlet implementation class GetPageNumServlet
 */
@WebServlet("/GetPageNumServlet")
public class GetPageNumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetPageNumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/xml;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String tempS = request.getParameter("school");
		String school = new String(tempS.getBytes("iso-8859-1"), "utf-8");
		String tempD = request.getParameter("department");
		String department = new String(tempD.getBytes("iso-8859-1"), "utf-8");
		String tempC = request.getParameter("course");
		String course = new String(tempC.getBytes("iso-8859-1"), "utf-8");
		DocumentInfo di=new DocumentInfo();
		int pageNum=di.getPageNum(school, department, course);
		
		out.println(pageNum);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
