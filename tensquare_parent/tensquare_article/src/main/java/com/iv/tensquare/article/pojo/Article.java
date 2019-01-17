package com.iv.tensquare.article.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_article")
public class Article implements Serializable {
	
	@Id
	private String id;
	
	private String columnid;//专栏ID
	private String userid;
	private String title;
	private String content;
	private String image;
	private Date createtime;
	private Date updatetime;
	private String ispublic;//是否公开：0(不公开), 1(公开)
	private String istop;	//是否置顶：0(不置顶), 1(置顶)
	private Long visits;	//浏览量
	private Long thumbup;	//点赞数
	private Long comment;	//评论数
	private String state;	//审核状态：0(未审核)，1(已审核)
	private String channelid;//所属频道，频道表主键ID
	private String url;
	private String type;	//文章类型，0(分享),1(专栏)
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getColumnid() {
		return columnid;
	}
	public void setColumnid(String columnid) {
		this.columnid = columnid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public String getIspublic() {
		return ispublic;
	}
	public void setIspublic(String ispublic) {
		this.ispublic = ispublic;
	}
	public String getIstop() {
		return istop;
	}
	public void setIstop(String istop) {
		this.istop = istop;
	}
	public Long getVisits() {
		return visits;
	}
	public void setVisits(Long visits) {
		this.visits = visits;
	}
	public Long getThumbup() {
		return thumbup;
	}
	public void setThumbup(Long thumbup) {
		this.thumbup = thumbup;
	}
	public Long getComment() {
		return comment;
	}
	public void setComment(Long comment) {
		this.comment = comment;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getChannelid() {
		return channelid;
	}
	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Article [id=" + id + ", columnid=" + columnid + ", userid=" + userid + ", title=" + title + ", content="
				+ content + ", image=" + image + ", createtime=" + createtime + ", updatetime=" + updatetime
				+ ", ispublic=" + ispublic + ", istop=" + istop + ", visits=" + visits + ", thumbup=" + thumbup
				+ ", comment=" + comment + ", state=" + state + ", channelid=" + channelid + ", url=" + url + ", type="
				+ type + "]";
	}
}
