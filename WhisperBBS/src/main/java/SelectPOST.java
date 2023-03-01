import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Database;

public class SelectPOST extends HttpServlet {

	// doGetメソッドをオーバーライド
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		String r = req.getParameter("R");
		String s = req.getParameter("S");

		Integer postId;

		try {
			postId = Integer.parseInt(r);
		} catch (NumberFormatException e) {
			postId = null;
		}

		try (Database db = new Database()) {
			if (postId != null) {
				req.setAttribute("root", db.getPost(postId));
			}

			req.setAttribute("contents", db.getPosts(postId, s));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		try {
			// 移動先をPrintに設定
			RequestDispatcher rd = req.getRequestDispatcher("Print");
			// ページを移動
			rd.forward(req, res);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
