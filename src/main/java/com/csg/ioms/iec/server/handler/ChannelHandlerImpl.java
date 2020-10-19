package com.csg.ioms.iec.server.handler;

import com.csg.ioms.iec.message.MessageDetail;

import io.netty.channel.ChannelHandlerContext;

/**
 * 
 * @ClassName:  ChannelHandlerImpl   
 * @Description: 实现一个自定义发现消息的类
 * @author: sun
 */
public class ChannelHandlerImpl implements  ChannelHandler {
	
	private ChannelHandlerContext ctx;
	
	public ChannelHandlerImpl(ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}

	@Override
	public void writeAndFlush(MessageDetail ruleDetail104) {
		ctx.channel().writeAndFlush(ruleDetail104);
	}

}
