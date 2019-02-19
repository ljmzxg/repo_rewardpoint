package com.iv.tensquare.friend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iv.tensquare.friend.pojo.NoFriend;

public interface NoFriendDao extends JpaRepository<NoFriend, String> {
	NoFriend findByUseridAndFriendid(String userid, String friendid);
}
