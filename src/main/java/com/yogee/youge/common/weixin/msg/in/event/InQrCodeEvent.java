package com.yogee.youge.common.weixin.msg.in.event;


import com.yogee.youge.common.weixin.msg.in.InMsg;

/**
 扫描带参数二维码事件
 1. 用户未关注时，进行关注后的事件推送
 <xml>
 <ToUserName><![CDATA[toUser]]></ToUserName>
 <FromUserName><![CDATA[FromUser]]></FromUserName>
 <CreateTime>123456789</CreateTime>
 <MsgType><![CDATA[event]]></MsgType>
 <Event><![CDATA[subscribe]]></Event>
 <EventKey><![CDATA[qrscene_123123]]></EventKey>
 <Ticket><![CDATA[TICKET]]></Ticket>
 </xml>

 2. 用户已关注时的事件推送
 <xml>
 <ToUserName><![CDATA[toUser]]></ToUserName>
 <FromUserName><![CDATA[FromUser]]></FromUserName>
 <CreateTime>123456789</CreateTime>
 <MsgType><![CDATA[event]]></MsgType>
 <Event><![CDATA[SCAN]]></Event>
 <EventKey><![CDATA[SCENE_VALUE]]></EventKey>
 <Ticket><![CDATA[TICKET]]></Ticket>
 </xml>
 */
public class InQrCodeEvent extends InMsg {

	// 1. 用户未关注时，进行关注后的事件推送： subscribe
	// 2. 用户已关注时的事件推送： SCAN
	private String event;

	// 1. 用户未关注时，进行关注后的事件推送： qrscene_123123
	// 2. 用户已关注时的事件推送： SCENE_VALUE
	private String eventKey;
	private String ticket;

	public InQrCodeEvent(String toUserName, String fromUserName, Integer createTime, String msgType) {
		super(toUserName, fromUserName, createTime, msgType);
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
}




