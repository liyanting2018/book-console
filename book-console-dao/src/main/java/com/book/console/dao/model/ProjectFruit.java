package com.book.console.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "project_fruit")
public class ProjectFruit {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	 
	 @Column(name = "project_nm")
	    private String projectNm;
	 
	 @Column(name = "project_fee")
	    private String projectFee;
	 
	 @Column(name = "start_date")
	    private Date startDate;
	 
	 @Column(name = "end_date")
	    private Date endDate;
	 
	 @Column(name = "project_unit")
	    private String projectUnit;
	 
	 @Column(name = "project_head")
	    private String projectHead;
	 
	 @Column(name = "project_member")
	    private String projectMember;
	 
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

	public String getProjectNm() {
		return projectNm;
	}

	public void setProjectNm(String projectNm) {
		this.projectNm = projectNm;
	}

	public String getProjectFee() {
		return projectFee;
	}

	public void setProjectFee(String projectFee) {
		this.projectFee = projectFee;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getProjectUnit() {
		return projectUnit;
	}

	public void setProjectUnit(String projectUnit) {
		this.projectUnit = projectUnit;
	}

	public String getProjectHead() {
		return projectHead;
	}

	public void setProjectHead(String projectHead) {
		this.projectHead = projectHead;
	}

	public String getProjectMember() {
		return projectMember;
	}

	public void setProjectMember(String projectMember) {
		this.projectMember = projectMember;
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

	@Override
	public String toString() {
		return "ProjectFruit [id=" + id + ", projectNm=" + projectNm + ", projectFee=" + projectFee + ", startDate="
				+ startDate + ", endDate=" + endDate + ", projectUnit=" + projectUnit + ", projectHead=" + projectHead
				+ ", project_member=" + projectMember + ", fruitCategoryId=" + fruitCategoryId + ", fruitStatus="
				+ fruitStatus + ", crtUser=" + crtUser + ", cupUser=" + uptUser + ", crtTime=" + crtTime + ", uptTime="
				+ uptTime + "]";
	}

	
}
