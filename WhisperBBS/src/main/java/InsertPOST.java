import db.Database;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertPOST extends HttpServlet {

	// doPostメソッドをオーバーライド
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// パラメータnameを取得
		String name = req.getParameter("name");
		// パラメータcontentを取得
		String content = req.getParameter("content");

		String reply = req.getParameter("R");

		System.out.println(reply);

		// nameが空のとき"null"に置き換え
		if (name == null || name.isEmpty()) {
			name = null;
		}
		if (content == null || content.isEmpty()) {
			name = "null";
		}
		if (reply == null || reply.isEmpty()) {
			reply = "";
		}
		if (reply.toLowerCase().equals("null")) {
			reply = "";
		}

		try (Database db = new Database()) {
			db.insertPost(name, content, reply);
		} catch (Exception e) {
			e.printStackTrace();
			res.sendRedirect("/Error");
		}

		// Homeに画面を遷移
		res.sendRedirect("Home?R=" + reply);
	}
}
