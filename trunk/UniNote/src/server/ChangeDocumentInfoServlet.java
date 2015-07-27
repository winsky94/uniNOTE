package server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.DocumentInfo;

/**
 * Servlet implementation class ChangeDocumentInfoServlet
 */
@WebServlet("/ChangeDocumentInfoServlet")
public class ChangeDocumentInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeDocumentInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

				response.setContentType("text/html;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				request.setCharacterEncoding("utf-8");

				String profile = request.getParameter("profile");
				String tag = request.getParameter("tag");
                String did=request.getParameter("id");
                
                DocumentInfo di=new DocumentInfo();
                di.modify(Integer.parseInt(did), profile, tag);		
                
                request.getRequestDispatcher("/manage_file.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
