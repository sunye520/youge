/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yogee/youge">JeeSite</a> All rights reserved.
 */
package com.yogee.youge.modules.dic.service;

import java.util.List;
import java.util.Map;

import com.yogee.youge.modules.dic.entity.ExportConsumeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yogee.youge.common.persistence.Page;
import com.yogee.youge.common.service.CrudService;
import com.yogee.youge.modules.dic.entity.DicConsumeMsg;
import com.yogee.youge.modules.dic.dao.DicConsumeMsgDao;

/**
 * 消费记录Service
 * @author sunye
 * @version 2020-05-14
 */
@Service
@Transactional(readOnly = true)
public class DicConsumeMsgService extends CrudService<DicConsumeMsgDao, DicConsumeMsg> {
	@Autowired
	DicConsumeMsgDao dicConsumeMsgDao;

	public DicConsumeMsg get(String id) {
		return super.get(id);
	}
	
	public List<DicConsumeMsg> findList(DicConsumeMsg dicConsumeMsg) {
		return super.findList(dicConsumeMsg);
	}
	
	public Page<DicConsumeMsg> findPage(Page<DicConsumeMsg> page, DicConsumeMsg dicConsumeMsg) {
		return super.findPage(page, dicConsumeMsg);
	}
	
	@Transactional(readOnly = false)
	public void save(DicConsumeMsg dicConsumeMsg) {
		super.save(dicConsumeMsg);
	}
	
	@Transactional(readOnly = false)
	public void delete(DicConsumeMsg dicConsumeMsg) {
		super.delete(dicConsumeMsg);
	}

	public List<ExportConsumeMsg> findAllExport(DicConsumeMsg dicConsumeMsg) {
		return dicConsumeMsgDao.findAllExport(dicConsumeMsg);
	}

	public String sumMoney(DicConsumeMsg dicConsumeMsg) {
		return dicConsumeMsgDao.sumMoney(dicConsumeMsg);
	}
}