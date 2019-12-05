package com.golf.dao.mapper;

import java.util.List;

import com.golf.dao.entity.Client;
import tk.mybatis.mapper.common.Mapper;

public interface ClientMapper extends Mapper<Client> {
    List<String> selectAllowedClient(String serviceId);

    List<Client> selectAuthorityServiceInfo(int clientId);
}