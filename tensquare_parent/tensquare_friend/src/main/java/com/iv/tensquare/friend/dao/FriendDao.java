package com.iv.tensquare.friend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iv.tensquare.friend.pojo.Friend;

public interface FriendDao extends JpaRepository<Friend, String> {

	public Friend findByUseridAndFriendid(String userid, String friendid);
	
}
