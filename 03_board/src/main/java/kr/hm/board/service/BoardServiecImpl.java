package kr.hm.board.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.hm.board.controller.BoardController;
import kr.hm.board.mapper.BoardMapper;
import kr.hm.board.vo.BoardVO;
import kr.hm.board.vo.CommentVO;
import kr.hm.board.vo.FileVO;
import kr.hm.board.vo.LikeVO;
import kr.hm.board.vo.PageVO;

@Service("boardService")
public class BoardServiecImpl implements BoardService {
		
	private static final Logger logger = LoggerFactory.getLogger(BoardServiecImpl.class);

	@Autowired
	private BoardMapper boardMapper = null;
	
	@Override
	public List<BoardVO> boardList(PageVO page) throws Exception {
		
		return boardMapper.boardList(page);
	}

	@Override
	public BoardVO boardDetail(int no) throws Exception {
		BoardVO board = boardMapper.boardDetail(no);
		return board;
	}
	@Override
	public int boardUpdate(BoardVO board) throws Exception {
		return boardMapper.boardUpdate(board);
	}


	@Override
	public int boardDelete(int no) throws Exception {
		return boardMapper.boardDelete(no);
	}

	@Override
	public int boardCount(PageVO page) throws Exception {
		return boardMapper.boardCount(page);
	}

	@Override
	public void viewCount(int no) throws Exception {
		boardMapper.viewCount(no);
	}

	@Override
	@Transactional
	public void boardWrite(BoardVO board, MultipartHttpServletRequest request) throws Exception {
		boardMapper.boardWrite(board);
		
		
		insertFile(request, board.getNo());
		
	}

	
	
	@Override
	public List<CommentVO> commentList(int no) throws Exception {
		return boardMapper.commentList(no);
	}

	@Override
	public int commentWrite(CommentVO comment) throws Exception {
		return boardMapper.commentWrite(comment);
	}

	@Override
	public int commentDelete(int comment_no) throws Exception {
		return boardMapper.commentDelete(comment_no);
	}

	@Override
	public int commentCount(int boardNo) throws Exception {
		return boardMapper.commentCount(boardNo);
	}

	@Override
	public int commentUpdate(CommentVO comment) throws Exception {
		return boardMapper.commentUpdate(comment);
	}

	
	@Override
	public List<CommentVO> replyComment(int no) throws Exception {
		return boardMapper.replyComment(no);
	}

	
	
	
	
	
	
	@Override
	public FileVO selectFile(int no) throws Exception {
		
		return boardMapper.selectFile(no); 
	}

	@Override
	public void insertFile(MultipartHttpServletRequest request, int no) throws Exception {
		FileVO fileVO = new FileVO();
		MultipartFile mFile = request.getFile("attachFile");
		logger.info("ori_name : " + mFile.getOriginalFilename());
		logger.info("name : " + mFile.getName());
		String path = "D:\\upload";
//
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd");
		String datePath = sdf.format(new Date());
		logger.info("datePath : " + datePath);
		String savePath = path + datePath;
		logger.info("savePath : " + savePath);
		File f = new File(savePath);
		logger.info("f : " + f);
		if (!f.exists())
			f.mkdirs();
//
//		//
//		mFile = request.getFile("attachFile");
		logger.info("mFile : " + mFile);
		String oriName = mFile.getOriginalFilename();
		logger.info("oriName : " + oriName);
		if (oriName != null && !oriName.equals("")) {
			// 확장자 처리
			String ext = "";
			// 뒤쪽에 있는 . 의 위치
			int index = oriName.lastIndexOf(".");
			logger.info("index : " + index);
			if (index != -1) {
				// 파일명에서 확장자명(.포함)을 추출
			ext = oriName.substring(index);
			logger.info("ext : " + ext);
			}
			String systemName = "hm" + UUID.randomUUID().toString() + ext;
			logger.info("systemName : " + systemName);
			String pathUp = savePath + "/" + systemName;
			logger.info("pathUp : " + pathUp);
			mFile.transferTo(new File(pathUp));
			fileVO.setOri_name(oriName);
			fileVO.setFile_path(pathUp);
			fileVO.setSystem_name(systemName);
			fileVO.setNo(no);
			boardMapper.insertFile(fileVO);
		}
		

		
	}

	@Override
	public int updateFile(int no) throws Exception {
		return boardMapper.updateFile(no);
	}

	
	
	
	
	@Override
	public int likeCnt(int no) throws Exception {
		return boardMapper.likeCnt(no);
	}

	@Override
	public LikeVO likeSelect(LikeVO like) throws Exception {
		
		return boardMapper.likeSelect(like);
	}

	@Override
	public void deleteLike(LikeVO like) throws Exception {
		boardMapper.deleteLike(like);
	}

	@Override
	public void insertLike(LikeVO like) throws Exception {
		boardMapper.insertLike(like);
	}



}
