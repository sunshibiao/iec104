package com.csg.ioms.iec.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csg.ioms.iec.common.BasicInstruction104;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;


/**
 *  这是一个定时任务管理池
 * @ClassName:  ScheduledTaskPool   
 * @Description: 
 * @author: sun
 */
@Slf4j
public class ScheduledTaskPool {

	private static final Logger LOGGER = LoggerFactory.getLogger(ChannelInboundHandlerAdapter.class);

	/**
	 * 发送指令
	 */
	private ChannelHandlerContext ctx;
	/**
	 * 发送启动指令的线程
	 */
	private Thread sendStatrThread;
	/**
	 * 循环发送启动指令线程的状态
	 */
	private Boolean sendStatrStatus = false;
	/**
	 * 发送测试指令的线程 类似心跳
	 */
	private Thread sendTestThread;
	/**
	 * 发送测试指令线程的状态
	 */
	private Boolean sendTestStatus = false;
	/**
	 * 发送总召唤指令状态
	 */
	private Boolean senGeneralCallStatus = false;
	/**
	 * 启动指令收到确认后固定时间内发送总召唤指令
	 */
	private Thread generalCallTThread;

	public ScheduledTaskPool(ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}

	/**
	 *
	* @Title: sendStatrFrame
	* @Description: 发送启动帧
	 */
	public void sendStatrFrame() {
		synchronized (sendStatrStatus) {
			if (sendStatrThread != null) {
				sendStatrStatus = true;
				sendStatrThread.start();
			} else if (sendStatrThread == null) {
				sendStatrStatus = true;
				sendStatrThread = new Thread(new Runnable() {
					@Override
					public void run() {
						while (sendStatrStatus) {
							try {
								ctx.channel().writeAndFlush(BasicInstruction104.STARTDT);
								LOGGER.info("发送启动链路指令");
								Thread.sleep(5000);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				});
				sendStatrThread.start();
			}
		}
	}

	/**
	 *
	* @Title: stopSendStatrFrame
	* @Description: 停止发送确认帧
	 */
	public void stopSendStatrFrame() {
		if (sendStatrThread != null) {
			sendStatrStatus = false;
		}
	}

	
	/**
	 * 
	* @Title: sendTestFrame
	* @Description: 发送测试帧
	 */
	public void sendTestFrame() {
		synchronized (sendTestStatus) {
			if (sendTestThread != null && sendTestThread.getState() == Thread.State.TERMINATED) {
				sendTestStatus = true;
				sendTestThread.start();
			} else if (sendTestThread == null) {
				sendTestStatus = true;
				sendTestThread = new Thread(new Runnable() {
					@Override
					public void run() {
						while (sendTestStatus) {
							try {
								LOGGER.info("发送测试链路指令");
								ctx.channel().writeAndFlush(BasicInstruction104.TESTFR);
								Thread.sleep(5000);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				});
				sendTestThread.start();
			}
		}
	}
	
	/**
	 *
	* @Title: stopSendTestFrame
	* @Description: 停止发送测试帧
	 */
	public void stopSendTestFrame() {
		if (sendTestThread != null) {
			sendTestStatus = false;
		}
	}

	/**
	 *
	* @Title: sendGeneralCall
	* @Description: 发送总召唤
	 */
	public void sendGeneralCall() {
		synchronized (senGeneralCallStatus) {
			if (generalCallTThread != null && generalCallTThread.getState() == Thread.State.TERMINATED) {
				senGeneralCallStatus = true;
				generalCallTThread.start();
			} else if (generalCallTThread == null) {
				senGeneralCallStatus = true;
				generalCallTThread = new Thread(new Runnable() {
					@Override
					public void run() {
						while (sendTestStatus) {
							try {
								LOGGER.info("发送总召唤指令");
								ctx.channel().writeAndFlush(BasicInstruction104.getGeneralCallRuleDetail104());
								Thread.sleep(1000 * 60);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				});
				generalCallTThread.start();
			}
		}
	}

}
