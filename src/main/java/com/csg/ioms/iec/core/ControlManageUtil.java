package com.csg.ioms.iec.core;

import com.csg.ioms.iec.common.Iec104Constant;
import com.csg.ioms.iec.message.MessageDetail;
import com.csg.ioms.iec.utils.Iec104Util;

import io.netty.channel.ChannelHandlerContext;

/**
 * 控制域的管理工具
 */
public class ControlManageUtil {

	/**
	 *  发送序号
	 */
	private Short send;

	/**
	 * 接收序号
	 */
	private Short accept;

	/**
	 * 接收到帧的数量
	 */
	private Short frameAmount;

	/**
	 *  发送S帧的锁
	 */
	private Boolean sendSframeLock;

	/**
	 * 接收到帧的数量最大阀值
	 */

	private short frameAmountMax;

	/**
	 * 发送消息句柄
	 */
	private ChannelHandlerContext ctx;
	

	
	public ControlManageUtil(ChannelHandlerContext ctx) {
		send = 0;
		accept = 0;
		frameAmount = 0;
		sendSframeLock = true;
		frameAmountMax = 1;
		this.ctx = ctx;
	}

	/**
	 * 启动S发送S确认帧 的任务
	 */
	public void startSendFrameTask() {
		Runnable runnable = () -> {
			while (true) {
				try {
					synchronized (sendSframeLock) {
						if (frameAmount >= frameAmountMax) {
							// 查过最大帧 的数量就要发送一个确认帧出去
							byte[] control = Iec104Util.getScontrol(accept);
							MessageDetail ruleDetail104 = new MessageDetail(control);
							ctx.channel().writeAndFlush(Encoder104.encoder(ruleDetail104));
							frameAmount = 0;
						}
						sendSframeLock.wait();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		CachedThreadPool.getCachedThreadPool().execute(runnable);
	}
	
	
	/**
	 * 返回当前的发送序号
	 */
	public short getSend() {
		synchronized (send) {
			short sendRule = this.send;
			this.send++;
			if (send > Iec104Constant.SEND_MAX) {
				send = Iec104Constant.SEND_MIN;
			}
			return sendRule;
		}
	}
	public short getAccept() {
		return accept;
	} 
	
	/**
	 * 
	* @Title: setAccept
	* @Description: 设置接收序号
	* @param lastAccept
	 */
	public void setAccept(short lastAccept) {
		synchronized (sendSframeLock) {
			this.accept = lastAccept;
			frameAmount++;
			if (frameAmount >= frameAmountMax) {
				this.accept = lastAccept;
				sendSframeLock.notifyAll();
			}
		}
	}


	public ControlManageUtil  setFrameAmountMax(short frameAmountMax) {
		this.frameAmount = frameAmountMax;
		return  this;
	}
}
