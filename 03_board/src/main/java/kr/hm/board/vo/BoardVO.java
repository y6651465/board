package kr.hm.board.vo;

import java.sql.Date;

public class BoardVO {
	private int no;
	private String title;
	private String content;
	private String id;
	private Date reg_date;
	private int count;
	private int view_count;
	private int like_count;
	
	public int getLike_count() {
		return like_count;
	}
	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}
	public int getView_count() {
		return view_count;
	}
	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	public Date getReg_date() {
		return reg_date;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "BoardVO [no=" + no + ", title=" + title + ", content=" + content + ", id=" + id + ", reg_date="
				+ reg_date + ", count=" + count + ", view_count=" + view_count + "]";
	}
	
}
