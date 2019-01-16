package com.iv.tensquare.qa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.iv.tensquare.qa.pojo.Problem;

public interface ProblemDao extends JpaRepository<Problem, String>, JpaSpecificationExecutor<Problem> {

}
