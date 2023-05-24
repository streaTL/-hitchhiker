package com.ssafy.enjoytrip.board.model;

public class CommentDto {
	String articleNo;
	String userId;
	String commentId;
	String createTime;
	String content;

	public String getUserId() {
		return userId;
	}

	public String getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
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

}
