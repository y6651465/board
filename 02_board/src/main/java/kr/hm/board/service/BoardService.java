package kr.hm.board.service;

import java.util.List;

import kr.hm.board.vo.BoardVO;

public interface BoardService {
	public List<BoardVO> boardList () throws Exception;
}
