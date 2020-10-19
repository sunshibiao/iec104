package com.csg.ioms.iec.server.handler;

import com.csg.ioms.iec.message.MessageDetail;

/**
 * 
 * @ClassName:  ChannelHandler   
 * @Description: 处理数据
 * @author: YDL
 * @date:   2020年5月19日 上午11:41:58
 */
public interface ChannelHandler {
	void writeAndFlush(MessageDetail ruleDetail104);
}
