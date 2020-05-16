/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yogee/youge">JeeSite</a> All rights reserved.
 */
package com.yogee.youge.modules.dic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yogee.youge.common.persistence.Page;
import com.yogee.youge.common.service.CrudService;
import com.yogee.youge.modules.dic.entity.DicClient;
import com.yogee.youge.modules.dic.dao.DicClientDao;

/**
 * 消费记录Service
 * @author sunye
 * @version 2020-05-14
 */
@Service
@Transactional(readOnly = true)
public class DicClientService extends CrudService<DicClientDao, DicClient> {
	@Autowired
	DicClientDao dicClientDao;

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

	public int findCountByNumberPlate(DicClient dicClient) {
		return dicClientDao.findCountByNumberPlate(dicClient);
	}
}