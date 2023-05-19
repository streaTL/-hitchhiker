package com.ssafy.enjoytrip.board.model;

public class BoardDto {
	private int articleNo;
	private int hit;
	private String subject;
	private String content;
	private String userId;
	private String date;
	private int type;
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "BoardDto [articleNo=" + articleNo + ", hit=" + hit + ", subject=" + subject + ", content=" + content
				+ ", userId=" + userId + ", date=" + date + ", type=" + type + "]";
	}
}
