package com.csg.ioms.iec.server.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csg.ioms.iec.common.Iec104Constant;
import com.csg.ioms.iec.utils.ByteUtil;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * 
* @ClassName: Check104Handler  
* @Description: 检查104报文 
* @author sun 
 */
public class Check104Handler extends ChannelInboundHandlerAdapter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Check104Handler.class);
	
	/**
	 * 拦截系统消息
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf result = (ByteBuf) msg;
		byte[] bytes = new byte[result.readableBytes()];
		result.readBytes(bytes);
		LOGGER.info("接收到的报文: " + ByteUtil.byteArrayToHexString(bytes));
		if (bytes.length < Iec104Constant.APCI_LENGTH || bytes[0] != Iec104Constant.HEAD_DATA) {
			LOGGER.error("报文无效");
			ReferenceCountUtil.release(result);
		} else {
			result.writeBytes(bytes);
			ctx.fireChannelRead(msg);
		}
	}
}
