package com.iv.tensquare.qa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.iv.tensquare.qa.pojo.Reply;

public interface ReplyDao extends JpaRepository<Reply, String>, JpaSpecificationExecutor<Reply> {

}
