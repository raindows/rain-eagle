package org.rain.eagle.rpc.server.entity;

import java.io.Serializable;
import java.util.Date;

public class EagleUser implements Serializable {

	private static final long serialVersionUID = -4915875925879898230L;

	private Long id;
	private String userId;
	private String userName;
	private Byte userSex;
	private String userBirthYear;
	private String userBirthMonth;
	private String userBirthDay;
	private Date createTime;
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Byte getUserSex() {
		return userSex;
	}

	public void setUserSex(Byte userSex) {
		this.userSex = userSex;
	}

	public String getUserBirthYear() {
		return userBirthYear;
	}

	public void setUserBirthYear(String userBirthYear) {
		this.userBirthYear = userBirthYear;
	}

	public String getUserBirthMonth() {
		return userBirthMonth;
	}

	public void setUserBirthMonth(String userBirthMonth) {
		this.userBirthMonth = userBirthMonth;
	}

	public String getUserBirthDay() {
		return userBirthDay;
	}

	public void setUserBirthDay(String userBirthDay) {
		this.userBirthDay = userBirthDay;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
