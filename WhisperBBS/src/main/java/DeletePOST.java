import db.Database;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletePOST extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String delete = req.getParameter("D");

		try (Database db = new Database()) {
			db.deletePost(delete);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		res.sendRedirect("Home");
	}
}
