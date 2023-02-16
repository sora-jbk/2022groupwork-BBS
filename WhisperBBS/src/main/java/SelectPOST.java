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
		
		
		try {
			if(req.getParameter("R") != null && !req.getParameter("R").isEmpty()) {
				String r = req.getParameter("R");
				String sql = "SELECT "
						+ "POST_ID, "
						+ "REPLY_TO, "
						+ "AUTHOR, "
						+ "CONTENT, "
						+ "POSTED_TIME, "
						+ "DELETED "
						+ "FROM POST "
						+ "WHERE POST_ID=" + r;
				System.out.println(sql);
				//Oracleにユーザー名whiser,パスワードbbsで接続
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl"
						,"whisper"
						,"bbs");
				
				
				
				//sql文を実行
				
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				
				rs.next();
				
				//PostBeanをインスタンス化
				PostBean root = new PostBean(
						rs.getString("POST_ID"),
						rs.getString("REPLY_TO"),
						rs.getString("AUTHOR"),
						rs.getString("CONTENT"),
						rs.getString("POSTED_TIME"),
						rs.getString("DELETED"),
						"NULL"
						);
				
				//jspにcontentsを渡す
				req.setAttribute("root", root);
			}
			
		}catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		
		try {
			String sql = "SELECT "
					+ "POST_ID, "
					+ "REPLY_TO, "
					+ "AUTHOR, CONTENT, "
					+ "POSTED_TIME, "
					+ "DELETED, "
					+ "(SELECT COUNT(*) FROM POST B WHERE A.POST_ID = B.REPLY_TO ) AS CNT "
					+ "FROM POST A";	//SELECT文
			ArrayList<PostBean> contents = new ArrayList<>();	//Beanを格納するためのリスト
			
			//Oracleにユーザー名whiser,パスワードbbsで接続
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl"
					,"whisper"
					,"bbs");
			
			
			//パラメータ"R"がnullでなければWHERE句を追加
			if((req.getParameter("R") != null && !req.getParameter("R").isEmpty()) && !req.getParameter("R").toUpperCase().equals("NULL")) {
				sql = sql + " WHERE REPLY_TO='" + req.getParameter("R") +"'";
			}else {
				sql = sql + " WHERE REPLY_TO IS NULL";
			}
			
			if (req.getParameter("S") != null && !req.getParameter("S").isEmpty()) {
				sql = sql + " AND CONTENT LIKE '%" + req.getParameter("S") + "%'";
			}
			
			System.out.println(sql);
			//sql文を実行
			
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			
			
			//PostBeanをインスタンス化し、contentsに格納
			while(rs.next()) {
				if (rs.getString("DELETED").equals("0")) {
					contents.add(new PostBean(
							rs.getString("POST_ID"),
							rs.getString("REPLY_TO"),
							rs.getString("AUTHOR"),
							rs.getString("CONTENT").replace("\n", "<br>"),
							rs.getString("POSTED_TIME"),
							rs.getString("DELETED"),
							rs.getString("CNT")));
				} else {
					contents.add(new PostBean(
							rs.getString("POST_ID"),
							rs.getString("REPLY_TO"),
							rs.getString("AUTHOR"),
							"DELETED",
							rs.getString("POSTED_TIME"),
							rs.getString("DELETED"),
							rs.getString("CNT")));
				}
			}
			
			//jspにcontentsを渡す
			req.setAttribute("contents", contents);
			
			
		}catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		try {
			//移動先をPrintに設定
		RequestDispatcher rd = req.getRequestDispatcher("Print");
		//ページを移動
		rd.forward(req, res); 
	}	catch (ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
