package bean;

public class PostBean {

	private String postId;
	private String replyTo;
	private String author;
	private String content;
	private String postedTime;
	private String deleted;
	private String resNum;

	// コンストラクタ
	public PostBean(String postId,
					String replyTo,
					String author,
					String content,
					String postedTime,
					String deleted,
					String resNum) {
		this.postId = postId;
		this.replyTo = replyTo;
		this.author = escapeHtml(author);
		this.content = escapeHtml(content);
		this.postedTime = postedTime;
		this.deleted = deleted;
		this.resNum = resNum;
	}

	public String getPostId() {
		return postId;
	}

	public String getReplyTo() {
		return replyTo;
	}

	public String getAuthor() {
		return author;
	}

	public String getContent() {
		return content;
	}

	public String getPostedTime() {
		return postedTime;
	}

	public String getDeleted() {
		return deleted;
	}

	public String getResNum() {
		return resNum;
	}

	private String escapeHtml(String s) {
		if (s == null) {
			return null;
		}

		return s.replace("&", "&amp;")
				.replace("<", "&lt;")
				.replace(">", "&gt;")
				.replace(" ", "&nbsp;")
				.replaceAll("\"", "&quot;")
				.replace("'", "&#39;");
	}
}
