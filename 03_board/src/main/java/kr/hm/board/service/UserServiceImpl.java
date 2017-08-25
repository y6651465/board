package kr.hm.board.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hm.board.mapper.UserMapper;
import kr.hm.board.vo.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public UserVO loginUser(UserVO user) throws Exception {
		
		return userMapper.loginUser(user);
	
	}

	@Override
	public void register(UserVO user) throws Exception {
		userMapper.register(user);
	}

}