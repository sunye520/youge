/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yogee/zhebuy">JeeSite</a> All rights reserved.
 */
package com.yogee.youge.modules.dic.entity;

import com.yogee.youge.common.persistence.ExportEntity;
import com.yogee.youge.common.utils.excel.annotation.ExcelField;

import java.util.Date;

/**
 * 导出订单商品
 */
public class ExportConsumeMsg extends ExportEntity<ExportConsumeMsg> {

	private static final long serialVersionUID = 1L;
	private String clientId;		// 客户资料id
	private String consumeDate;		// 消费时间
	private String consumeMsg;		// 消费信息
	private String consumeMoney;		// 消费金额
	private String numberPlate;		// 车牌号
	private String phone;		// 电话
	private String name;		// 客户名称




	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@ExcelField(title="消费时间", align=1, sort=5, width = 5000)
	public String getConsumeDate() {
		return consumeDate;
	}

	public void setConsumeDate(String consumeDate) {
		this.consumeDate = consumeDate;
	}
	@ExcelField(title="消费明细", align=10, sort=6, width = 20000)
	public String getConsumeMsg() {
		return consumeMsg;
	}

	public void setConsumeMsg(String consumeMsg) {
		this.consumeMsg = consumeMsg;
	}
	@ExcelField(title="消费金额", align=1, sort=4, width = 5000)
	public String getConsumeMoney() {
		return consumeMoney;
	}

	public void setConsumeMoney(String consumeMoney) {
		this.consumeMoney = consumeMoney;
	}
	@ExcelField(title="车牌号", align=1, sort=3, width = 5000)
	public String getNumberPlate() {
		return numberPlate;
	}

	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}
	@ExcelField(title="客户电话", align=1, sort=2, width = 5000)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	@ExcelField(title="客户姓名", align=1, sort=1, width = 5000)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}