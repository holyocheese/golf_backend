package com.golf.dao.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "app_setting")
public class AppSetting {
    @Id
    private Integer id;

    /**
     * APP名称
     */
    private String name;

    /**
     * 版本号
     */
    private String version;

    /**
     * 手机系统类型：1、安卓 2、IOS
     */
    private Integer type;

    @Column(name = "force_update")
    private Integer forceUpdate;

    /**
     * 是否需要更新：0、不需要 1、需要
     */
    @Column(name = "need_update")
    private Integer needUpdate;

    @Column(name = "update_message")
    private String updateMessage;

    private String extend;

    /**
     * 生效时间,时间戳
     */
    @Column(name = "effective_time")
    private Date effectiveTime;

    /**
     * 文件名 对应安装包名称
     */
    @Column(name = "file_name")
    private String fileName;

    @Column(name = "tenant_id")
    private String tenantId;

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
     * 获取APP名称
     *
     * @return name - APP名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置APP名称
     *
     * @param name APP名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取版本号
     *
     * @return version - 版本号
     */
    public String getVersion() {
        return version;
    }

    /**
     * 设置版本号
     *
     * @param version 版本号
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 获取手机系统类型：1、安卓 2、IOS
     *
     * @return type - 手机系统类型：1、安卓 2、IOS
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置手机系统类型：1、安卓 2、IOS
     *
     * @param type 手机系统类型：1、安卓 2、IOS
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return force_update
     */
    public Integer getForceUpdate() {
        return forceUpdate;
    }

    /**
     * @param forceUpdate
     */
    public void setForceUpdate(Integer forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    /**
     * 获取是否需要更新：0、不需要 1、需要
     *
     * @return need_update - 是否需要更新：0、不需要 1、需要
     */
    public Integer getNeedUpdate() {
        return needUpdate;
    }

    /**
     * 设置是否需要更新：0、不需要 1、需要
     *
     * @param needUpdate 是否需要更新：0、不需要 1、需要
     */
    public void setNeedUpdate(Integer needUpdate) {
        this.needUpdate = needUpdate;
    }

    /**
     * @return update_message
     */
    public String getUpdateMessage() {
        return updateMessage;
    }

    /**
     * @param updateMessage
     */
    public void setUpdateMessage(String updateMessage) {
        this.updateMessage = updateMessage;
    }

    /**
     * @return extend
     */
    public String getExtend() {
        return extend;
    }

    /**
     * @param extend
     */
    public void setExtend(String extend) {
        this.extend = extend;
    }

    /**
     * 获取生效时间,时间戳
     *
     * @return effective_time - 生效时间,时间戳
     */
    public Date getEffectiveTime() {
        return effectiveTime;
    }

    /**
     * 设置生效时间,时间戳
     *
     * @param effectiveTime 生效时间,时间戳
     */
    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    /**
     * 获取文件名 对应安装包名称
     *
     * @return file_name - 文件名 对应安装包名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置文件名 对应安装包名称
     *
     * @param fileName 文件名 对应安装包名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return tenant_id
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * @param tenantId
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
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