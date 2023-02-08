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
	
//	doGetメソッドをオーバーライド
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		

		String sql = "SELECT * FROM POST";	//SELECT文
		ArrayList<PostBean> contents = new ArrayList<>();	//Beanを格納するためのリスト
		
		
		try {
			//Oracleにユーザー名whiser,パスワードbbsで接続
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl"
					,"whisper"
					,"bbs");
			
			
			//パラメータ"R"がnullでなければWHERE句を追加
			if(req.getParameter("R") != null && !req.getParameter("R").isEmpty()) {
				sql = sql + " WHERE REPLY_TO='" + req.getParameter("R") +"'";
			}else {
				sql = sql + " WHERE REPLY_TO IS NULL";
			}
			
			
			
			//sql文を実行
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			
			
			//PostBeanをインスタンス化し、contentsに格納
			while(rs.next()) {
				contents.add(new PostBean(
						rs.getString("POST_ID"),
						rs.getString("REPLY_TO"),
						rs.getString("AUTHOR"),
						rs.getString("CONTENT"),
						rs.getString("POSTED_TIME")));
			}
			
			//jspにcontentsを渡す
			req.setAttribute("contents", contents);
			
			//移動先をPrintに設定
			RequestDispatcher rd = req.getRequestDispatcher("Print");
			
			
			//ページを移動
			rd.forward(req, res);
			
		}catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
	}
}
