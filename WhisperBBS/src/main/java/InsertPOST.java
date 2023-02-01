import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertPost extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		String name = req.getParameter("name");
		String content = req.getParameter("content");
		String sql = "INSERT INTO POST(AUTHOR, CONTENT) VALUES(?, ?)";
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2, content);
			st.executeUpdate(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		RequestDispatcher rd = req.getRequestDispatcher("Home");
		
		try {
			rd.forward(req, res);
		} catch (ServletException | IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
	}
}
