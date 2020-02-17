package com.golf.dao.entity;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "ewheel_car")
public class EwheelCar {
    /**
     * 小车ID
     */
    @Id
    @Column(name = "car_id")
    private String carId;

    @Column(name = "car_name")
    private String carName;

    /**
     * 小车密码
     */
    @JsonIgnore
    private String password;

    /**
     * 初始密码
     */
    @Column(name = "initial_password")
    @JsonIgnore
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
    @Column(name = "ext_question1")
    private String extQuestion1;

    /**
     * 找回问题答案
     */
    @Column(name = "ext_answer1")
    @JsonIgnore
    private String extAnswer1;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取小车ID
     *
     * @return car_id - 小车ID
     */
    public String getCarId() {
        return carId;
    }

    /**
     * 设置小车ID
     *
     * @param carId 小车ID
     */
    public void setCarId(String carId) {
        this.carId = carId;
    }

    /**
     * @return car_name
     */
    public String getCarName() {
        return carName;
    }

    /**
     * @param carName
     */
    public void setCarName(String carName) {
        this.carName = carName;
    }

    /**
     * 获取小车密码
     *
     * @return password - 小车密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置小车密码
     *
     * @param password 小车密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取初始密码
     *
     * @return initial_password - 初始密码
     */
    public String getInitialPassword() {
        return initialPassword;
    }

    /**
     * 设置初始密码
     *
     * @param initialPassword 初始密码
     */
    public void setInitialPassword(String initialPassword) {
        this.initialPassword = initialPassword;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取找回问题
     *
     * @return ext_question1 - 找回问题
     */
    public String getExtQuestion1() {
        return extQuestion1;
    }

    /**
     * 设置找回问题
     *
     * @param extQuestion1 找回问题
     */
    public void setExtQuestion1(String extQuestion1) {
        this.extQuestion1 = extQuestion1;
    }

    /**
     * 获取找回问题答案
     *
     * @return ext_answer1 - 找回问题答案
     */
    public String getExtAnswer1() {
        return extAnswer1;
    }

    /**
     * 设置找回问题答案
     *
     * @param extAnswer1 找回问题答案
     */
    public void setExtAnswer1(String extAnswer1) {
        this.extAnswer1 = extAnswer1;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}