package kr.hm.board.mapper;

import java.util.List;

import kr.hm.board.vo.BoardVO;

public interface BoardMapper {
	public List<BoardVO> boardList () throws Exception;
}
