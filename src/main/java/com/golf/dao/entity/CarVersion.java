package com.golf.dao.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "car_version")
public class CarVersion {
    @Id
    private Integer id;

    /**
     * 型号
     */
    private String name;

    /**
     * 数据创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 数据更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 创建人
     */
    @Column(name = "create_id")
    private String createId;

    /**
     * 更新人
     */
    @Column(name = "update_id")
    private String updateId;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取型号
     *
     * @return name - 型号
     */
    public String getName() {
        return name;
    }

    /**
     * 设置型号
     *
     * @param name 型号
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取数据创建时间
     *
     * @return create_time - 数据创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置数据创建时间
     *
     * @param createTime 数据创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取数据更新时间
     *
     * @return update_time - 数据更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置数据更新时间
     *
     * @param updateTime 数据更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取创建人
     *
     * @return create_id - 创建人
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * 设置创建人
     *
     * @param createId 创建人
     */
    public void setCreateId(String createId) {
        this.createId = createId;
    }

    /**
     * 获取更新人
     *
     * @return update_id - 更新人
     */
    public String getUpdateId() {
        return updateId;
    }

    /**
     * 设置更新人
     *
     * @param updateId 更新人
     */
    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }
}