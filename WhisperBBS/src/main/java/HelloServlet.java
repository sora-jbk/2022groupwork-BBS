import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PostBean;

public class HelloServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		String Resp = "";
		ArrayList<PostBean> contents = new ArrayList<>();
		
		if(req.getParameter("R") != null && !req.getParameter("R").isEmpty()) {
			Resp = req.getParameter("R");
		}
		
		Class.forName();
	}
}
