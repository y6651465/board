package kr.hm.board.mapper;

import kr.hm.board.vo.UserVO;

public interface UserMapper {
	public UserVO loginUser(UserVO user) throws Exception;
	
	public void register(UserVO user) throws Exception;
	
}
