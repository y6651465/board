package kr.hm.board.vo;

import java.sql.Date;

public class CommentVO {
	private int comment_no;
	private int no;
	private String comment_content;
	private String id;
	private Date comment_reg_date;
	private int parent_comment;
	
	
	public int getParent_comment() {
		return parent_comment;
	}
	public void setParent_comment(int parent_comment) {
		this.parent_comment = parent_comment;
	}
	public Date getComment_reg_date() {
		return comment_reg_date;
	}
	public void setComment_reg_date(Date comment_reg_date) {
		this.comment_reg_date = comment_reg_date;
	}
	public int getComment_no() {
		return comment_no;
	}
	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String coment_content) {
		this.comment_content = coment_content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
