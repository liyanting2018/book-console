package com.book.console.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "patent_fruit")
public class PatentFruit {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	 
	 @Column(name = "patent_nm")
	    private String patentNm;
	 
	 @Column(name = "patent_id")
	    private String patentId;
	 
	 @Column(name = "inventor")
	    private String inventor;
	 
	 @Column(name = "inventor_num")
	    private String inventorNum;
	 
	 @Column(name = "apply_date")
	    private Date applyDate;
	 
	 @Column(name = "auth_date")
	    private Date authDate;
	 
	 @Column(name = "diploma_id")
	    private String diplomaId;
	 
	 @Column(name = "patent_type")
	    private String patentType;
	 

	@Column(name = "fruit_category_id")
	    private String fruitCategoryId;
	 
	 @Column(name = "fruit_status")
	    private String fruitStatus;
	 
	 @Column(name = "crt_user")
	    private String crtUser;
	 
	 @Column(name = "upt_user")
	    private String uptUser;
	 
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

	public String getPatentNm() {
		return patentNm;
	}

	public void setPatentNm(String patentNm) {
		this.patentNm = patentNm;
	}

	public String getPatentId() {
		return patentId;
	}

	public void setPatentId(String patentId) {
		this.patentId = patentId;
	}

	public String getInventor() {
		return inventor;
	}

	public void setInventor(String inventor) {
		this.inventor = inventor;
	}

	public String getinventorNum() {
		return inventorNum;
	}

	public void setinventorNum(String inventorNum) {
		this.inventorNum = inventorNum;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public Date getAuthDate() {
		return authDate;
	}

	public void setAuthDate(Date authDate) {
		this.authDate = authDate;
	}

	public String getDiplomaId() {
		return diplomaId;
	}

	public void setDiplomaId(String diplomaId) {
		this.diplomaId = diplomaId;
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
		return uptUser;
	}

	public void setUptUser(String uptUser) {
		this.uptUser = uptUser;
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
	
	 public String getPatentType() {
		return patentType;
	}

	public void setPatentType(String patentType) {
		this.patentType = patentType;
	}

	@Override
	public String toString() {
		return "PatentFruit [id=" + id + ", patentNm=" + patentNm + ", patentId=" + patentId + ", inventor=" + inventor
				+ ", inventorNum=" + inventorNum + ", applyDate=" + applyDate + ", authDate=" + authDate
				+ ", diplomaId=" + diplomaId + ", patentType=" + patentType + ", fruitCategoryId=" + fruitCategoryId
				+ ", fruitStatus=" + fruitStatus + ", crtUser=" + crtUser + ", cupUser=" + uptUser + ", crtTime="
				+ crtTime + ", uptTime=" + uptTime + "]";
	}


	
}
