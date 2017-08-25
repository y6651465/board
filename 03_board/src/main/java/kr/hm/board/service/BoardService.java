package kr.hm.board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.hm.board.vo.BoardVO;
import kr.hm.board.vo.CommentVO;
import kr.hm.board.vo.FileVO;
import kr.hm.board.vo.LikeVO;
import kr.hm.board.vo.PageVO;

public interface BoardService {
	public List<BoardVO> boardList (PageVO page) throws Exception;
	
	public BoardVO boardDetail (int no) throws Exception;
	
	public int boardUpdate (BoardVO board) throws Exception;
	
	public int boardDelete (int no) throws Exception;
	
	public int boardCount (PageVO page) throws Exception;
	
	public void viewCount (int no) throws Exception;
	
	public void boardWrite (BoardVO board, MultipartHttpServletRequest mRequest) throws Exception;
	
	// 댓글
	public List<CommentVO> commentList (int no) throws Exception;
	
	public int commentWrite (CommentVO comment) throws Exception;
	
	public int commentDelete (int comment_no) throws Exception;
	
	public int commentCount (int boardNo) throws Exception;
	
	public int commentUpdate (CommentVO comment) throws Exception;
	
	public List<CommentVO> replyComment (int no) throws Exception;
	
	// 파일
	
	public FileVO selectFile (int no) throws Exception;
	
	public void insertFile (MultipartHttpServletRequest request, int no) throws Exception;
	
	public int updateFile (int no) throws Exception;	
	
	// 좋아요
	
	public int likeCnt (int no) throws Exception;
	
	public LikeVO likeSelect(LikeVO liek) throws Exception;
	
	public void deleteLike(LikeVO like) throws Exception;
	
	public void insertLike(LikeVO like) throws Exception;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
