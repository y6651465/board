package kr.hm.board.service;

import kr.hm.board.vo.UserVO;

public interface UserService {
	public UserVO loginUser(UserVO user) throws Exception;
	
	public void register(UserVO user) throws Exception;
}
