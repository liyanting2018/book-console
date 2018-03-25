package com.book.console.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "book_fruit")
public class BookFruit {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	 
	 @Column(name = "book_nm")
	    private String bookNm;
	 
	 @Column(name = "published_date")
	    private Date publishedDate;
	 
	 @Column(name = "published_unit")
	    private String publishedUnit;
	 
	 @Column(name = "published_num")
	    private String publishedNum;
	 
	 @Column(name = "book_author")
	    private String bookAuthor;
	 
	 @Column(name = "book_chapter")
	    private String bookChapter;
	 
	 @Column(name = "word_cnt")
	    private String wordCnt;
	 
	 @Column(name = "fruit_category_id")
	    private String fruitCategoryId;
	 
	 @Column(name = "fruit_status")
	    private String fruitStatus;
	 
	 @Column(name = "crt_user")
	    private String crtUser;
	 
	 @Column(name = "upt_user")
	    private String cupUser;
	 
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

	public String getBookNm() {
		return bookNm;
	}

	public void setBookNm(String bookNm) {
		this.bookNm = bookNm;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getPublishedUnit() {
		return publishedUnit;
	}

	public void setPublishedUnit(String publishedUnit) {
		this.publishedUnit = publishedUnit;
	}

	public String getPublishedNum() {
		return publishedNum;
	}

	public void setPublishedNum(String publishedNum) {
		this.publishedNum = publishedNum;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookChapter() {
		return bookChapter;
	}

	public void setBookChapter(String bookChapter) {
		this.bookChapter = bookChapter;
	}

	public String getWordCnt() {
		return wordCnt;
	}

	public void setWordCnt(String wordCnt) {
		this.wordCnt = wordCnt;
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
		return cupUser;
	}

	public void setUptUser(String cupUser) {
		this.cupUser = cupUser;
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
		return "BookFruit [id=" + id + ", bookNm=" + bookNm + ", publishedDate=" + publishedDate + ", publishedUnit="
				+ publishedUnit + ", publisheNum=" + publishedNum + ", bookAuthor=" + bookAuthor + ", bookChapter="
				+ bookChapter + ", wordCnt=" + wordCnt + ", fruitCategoryId=" + fruitCategoryId + ", fruitStatus="
				+ fruitStatus + ", crtUser=" + crtUser + ", cupUser=" + cupUser + ", crtTime=" + crtTime + ", uptTime="
				+ uptTime + "]";
	}

	 
}
