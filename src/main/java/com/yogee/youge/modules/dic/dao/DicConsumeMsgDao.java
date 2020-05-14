/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yogee/youge">JeeSite</a> All rights reserved.
 */
package com.yogee.youge.modules.dic.dao;

import com.yogee.youge.common.persistence.CrudDao;
import com.yogee.youge.common.persistence.annotation.MyBatisDao;
import com.yogee.youge.modules.dic.entity.DicConsumeMsg;
import com.yogee.youge.modules.dic.entity.ExportConsumeMsg;

import java.util.List;
import java.util.Map;

/**
 * 消费记录DAO接口
 * @author sunye
 * @version 2020-05-14
 */
@MyBatisDao
public interface DicConsumeMsgDao extends CrudDao<DicConsumeMsg> {

    List<ExportConsumeMsg> findAllExport(DicConsumeMsg dicConsumeMsg);

    String sumMoney(DicConsumeMsg dicConsumeMsg);
}