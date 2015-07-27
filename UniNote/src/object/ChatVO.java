package object;

public class ChatVO {
	private int documentID;// 文章编号
	private String nickName;// 评论者
	private String date;// 评论日期
	private String content;// 评论内容

	public ChatVO(int documentID, String nickName, String date, String content) {
		super();
		this.documentID = documentID;
		this.nickName = nickName;
		this.date = date;
		this.content = content;
	}

	public int getDocumentID() {
		return documentID;
	}

	public void setDocumentID(int documentID) {
		this.documentID = documentID;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
