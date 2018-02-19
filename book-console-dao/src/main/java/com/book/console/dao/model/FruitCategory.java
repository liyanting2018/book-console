package com.book.console.dao.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "fruit_category")
public class FruitCategory implements Serializable {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fruit_category")
    private String fruitCategory;
    
    @Column(name = "fruit_type")
    private String fruitType;
    
    @Column(name = "fruit_level")
    private String fruitLevel;
    
    @Column(name = "fruit_DESC")
    private String fruitDesc;
    
    @Column(name = "crt_user")
    private String crtUser;
    
    @Column(name = "cup_user")
    private String cupUser;

    @Column(name = "crt_time")
    private Date crtTime;

    @Column(name = "upt_time")
    private Date uptTime;

    private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFruitCategory() {
		return fruitCategory;
	}

	public void setFruitCategory(String fruitCategory) {
		this.fruitCategory = fruitCategory;
	}

	public String getFruitType() {
		return fruitType;
	}

	public void setFruitType(String fruitType) {
		this.fruitType = fruitType;
	}

	public String getFruitLevel() {
		return fruitLevel;
	}

	public void setFruitLevel(String fruitLevel) {
		this.fruitLevel = fruitLevel;
	}

	public String getFruitDesc() {
		return fruitDesc;
	}

	public void setFruitDesc(String fruitDesc) {
		this.fruitDesc = fruitDesc;
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
		return "FruitCategory [id=" + id + ", fruitCategory=" + fruitCategory + ", fruitType=" + fruitType
				+ ", fruitLevel=" + fruitLevel + ", fruitDesc=" + fruitDesc + ", crtUser=" + crtUser + ", cupUser="
				+ cupUser + ", crtTime=" + crtTime + ", uptTime=" + uptTime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((crtTime == null) ? 0 : crtTime.hashCode());
		result = prime * result + ((crtUser == null) ? 0 : crtUser.hashCode());
		result = prime * result + ((cupUser == null) ? 0 : cupUser.hashCode());
		result = prime * result + ((fruitCategory == null) ? 0 : fruitCategory.hashCode());
		result = prime * result + ((fruitDesc == null) ? 0 : fruitDesc.hashCode());
		result = prime * result + ((fruitLevel == null) ? 0 : fruitLevel.hashCode());
		result = prime * result + ((fruitType == null) ? 0 : fruitType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((uptTime == null) ? 0 : uptTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FruitCategory other = (FruitCategory) obj;
		if (crtTime == null) {
			if (other.crtTime != null)
				return false;
		} else if (!crtTime.equals(other.crtTime))
			return false;
		if (crtUser == null) {
			if (other.crtUser != null)
				return false;
		} else if (!crtUser.equals(other.crtUser))
			return false;
		if (cupUser == null) {
			if (other.cupUser != null)
				return false;
		} else if (!cupUser.equals(other.cupUser))
			return false;
		if (fruitCategory == null) {
			if (other.fruitCategory != null)
				return false;
		} else if (!fruitCategory.equals(other.fruitCategory))
			return false;
		if (fruitDesc == null) {
			if (other.fruitDesc != null)
				return false;
		} else if (!fruitDesc.equals(other.fruitDesc))
			return false;
		if (fruitLevel == null) {
			if (other.fruitLevel != null)
				return false;
		} else if (!fruitLevel.equals(other.fruitLevel))
			return false;
		if (fruitType == null) {
			if (other.fruitType != null)
				return false;
		} else if (!fruitType.equals(other.fruitType))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (uptTime == null) {
			if (other.uptTime != null)
				return false;
		} else if (!uptTime.equals(other.uptTime))
			return false;
		return true;
	}

	
    
}