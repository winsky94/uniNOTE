package server;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
 

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
 

/**
 * Servlet implementation class Upload
 */
@WebServlet("/UpLoadServlet")
public class UpLoadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String uploadPath = "D:\\web_server_file"; // 上传文件的目录
    private String tempPath = "d:\\web_server_file\\buffer\\"; // 临时文件目录
    File tempPathFile; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpLoadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    	this.doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest request, HttpServletResponse response)
           throws IOException, ServletException {
       try {
           // Create a factory for disk-based file items
           DiskFileItemFactory factory = new DiskFileItemFactory();
 
           // Set factory constraints
           factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
           factory.setRepository(tempPathFile);// 设置缓冲区目录
 
           // Create a new file upload handler
           ServletFileUpload upload = new ServletFileUpload(factory);
 
           // Set overall request size constraint
           upload.setSizeMax(4194304*1024); // 设置最大文件尺寸，这里是4GB

           List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
           Iterator<FileItem> i = items.iterator();
           while (i.hasNext()) {
              FileItem fi = (FileItem) i.next();
              String fileName = fi.getName();
              if (fileName != null) {
                  File fullFile = new File(fi.getName());
                  File savedFile = new File(uploadPath, fullFile.getName());
                  fi.write(savedFile);
              }
           }
           System.out.print("upload succeed");
       } catch (Exception e) {
           // 可以跳转出错页面
           e.printStackTrace();
       }
    }
    public void init() throws ServletException {
        File uploadFile = new File(uploadPath);
        if (!uploadFile.exists()) {
            uploadFile.mkdirs();
        }
        File tempPathFile = new File(tempPath);
         if (!tempPathFile.exists()) {
            tempPathFile.mkdirs();
        }
     }
}
