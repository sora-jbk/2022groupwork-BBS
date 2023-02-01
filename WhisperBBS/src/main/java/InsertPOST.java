import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertPOST extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		String name = req.getParameter("name");
		String content = req.getParameter("content");
		String sql = "INSERT INTO POST(AUTHOR, CONTENT) VALUES(?, ?)";
		if(name == null || name.isEmpty()) {
			name = "null";
		}
		if(content == null || content.isEmpty()) {
			name = "null";
		}
		
		System.err.println(name+content);
		System.out.println(sql);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","whisper","bbs");
			con.setAutoCommit(false);
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2, content);
			st.executeUpdate();
			con.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		try {
			res.sendRedirect("Home");
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
	}
}
