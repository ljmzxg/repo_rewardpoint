package com.iv.tensquare.recruit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.iv.tensquare.recruit.pojo.Enterprise;

public interface EnterpriseDao extends JpaRepository<Enterprise, String>, JpaSpecificationExecutor<Enterprise> {

	public List<Enterprise> findByIshot(String ishot);
}
