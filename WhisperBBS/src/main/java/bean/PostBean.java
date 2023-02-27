package bean;

public class PostBean {
	private String post_id;
	private String reply_to;
	private String author;
	private String content;
	private String posted_time;
	private String deleted;
	private String resNum;
	
//	コンストラクタ
	public PostBean(String post_id, 
			String reply_to, 
			String author, 
			String content, 
			String posted_time,
			String deleted,
			String resNum) {
		
		this.post_id = post_id;
		this.reply_to = reply_to;
		if(author != null) {
			this.author = author
					.replace("&", "&amp;")
					.replace("<", "&lt;")
					.replace(">", "&gt;")
					.replace(" ", "&nbsp;")
					.replaceAll("\"", "&quot;")
					.replace("'", "&#39;");
		}
		if(content != null) {
			this.content = content
					.replace("&", "&amp;")
					.replace("<", "&lt;")
					.replace(">", "&gt;")
					.replace(" ", "&nbsp;")
					.replaceAll("\"", "&quot;")
					.replace("'", "&#39;");
		}
		this.posted_time = posted_time;
		this.deleted = deleted;
		this.resNum = resNum;
		
	}
	
	public String getPost_id() {
		return post_id;
	}
	public String getReply_to() {
		return reply_to;
	}
	public String getAuthor() {
		return author;
	}
	public String getContent() {
		return content;
	}
	public String getPosted_time() {
		return posted_time;
	}
	public String getDeleted() {
		return deleted;
	}
	public String getResNum() {
		return resNum;
	}

}
