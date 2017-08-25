package kr.hm.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mysql.jdbc.StringUtils;

import kr.hm.board.service.BoardService;
import kr.hm.board.vo.BoardVO;
import kr.hm.board.vo.CommentVO;
import kr.hm.board.vo.FileVO;
import kr.hm.board.vo.LikeVO;
import kr.hm.board.vo.PageResultVO;
import kr.hm.board.vo.PageVO;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;

	@RequestMapping("/boardList.do")
	public void boardList(PageVO page, Model model, @ModelAttribute("whatSearch") String whatSearch,
			@ModelAttribute("wantSearch") String wantSearch) throws Exception {
		// logger.info("pageNO : "+ page.getPageNo());

		int count = boardService.boardCount(page);
		int pageNo = page.getPageNo();
		List<BoardVO> list = boardService.boardList(page);
		model.addAttribute(whatSearch);
		model.addAttribute(wantSearch);
		model.addAttribute("list", list);
		model.addAttribute("pageResult", new PageResultVO(pageNo, count));
	}

	@RequestMapping("/boardDetail.do")

	public String boardDetail(HttpServletRequest req, HttpServletResponse res, int no, Model model, int pageNo,
			String whatSearch, String wantSearch, String view) throws Exception {
		BoardVO board = boardService.boardDetail(no);
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("userId");
		Cookie cookies[] = req.getCookies();
		Map<String, Object> map = new HashMap<>();
		if (req.getCookies() != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie obj = cookies[i];
				map.put(obj.getName(), obj.getValue());
			}
		}
		
		// 저장된 쿠키중에 read_count 만 불러오기
	    String readCount = (String) map.get("read_count"+id);
	     // 저장될 새로운 쿠키값 생성
	    String newReadCount = "|"+no;

	    // 저장된 쿠키에 새로운 쿠키값이 존재하는 지 검사
	    if ( StringUtils.indexOfIgnoreCase(readCount, newReadCount) == -1 ) {
	          // 없을 경우 쿠키 생성
	          Cookie cookie = new Cookie("read_count"+id, readCount+newReadCount);
	         
	          res.addCookie(cookie);
	          boardService.viewCount(no);
	    }
		
		
		FileVO file = new FileVO();
		file = boardService.selectFile(no);
		model.addAttribute("file", file);
		model.addAttribute("page", pageNo);
		model.addAttribute("board", board);
		model.addAttribute("whatSearch", whatSearch);
		model.addAttribute("wantSearch", wantSearch);
		logger.info("----------디테일 나감");
		return "board/boardDetail";
	}

	@RequestMapping("/boardUpdateForm.do")
	public void boardUpdateForm(int no, Model model, int pageNo, String whatSearch, String wantSearch)
			throws Exception {
		model.addAttribute("board", boardService.boardDetail(no));
		model.addAttribute("whatSearch", whatSearch);
		model.addAttribute("wantSearch", wantSearch);
		model.addAttribute("page", pageNo);
	}

	@RequestMapping("/update.do")
	public void boardUpdate(Model model, BoardVO board, @ModelAttribute("no") int no, int pageNo, String whatSearch,
			String wantSearch) throws Exception {
		logger.info("boardUpdate");
		model.addAttribute("whatSearch", whatSearch);
		model.addAttribute("wantSearch", wantSearch);
		model.addAttribute("page", pageNo);
		// int result = boardService.boardUpdate(board);
		// logger.info("boardUpdate : " + result + " rows");

	}

	@RequestMapping("/delete.do")
	public void boardDelete(int no) throws Exception {
		// logger.info("boardDelete");
		boardService.boardDelete(no);
	}

	@RequestMapping("/writeForm.do")
	public String boardWriteForm() throws Exception {
		return "board/writeForm";
	}

	@RequestMapping(value = "/write.do")
	public void boardWrite(MultipartHttpServletRequest request) throws Exception {
		BoardVO board = new BoardVO();

		logger.info("id : " + request.getParameter("id"));
		logger.info("title : " + request.getParameter("title"));
		logger.info("content : " + request.getParameter("content"));
		board.setId(request.getParameter("id"));
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		boardService.boardWrite(board, request);

	}

	// --------------------------- 댓글
	@RequestMapping("/commentList.do")
	@ResponseBody
	public List<CommentVO> commentList(int no) throws Exception {
		return boardService.commentList(no);
	}

	@RequestMapping("/commentWrite.do")
	@ResponseBody
	public void commentWrite(CommentVO comment) throws Exception {
		logger.info("commentno : " +comment.getNo() );
		boardService.commentWrite(comment);
	}

	@RequestMapping("/commentDelete.do")
	@ResponseBody
	public void commentDelete(int comment_no) throws Exception {
		boardService.commentDelete(comment_no);
	}

	@RequestMapping("/commentUpdate.do")
	@ResponseBody
	public void commentUpdate(CommentVO comment) throws Exception {
		boardService.commentUpdate(comment);
	}

	@RequestMapping("/commentCount.json")
	@ResponseBody
	public String commentCount(int boardNo) throws Exception {
		return String.valueOf(boardService.commentCount(boardNo));
	}
	@RequestMapping("/replyComment.do")
	@ResponseBody
	public List<CommentVO> replyComment(int no) throws Exception{
		return boardService.replyComment(no);
	}
	// 파일

	@RequestMapping("/download.do")
	public void fileDownload(HttpServletRequest req, HttpServletResponse res, int no) throws Exception {
		logger.info("----------파일 다운로드 들어옴");
		FileVO fileVO = new FileVO();
		fileVO = boardService.selectFile(no);
		String path = fileVO.getFile_path();
		logger.info("path : " + path);

		//
		String oName = fileVO.getOri_name();
		logger.info("oName : " + oName);
		res.setContentType("application/octet-stream");
		oName = new String(oName.getBytes("utf-8"), "8859_1");
		res.setHeader("Content-Disposition", "attachment;filename=\"" + oName + "\"");
		OutputStream out = res.getOutputStream();
		FileInputStream fis = new FileInputStream(path + File.separator);

		int n = 0;
		byte[] b = new byte[512];
		while ((n = fis.read(b)) != -1) {
			out.write(b, 0, n);
		}
		fis.close();
		out.close();
		logger.info("----------파일 다운로드 나감");

	}








	// 좋아요
	@RequestMapping("/like.do")
	@ResponseBody
	public String insertLike(LikeVO like, @ModelAttribute("no") int no) throws Exception{
//		LikeVO dbLike = new LikeVO();
		logger.info("id : "+like.getId());
		logger.info("no : "+like.getNo());
//		dbLike = boardService.likeSelect(like);
		
//		Boolean fla=dbLike != like;
		logger.info("이프문 1");
//		logger.info("dbid : "+dbLike.getId());
//		logger.info("dbno : "+dbLike.getNo());
//		logger.info("dbno : "+dbLike.getLike_no());
//		logger.info("id : "+dbLike.getId());
//		logger.info("no : "+dbLike.getNo());
//		logger.info("Likeno : "+dbLike.getLike_no());
//		logger.info(" "+fla);
		if(like != null) {
				like.setLike_no("y");
				boardService.insertLike(like);	
		}
//		else{
//			boardService.deleteLike(like);
//		}
		
		logger.info("no : " + no);
		logger.info(String.valueOf(boardService.likeCnt(no)));
		return String.valueOf(boardService.likeCnt(no));
	}

	@RequestMapping("/likeCount.do")
	@ResponseBody
	public String likeCount(int no) throws Exception{
		logger.info("like Count 들어옴");
		logger.info("like Count 값 : "+boardService.likeCnt(no));
		return String.valueOf(boardService.likeCnt(no));
		
	}







}
