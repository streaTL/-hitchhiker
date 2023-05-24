package com.ssafy.enjoytrip.board.model;

public class CommentDto {
	int articleNo;

	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}

	String userId;
	int commentId;
	String createTime;
	String content;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "CommentDto [articleNo=" + articleNo + ", userId=" + userId + ", commentId=" + commentId
				+ ", createTime=" + createTime + ", content=" + content + "]";
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getArticleNo() {
		return articleNo;
	}

}
