import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertPOST extends HttpServlet {
	
//	doPostメソッドをオーバーライド
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		
		//パラメータnameを取得
		String name = req.getParameter("name");
		//パラメータcontentを取得
		String content = req.getParameter("content");
		//InsertSQL文
		String sql = "INSERT INTO POST(AUTHOR, CONTENT) VALUES(?, ?)";
		
		//nameが空のとき"null"に置き換え
		if(name == null || name.isEmpty()) {
			name = "null";
		}
		if(content == null || content.isEmpty()) {
			name = "null";
		}
		
		
		try {
			
			//Oracleに接続
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","whisper","bbs");
			con.setAutoCommit(false);
			PreparedStatement st = con.prepareStatement(sql);
			
			//バインド変数に代入
			st.setString(1, name);
			st.setString(2, content);
			
			//SQLを実行
			st.executeUpdate();
			
			//DBをコミット
			con.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		try {
			
			//Homeに画面を遷移
			res.sendRedirect("Home");
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
	}
}
