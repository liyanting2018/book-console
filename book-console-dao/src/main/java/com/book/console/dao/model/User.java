package com.book.console.dao.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.Transient;

import com.book.console.common.Constants;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 权限
     */
    private Integer role;
    
    /**
     * 职工工号
     */
    @Column(name = "user_code")
    private String userCode;
    

    /**
     * 系
     */
    private String department;

    /**
     * 电话
     */
    private String mobile;

    /**
     * 职称
     */
    private String title;
    
    /**
     * 邮箱
     */
    private String email;
    
    //密碼
    private String password;

 
    
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
     * 获取团队名称
     * @return
     */
    public String teamName() {
        return Constants.ADMIN_USERNAME.equals(username) ? "" : this.department.substring(0, this.department.lastIndexOf("_"));
    }


    /**
     * 中文名
     */
    @Column(name = "chinese_name")
    private String chineseName;

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private Date createdAt;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取权限
     *
     * @return role - 权限
     */
    public Integer getRole() {
        return role;
    }

    /**
     * 设置权限
     *
     * @param role 权限
     */
    public void setRole(Integer role) {
        this.role = role;
    }

    /**
     * 获取部门
     *
     * @return department - 部门
     */
    public String getDepartment() {
        return department;
    }

    /**
     * 设置部门
     *
     * @param department 部门
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * 获取电话
     *
     * @return mobile - 电话
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置电话
     *
     * @param mobile 电话
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取头衔
     *
     * @return title - 头衔
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置头衔
     *
     * @param title 头衔
     */
    public void setTitle(String title) {
        this.title = title;
    }

    
    public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
     * 获取中文名
     *
     * @return chinese_name - 中文名
     */
    public String getChineseName() {
        return chineseName;
    }

    /**
     * 设置中文名
     *
     * @param chineseName 中文名
     */
    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    /**
     * 获取创建时间
     *
     * @return created_at - 创建时间
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * 设置创建时间
     *
     * @param createdAt 创建时间
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", role=").append(role);
        sb.append(", department=").append(department);
        sb.append(", mobile=").append(mobile);
        sb.append(", title=").append(title);
        sb.append(", chineseName=").append(chineseName);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}