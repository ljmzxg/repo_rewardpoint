package com.iv.tensquare.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iv.tensquare.user.pojo.User;

public interface UserDao extends JpaRepository<User, String> {

}
