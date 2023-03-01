import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PostBean;

public class SelectPOST extends HttpServlet {

	// doGetメソッドをオーバーライド
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		String r = req.getParameter("R");
		String s = req.getParameter("S");

		// 初期ページには親コンテンツがないので、スレッドタイトルは要らない
		if (r != null && !r.isEmpty()) {
			try {
				String sql = "SELECT "
						+ "POST_ID, "
						+ "REPLY_TO, "
						+ "NVL(AUTHOR,'NO_NAME') AS AUTHOR, "
						+ "CONTENT, "
						// TO_CHAR関数があるので*は使えない
						+ "TO_CHAR(POSTED_TIME,'YYYY-MM-DD HH:MI:SS') AS TIME, "
						+ "DELETED "
						+ "FROM POST "
						+ "WHERE POST_ID=?";
				System.out.println(sql);

				// Oracleにユーザー名whisper,パスワードbbsで接続
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl"
						, "whisper"
						, "bbs");

				// sql文を実行

				PreparedStatement ps = con.prepareStatement(sql);

				ps.setString(1, r);
				// 結果が必要だからexecuteQuery()を使用
				ResultSet rs = ps.executeQuery();

				rs.next();

				// PostBeanをインスタンス化
				PostBean root = new PostBean(
						rs.getString("POST_ID"),
						rs.getString("REPLY_TO"),
						rs.getString("AUTHOR"),
						rs.getString("CONTENT"),
						rs.getString("TIME"),
						rs.getString("DELETED"),
						"NULL" // スレッドタイトルに返信数はいらないので"NULL"を指定
				);

				// jspにcontentsを渡す
				req.setAttribute("root", root);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		try {
			String sql = "SELECT "
					+ "POST_ID, "
					+ "REPLY_TO, "
					+ "NVL(AUTHOR,'NO_NAME') AS AUTHOR, "
					+ "CONTENT, "
					+ "TO_CHAR(POSTED_TIME,'YYYY-MM-DD HH24:MI:SS') AS TIME, "
					+ "DELETED, "
					+ "(SELECT COUNT(*) FROM POST B WHERE A.POST_ID = B.REPLY_TO ) AS CNT "
					+ "FROM POST A";    // SELECT文
			ArrayList<PostBean> contents = new ArrayList<>();    // Beanを格納するためのリスト

			// Oracleにユーザー名whisper,パスワードbbsで接続
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl"
					, "whisper"
					, "bbs");

			// パラメータ"R"がnullでなければWHERE句を追加
			if (r != null && !r.isEmpty() && !r.equals("null")) {
				sql = sql + " WHERE REPLY_TO=?";
			} else {
				sql = sql + " WHERE REPLY_TO IS NULL";
			}

			if (s != null && !s.isEmpty()) {
				sql = sql + " AND CONTENT LIKE ?";
			}

			sql = sql + " 	ORDER BY POST_ID DESC";

			System.out.println(sql);
			// sql文を実行
			int counter = 1;

			PreparedStatement ps = con.prepareStatement(sql);

			if (r != null && !r.isEmpty() && !r.equals("null")) {
				ps.setString(counter, r);
				counter++;
			}
			if (s != null && !s.isEmpty()) {
				ps.setString(counter, "%" + s + "%");
			}

			ResultSet rs = ps.executeQuery();

			// PostBeanをインスタンス化し、contentsに格納
			while (rs.next()) {
				if (rs.getString("DELETED").equals("0")) {
					contents.add(new PostBean(
							rs.getString("POST_ID"),
							rs.getString("REPLY_TO"),
							rs.getString("AUTHOR"),
							rs.getString("CONTENT"),
							rs.getString("TIME"),
							rs.getString("DELETED"),
							rs.getString("CNT")));
				} else {
					contents.add(new PostBean(
							rs.getString("POST_ID"),
							rs.getString("REPLY_TO"),
							rs.getString("AUTHOR"),
							"DELETED",
							rs.getString("TIME"),
							rs.getString("DELETED"),
							rs.getString("CNT")));
				}
			}

			// jspにcontentsを渡す
			req.setAttribute("contents", contents);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
