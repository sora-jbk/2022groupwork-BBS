import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletePOST extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		String delete = req.getParameter("D");
		
		String sql = "UPDATE POST "
				+ "SET DELETED=1"
				+ " WHERE POST_ID = ?";
		
		
		try {
			
			//Oracleに接続
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","whisper","bbs");
			con.setAutoCommit(false);
			PreparedStatement st = con.prepareStatement(sql);
			
			//バインド変数に代入
			st.setString(1, delete);
			//SQLを実行
			st.executeUpdate();
			
			//DBをコミット
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
