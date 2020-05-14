/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yogee/youge">JeeSite</a> All rights reserved.
 */
package com.yogee.youge.modules.dic.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.yogee.youge.common.persistence.DataEntity;

/**
 * 消费记录Entity
 * @author sunye
 * @version 2020-05-14
 */
public class DicConsumeMsg extends DataEntity<DicConsumeMsg> {
	
	private static final long serialVersionUID = 1L;
	private String clientId;		// 客户资料id
	private Date consumeDate;		// 消费时间
	private String consumeMsg;		// 消费信息
	private String consumeMoney;		// 消费金额
	private String numberPlate;		// 车牌号
	private String phone;		// 电话
	private String name;		// 客户名称
	
	public DicConsumeMsg() {
		super();
	}

	public DicConsumeMsg(String id){
		super(id);
	}

	@Length(min=1, max=20, message="车牌号长度必须介于 1 和 20 之间")
	public String getNumberPlate() {
		return numberPlate;
	}

	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getConsumeDate() {
		return consumeDate;
	}

	public void setConsumeDate(Date consumeDate) {
		this.consumeDate = consumeDate;
	}
	
	public String getConsumeMsg() {
		return consumeMsg;
	}

	public void setConsumeMsg(String consumeMsg) {
		this.consumeMsg = consumeMsg;
	}
	
	public String getConsumeMoney() {
		return consumeMoney;
	}

	public void setConsumeMoney(String consumeMoney) {
		this.consumeMoney = consumeMoney;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}