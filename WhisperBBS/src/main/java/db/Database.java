package db;

import bean.PostBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database implements AutoCloseable {

	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";

	private static final String DB_USER = "whisper";

	private static final String DB_PASS = "bbs";

	private final Connection connection;

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public Database() throws SQLException {
		connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
	}

	@Override
	public void close() throws Exception {
		if (connection != null) {
			connection.close();
		}
	}

	/**
	 * 投稿の取得
	 *
	 * @param postId 投稿番号
	 * @return 投稿
	 */
	public PostBean getPost(int postId) throws SQLException {
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

		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, postId);
			// 結果が必要だからexecuteQuery()を使用
			ResultSet rs = ps.executeQuery();

			rs.next();

			// PostBeanをインスタンス化
			PostBean post = new PostBean(
					rs.getString("POST_ID"),
					rs.getString("REPLY_TO"),
					rs.getString("AUTHOR"),
					rs.getString("CONTENT"),
					rs.getString("TIME"),
					rs.getString("DELETED"),
					"NULL" // スレッドタイトルに返信数はいらないので"NULL"を指定
			);

			return post;
		}
	}

	/**
	 * 投稿リストの取得
	 *
	 * @param postId 投稿番号
	 * @param query  検索ワード
	 * @return 投稿リスト
	 */
	public List<PostBean> getPosts(Integer postId, String query) throws SQLException {
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

		// パラメータ"R"がnullでなければWHERE句を追加
		if (postId != null) {
			sql = sql + " WHERE REPLY_TO=?";
		} else {
			sql = sql + " WHERE REPLY_TO IS NULL";
		}

		if (query != null && !query.isEmpty()) {
			sql = sql + " AND CONTENT LIKE ?";
		}

		sql = sql + " 	ORDER BY POST_ID DESC";

		// sql文を実行
		int counter = 1;

		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			if (postId != null) {
				ps.setInt(counter, postId);
				counter++;
			}
			if (query != null && !query.isEmpty()) {
				ps.setString(counter, "%" + query + "%");
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
		}

		return contents;
	}

	/**
	 * 投稿の追加
	 *
	 * @param author  投稿者
	 * @param content 投稿内容
	 * @param replyTo 返信先
	 */
	public void insertPost(String author, String content, String replyTo) throws SQLException {
		String sql = "INSERT INTO POST(AUTHOR, CONTENT, REPLY_TO) VALUES(?, ?, ?)";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, author);
			ps.setString(2, content);
			ps.setString(3, replyTo);
			ps.executeUpdate();
		}
	}

	/**
	 * 投稿の削除
	 *
	 * @param postId 投稿番号
	 */
	public void deletePost(String postId) throws SQLException {
		String sql = "UPDATE POST SET DELETED = 1 WHERE POST_ID = ?";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, postId);
			ps.executeUpdate();
		}
	}
}
