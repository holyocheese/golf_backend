package com.golf.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.golf.dao.entity.ResourceAuthority;
import tk.mybatis.mapper.common.Mapper;

public interface ResourceAuthorityMapper extends Mapper<ResourceAuthority> {
	public void deleteByAuthorityIdAndResourceType(@Param("authorityId")String authorityId,@Param("resourceType") String resourceType);
}