package kr.hm.board.mapper;

import java.util.List;


import kr.hm.board.vo.BoardVO;
import kr.hm.board.vo.CommentVO;
import kr.hm.board.vo.FileVO;
import kr.hm.board.vo.LikeVO;
import kr.hm.board.vo.PageVO;

public interface BoardMapper {
	public List<BoardVO> boardList (PageVO page) throws Exception;
	
	public BoardVO boardDetail (int no) throws Exception;
	
	public int boardUpdate (BoardVO board) throws Exception;
	
	public int boardDelete (int no) throws Exception;
	
	public int boardCount (PageVO page) throws Exception;
	
	public void viewCount (int no) throws Exception;
	
	public int boardWrite (BoardVO board) throws Exception;
	// 추천
	
	public int likeCnt (int no) throws Exception;
	
	public LikeVO likeSelect(LikeVO like) throws Exception;
	
	public void deleteLike(LikeVO like) throws Exception;
	
	public void insertLike(LikeVO like) throws Exception;
	
	
	
	
	// 댓글
	public List<CommentVO> commentList (int no) throws Exception; 
	
	public int commentWrite (CommentVO comment) throws Exception;
	
	public int commentDelete (int comment_no) throws Exception;
	
	public int commentCount (int boardNo) throws Exception;
	
	public int commentUpdate (CommentVO comment) throws Exception;
	
	public List<CommentVO> replyComment (int no) throws Exception;
	// 파일
	
	public FileVO selectFile (int no) throws Exception;
	
	public void insertFile (FileVO file) throws Exception;
	
	public int updateFile (int no) throws Exception;	
}

