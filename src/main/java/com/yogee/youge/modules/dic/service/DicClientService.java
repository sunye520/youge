/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yogee/youge">JeeSite</a> All rights reserved.
 */
package com.yogee.youge.modules.dic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yogee.youge.common.persistence.Page;
import com.yogee.youge.common.service.CrudService;
import com.yogee.youge.modules.dic.entity.DicClient;
import com.yogee.youge.modules.dic.dao.DicClientDao;

/**
 * 客户资料Service
 * @author sunye
 * @version 2020-05-13
 */
@Service
@Transactional(readOnly = true)
public class DicClientService extends CrudService<DicClientDao, DicClient> {

	public DicClient get(String id) {
		return super.get(id);
	}
	
	public List<DicClient> findList(DicClient dicClient) {
		return super.findList(dicClient);
	}
	
	public Page<DicClient> findPage(Page<DicClient> page, DicClient dicClient) {
		return super.findPage(page, dicClient);
	}
	
	@Transactional(readOnly = false)
	public void save(DicClient dicClient) {
		super.save(dicClient);
	}
	
	@Transactional(readOnly = false)
	public void delete(DicClient dicClient) {
		super.delete(dicClient);
	}
	
}