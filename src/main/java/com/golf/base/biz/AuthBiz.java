package com.golf.base.biz;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.golf.common.BaseBiz;
import com.golf.dao.entity.Client;
import com.golf.dao.mapper.ClientMapper;


@Service
@Transactional(rollbackFor = Exception.class)
public class AuthBiz extends BaseBiz<ClientMapper,Client> {
	
}
