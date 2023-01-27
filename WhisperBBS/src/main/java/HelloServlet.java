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

public class HelloServlet extends HttpServlet implements db.DBInfo{
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		String sql = "SELECT * FROM "+ TableName +" WHERE ?";
		String Resp = "";
		ArrayList<PostBean> contents = new ArrayList<>();
		
		
		try {
			Class.forName(ClassName);
			
			Connection con = DriverManager.getConnection(ConStr,User,Pass);
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			
			if(req.getParameter("R") != null && !req.getParameter("R").isEmpty()) {
				ps.setString(1, req.getParameter("R").toString());
			}else {
				ps.setString(2, "TRUE");
			}
			
			
			ResultSet rs = ps.executeQuery();
			
			
			
			while(rs.next()) {
				contents.add(new PostBean(
						rs.getString("POST_ID"),
						rs.getString("REPLY_TO"),
						rs.getString("AUTHOR"),
						rs.getString("CONTENT"),
						rs.getString("POSTED_TIME")));
			}
			
			
			req.setAttribute("contents", contents);
			
			RequestDispatcher rd = req.getRequestDispatcher("Index");
			
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
