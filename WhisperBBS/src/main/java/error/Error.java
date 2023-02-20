package error;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ErrorServlet")
public class Error extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		ServletContext application = this.getServletContext();
		
		System.out.println("Exception was throwed");
		System.out.println(new java.util.Date());
		System.out.println(System.getProperty("line.separator"));
		System.out.println(request.getAttribute("jakarta.servlet.error.request_uri"));
		System.out.println(System.getProperty("line.separator"));
		System.out.println(request.getAttribute("jakarta.servlet.error.message"));
		
		
		response.sendRedirect(request.getContextPath() + "/Error");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		doGet(request, response);
	}
}
