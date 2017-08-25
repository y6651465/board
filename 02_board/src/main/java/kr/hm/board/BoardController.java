package kr.hm.board;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.hm.board.service.BoardService;
import kr.hm.board.vo.BoardVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping("/board/boardList.do")
	public ModelAndView boardList() throws Exception {
		logger.info("boardListController");
		
		List<BoardVO> list = boardService.boardList();
		ModelAndView mv = new ModelAndView("boardList");
		mv.addObject(list);
		return mv;
	}
	
}
