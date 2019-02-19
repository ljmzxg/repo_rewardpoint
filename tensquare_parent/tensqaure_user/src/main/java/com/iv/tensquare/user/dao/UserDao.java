package com.iv.tensquare.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.iv.tensquare.user.pojo.User;

public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

	User findByMobile(String mobile);

	@Modifying
	@Query(value = "update tb_user set fanscount = fanscount + ?1 where id = ?2", nativeQuery = true)
	void updateFanscount(int x, String friendid);

	@Modifying
	@Query(value = "update tb_user set followcount = followcount + ?1 where id = ?2", nativeQuery = true)
	void updateFollowcount(int x, String userid);

}
