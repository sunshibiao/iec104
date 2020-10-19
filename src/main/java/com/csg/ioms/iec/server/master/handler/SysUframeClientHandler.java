package com.csg.ioms.iec.server.master.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csg.ioms.iec.core.Iec104ThreadLocal;
import com.csg.ioms.iec.enums.UControlEnum;
import com.csg.ioms.iec.utils.ByteUtil;
import com.csg.ioms.iec.utils.Iec104Util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 
* @ClassName: SysUframeInboundHandler  
* @Description: 处理U帧的报文 
* @author sun 
 */
public class SysUframeClientHandler extends ChannelInboundHandlerAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(SysUframeClientHandler.class);
	
	/**
	 * 拦截系统消息 
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf result = (ByteBuf) msg;
		byte[] bytes = new byte[result.readableBytes()];
		result.readBytes(bytes);
		if (isSysInstruction(bytes)) {
			UControlEnum uControlEnum = Iec104Util.getUcontrol(ByteUtil.getByte(bytes, 2, 4));
			if (uControlEnum != null) {
				uInstructionHandler(ctx, result, uControlEnum);
				return;
			}
		} 
		result.writeBytes(bytes);
		ctx.fireChannelRead(result);
	}

	/**
	 * 
	* @Title: isSysInstruction  
	* @Description: TODO  判断是否是 系统报文
	* @param @param bytes
	* @param @return 
	* @return boolean   
	* @throws
	 */
	private boolean isSysInstruction(byte[] bytes) {
		// U帧只有6字节
		return bytes.length == 6;
	}
	
	/**
	 * 
	* @Title: uInstructionHandler  
	* @Description: 处理U帧 
	* @param ctx
	* @param result
	* @param uControlEnum 
	* @return void   
	* @throws
	 */
	private void uInstructionHandler(ChannelHandlerContext ctx, ByteBuf result, UControlEnum uControlEnum) {
		result.readBytes(new byte[result.readableBytes()]);
		if (uControlEnum == UControlEnum.TESTFR_YES) {
			LOGGER.info("收到测试确认指令");
			Iec104ThreadLocal.getScheduledTaskPool().sendGeneralCall();
		} else if (uControlEnum == UControlEnum.STOPDT_YES) {
			LOGGER.info("收到停止确认指令");
		} else if (uControlEnum == UControlEnum.STARTDT_YES) {
			LOGGER.info("收到启动指令确认指令");
			Iec104ThreadLocal.getScheduledTaskPool().stopSendStatrFrame();
			Iec104ThreadLocal.getScheduledTaskPool().sendTestFrame();
		} else {
			LOGGER.error("U报文无效");
		}
	}
}
