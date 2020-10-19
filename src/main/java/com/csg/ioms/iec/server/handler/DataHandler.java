package com.csg.ioms.iec.server.handler;

import com.csg.ioms.iec.message.MessageDetail;

/**
 * 
 * @ClassName:  DataHandler   
 * @Description:  数据处理
 * @author: sun
 */
public interface DataHandler {

	/**
	 * 
	* @Title: handlerAdded
	* @Description: 建立连接
	* @param ctx
	* @throws Exception
	 */
	void handlerAdded(ChannelHandler ctx) throws Exception;
	
	/**
	 * 
	* @Title: channelRead0
	* @Description: 收到消息
	* @param ctx
	* @param ruleDetail104
	* @throws Exception
	 */
	void channelRead(ChannelHandler ctx, MessageDetail ruleDetail104) throws Exception;
}
