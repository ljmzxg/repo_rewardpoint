package com.iv.tensquare.friend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iv.tensquare.friend.dao.FriendDao;

@Service
@Transactional
public class FriendService {

	@Autowired
	private FriendDao friendDao;
	
	public int addFriend(String userid, String friendid) {
		//先判断userid 到friendid 是否有数据，有数据表示重复添加，返回0
		
		//直接添加好友，userid 到 friendid 的type为0
		
		//判断从friend 到 userid 是否有数据，有，将双方的状态都改为“1”
		
		return 0;
	}

}
