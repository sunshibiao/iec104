package com.csg.ioms.iec.server.master.handler;


import java.io.IOException;

import com.csg.ioms.iec.core.CachedThreadPool;
import com.csg.ioms.iec.core.ControlManageUtil;
import com.csg.ioms.iec.core.Iec104ThreadLocal;
import com.csg.ioms.iec.core.ScheduledTaskPool;
import com.csg.ioms.iec.message.MessageDetail;
import com.csg.ioms.iec.server.handler.ChannelHandlerImpl;
import com.csg.ioms.iec.server.handler.DataHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class Iec104ClientHandler extends SimpleChannelInboundHandler<MessageDetail> {

	private DataHandler dataHandler;

	public Iec104ClientHandler(DataHandler dataHandler) {
		this.dataHandler = dataHandler;
	}
	
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// 启动成功后一直发启动链路命令
		Iec104ThreadLocal.setScheduledTaskPool(new ScheduledTaskPool(ctx));
		Iec104ThreadLocal.getScheduledTaskPool().sendStatrFrame();
		Iec104ThreadLocal.setControlPool(new ControlManageUtil(ctx).setFrameAmountMax(Iec104ThreadLocal.getIec104Conig().getFrameAmountMax()));
		Iec104ThreadLocal.getControlPool().startSendFrameTask();

		if (dataHandler != null) {
			CachedThreadPool.getCachedThreadPool().execute(new Runnable() {
				@Override
				public void run() {
					try {
						dataHandler.handlerAdded(new ChannelHandlerImpl(ctx));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
        }
	}
	
	@Override
	public void channelRead0(ChannelHandlerContext ctx, MessageDetail ruleDetail104) throws IOException {
		if (dataHandler != null) {
			CachedThreadPool.getCachedThreadPool().execute(new Runnable() {
    			@Override
    			public void run() {
    				try {
						dataHandler.channelRead(new ChannelHandlerImpl(ctx), ruleDetail104);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    		});
    	}
	}


}
