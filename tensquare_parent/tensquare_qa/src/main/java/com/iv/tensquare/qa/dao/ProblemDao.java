package com.iv.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.iv.tensquare.qa.pojo.Problem;

public interface ProblemDao extends JpaRepository<Problem, String>, JpaSpecificationExecutor<Problem> {

	@Query(value = "select * from tb_problem, tb_pl where id = problemid and labelid=? order by replytime desc", nativeQuery = true)
	public Page<Problem> newList(String labelId, Pageable pageable);
	
	@Query(value = "select * from tb_problem, tb_pl where id = problemid and labelid=? order by reply desc", nativeQuery = true)
	public Page<Problem> hotList(String labelId, Pageable pageable);
	
	@Query(value = "select * from tb_problem , tb_pl where id = problemid and labelid=? and reply = 0 order by createtime desc", nativeQuery = true)
	public Page<Problem> waitList(String labelId, Pageable pageable);
	
}
