package com.iv.tensquare.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.iv.tensquare.article.pojo.Article;

public interface ArticleDao extends JpaRepository<Article, String>, JpaSpecificationExecutor<Article> {

	@Modifying
	@Query(value = "update tb_article set state = 1 where id = ?", nativeQuery = true)
	public void updateState(String id);
	
	@Modifying
	@Query(value = "update tb_article set thumbup = thumbup + 1 where id = ?", nativeQuery = true)
	public void addThumbup(String id);
	
}
