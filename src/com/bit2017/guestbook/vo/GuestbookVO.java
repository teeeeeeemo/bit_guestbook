package com.bit2017.guestbook.vo;

public class GuestbookVO {

	private Long no;
	private String name;
	private String password;
	private String content;
	private String regDate;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "GuestbookVO [no=" + no + ", name=" + name + ", content=" + content + ", regDate=" + regDate + "]";
	}

}
