package com.book.console.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "fruit_detail")
public class FruitDetail {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	 
	 @Column(name = "fruit_code")
	    private String fruitCode;
	 
	 @Column(name = "fruit_nm")
	    private String fruitNm;
	 
	 @Column(name = "fruit_DESC")
	    private String fruitDesc;
	 
	 @Column(name = "fruit_category_id")
	    private String fruitCategoryId;
	 
	 @Column(name = "fruit_category")
	    private String fruitCategory;
	 
	 @Column(name = "fruit_man_id")
	    private String fruitManId;
	 
	 @Column(name = "fruit_man_nm")
	    private String fruitManNm;
	 
	 @Column(name = "fruit_status")
	    private String fruitStatus;
	 
	 @Column(name = "crt_user")
	    private String crtUser;
	 
	 @Column(name = "cup_user")
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

	public String getFruitCode() {
		return fruitCode;
	}

	public void setFruitCode(String fruitCode) {
		this.fruitCode = fruitCode;
	}

	public String getFruitNm() {
		return fruitNm;
	}

	public void setFruitNm(String fruitNm) {
		this.fruitNm = fruitNm;
	}

	public String getFruitDesc() {
		return fruitDesc;
	}

	public void setFruitDesc(String fruitDesc) {
		this.fruitDesc = fruitDesc;
	}

	public String getFruitCategoryId() {
		return fruitCategoryId;
	}

	public void setFruitCategoryId(String fruitCategoryId) {
		this.fruitCategoryId = fruitCategoryId;
	}

	public String getFruitCategory() {
		return fruitCategory;
	}

	public void setFruitCategory(String fruitCategory) {
		this.fruitCategory = fruitCategory;
	}

	public String getFruitManId() {
		return fruitManId;
	}

	public void setFruitManId(String fruitManId) {
		this.fruitManId = fruitManId;
	}

	public String getFruitManNm() {
		return fruitManNm;
	}

	public void setFruitManNm(String fruitManNm) {
		this.fruitManNm = fruitManNm;
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

	public String getCupUser() {
		return cupUser;
	}

	public void setCupUser(String cupUser) {
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
		return "FruitDetail [id=" + id + ", fruitCode=" + fruitCode + ", fruitNm=" + fruitNm + ", fruitDesc="
				+ fruitDesc + ", fruitCategoryId=" + fruitCategoryId + ", fruitCategory=" + fruitCategory
				+ ", fruitManId=" + fruitManId + ", fruitManNm=" + fruitManNm + ", fruitStatus=" + fruitStatus
				+ ", crtUser=" + crtUser + ", cupUser=" + cupUser + ", crtTime=" + crtTime + ", uptTime=" + uptTime
				+ "]";
	}
	 
	 
	 
}
