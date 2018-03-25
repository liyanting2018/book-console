package com.book.console.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "paper_fruit")
public class PaperFruit {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	 
	 @Column(name = "paper_nm")
	    private String paperNm;
	 
	 @Column(name = "journal_name")
	    private String journalName;
	 
	 @Column(name = "journal_num")
	    private String journalNum;
	 
	 @Column(name = "published_date")
	    private Date publishedDate;
	 
	 @Column(name = "paper_level")
	    private String paperLevel;
	 
	 @Column(name = "paper_author")
	    private String paperAuthor;
	 
	 @Column(name = "fruit_category_id")
	    private String fruitCategoryId;
	 
	 @Column(name = "fruit_status")
	    private String fruitStatus;
	 
	 @Column(name = "crt_user")
	    private String crtUser;
	 
	 @Column(name = "upt_user")
	    private String UptUser;
	 
	 @Column(name = "crt_time")
	    private Date crtTime;
	 
	 @Column(name = "upt_time")
	    private Date uptTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPaperNm() {
		return paperNm;
	}

	public void setPaperNm(String paperNm) {
		this.paperNm = paperNm;
	}

	public String getJournalName() {
		return journalName;
	}

	public void setJournalName(String journalName) {
		this.journalName = journalName;
	}

	public String getJournalNum() {
		return journalNum;
	}

	public void setJournalNum(String journalNum) {
		this.journalNum = journalNum;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getPaperLevel() {
		return paperLevel;
	}

	public void setPaperLevel(String paperLevel) {
		this.paperLevel = paperLevel;
	}

	public String getPaperAuthor() {
		return paperAuthor;
	}

	public void setPaperAuthor(String paperAuthor) {
		this.paperAuthor = paperAuthor;
	}

	public String getFruitCategoryId() {
		return fruitCategoryId;
	}

	public void setFruitCategoryId(String fruitCategoryId) {
		this.fruitCategoryId = fruitCategoryId;
	}

	public String getFruitStatus() {
		return fruitStatus;
	}

	public void setFruitStatus(String fruitStatus) {
		this.fruitStatus = fruitStatus;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

	public String getUptUser() {
		return UptUser;
	}

	public void setUptUser(String UptUser) {
		this.UptUser = UptUser;
	}

	public Date getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}

	public Date getUptTime() {
		return uptTime;
	}

	public void setUptTime(Date uptTime) {
		this.uptTime = uptTime;
	}

	@Override
	public String toString() {
		return "PaperFruit [id=" + id + ", paperNm=" + paperNm + ", journalName=" + journalName + ", journalNum="
				+ journalNum + ", publishedDate=" + publishedDate + ", paperLevel=" + paperLevel + ", paperAuthor="
				+ paperAuthor + ", fruitCategoryId=" + fruitCategoryId + ", fruitStatus=" + fruitStatus + ", crtUser="
				+ crtUser + ", UptUser=" + UptUser + ", crtTime=" + crtTime + ", uptTime=" + uptTime + "]";
	}

}
