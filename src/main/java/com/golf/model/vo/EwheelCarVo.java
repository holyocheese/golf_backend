package com.golf.model.vo;

import java.util.Date;

public class EwheelCarVo {

    private String carId;

    private String carName;

    /**
     * 小车密码
     */
    private String password;

    /**
     * 初始密码
     */
    private String initialPassword;

    /**
     * 备注
     */
    private String remark;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 找回问题
     */
    private String extQuestion1;

    /**
     * 找回问题答案
     */
    private String extAnswer1;

    /**
     * 更新时间
     */
    private Date updateTime;

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getInitialPassword() {
		return initialPassword;
	}

	public void setInitialPassword(String initialPassword) {
		this.initialPassword = initialPassword;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getExtQuestion1() {
		return extQuestion1;
	}

	public void setExtQuestion1(String extQuestion1) {
		this.extQuestion1 = extQuestion1;
	}

	public String getExtAnswer1() {
		return extAnswer1;
	}

	public void setExtAnswer1(String extAnswer1) {
		this.extAnswer1 = extAnswer1;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
    
}
