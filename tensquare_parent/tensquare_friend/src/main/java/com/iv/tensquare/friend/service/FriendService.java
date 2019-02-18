package com.iv.tensquare.friend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iv.tensquare.friend.dao.FriendDao;
import com.iv.tensquare.friend.pojo.Friend;

@Service
@Transactional
public class FriendService {

	@Autowired
	private FriendDao friendDao;
	
	public int addFriend(String userid, String friendid) {
		//先判断userid 到friendid 是否有数据，有数据表示重复添加，返回0
		Friend friend = friendDao.findByUseridAndFriendid(userid, friendid);
		if( friend != null ) {
			return 0;
		}
		
		//直接添加好友，userid 到 friendid 的type为0
		friend = new Friend();
		friend.setUserid(userid);
		friend.setFriendid(friendid);
		friend.setIslike("0");
		friendDao.save(friend);
		
		//判断从friend 到 userid 是否有数据，有，将双方的状态都改为“1”
		if(friendDao.findByUseridAndFriendid(friendid, userid) != null) {
			//把双方的islike 都改为1
			friendDao.updateIslike("1", userid, friendid );
			friendDao.updateIslike("1", friendid, userid);
		}
		return 1;
	}

}
