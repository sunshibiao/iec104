package com.csg.ioms.iec.server.master.handler;

import com.csg.ioms.iec.common.BasicInstruction104;
import com.csg.ioms.iec.message.MessageDetail;
import com.csg.ioms.iec.server.handler.ChannelHandler;
import com.csg.ioms.iec.server.handler.DataHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * I帧数据处理
 * @author Administrator
 *
 */
@Slf4j
public class SysDataHandler implements DataHandler {

	@Override
	public void handlerAdded(ChannelHandler ctx) throws Exception {
		ctx.writeAndFlush(BasicInstruction104.getEndGeneralCallRuleDetail104());
	}

	@Override
	public void channelRead(ChannelHandler ctx, MessageDetail ruleDetail104) throws Exception {
		/*
		 * log.info("启动字符：" + ruleDetail104.getStart()); log.info("字节长度：" +
		 * ruleDetail104.getApuuLength()); log.info("控制域：" +
		 * JSON.toJSONString(ruleDetail104.getControl())); log.info("类型标识：" +
		 * ruleDetail104.getTypeIdentifier().getValue()); log.info("可变结构限定词：" +
		 * ruleDetail104.isContinuous()); log.info("数据长度：" +
		 * ruleDetail104.getMeasgLength()); log.info("传输原因：" +
		 * ruleDetail104.getTransferReason()); log.info("终端地址：" +
		 * ruleDetail104.getTerminalAddress()); log.info("消息地址：" +
		 * ruleDetail104.getMessageAddress()); log.info("消息结构：" +
		 * ruleDetail104.getMessages()); log.info("是否有消息元素：" +
		 * ruleDetail104.isMessage()); log.info("判断是否有限定词：" +
		 * ruleDetail104.isQualifiers()); log.info("判断是否有时标：" +
		 * ruleDetail104.isTimeScaleExit()); log.info("判断消息是否连续：" +
		 * ruleDetail104.isContinuous()); if(ruleDetail104.getMeasgLength()>0){ for (int
		 * i = 0; i<ruleDetail104.getMeasgLength();i++) {
		 * log.info(JSON.toJSONString(ruleDetail104.getMessages().get(i))); } } try {
		 * log.info("是否有消息元素：" + ruleDetail104.getQualifiers().getValue()); }catch
		 * (Exception e){}
		 * 
		 * //log.info("限定词：" + ruleDetail104.getQualifiers().getValue()); log.info("时标："
		 * + ruleDetail104.getTimeScale()); log.info("限定词：" +
		 * ruleDetail104.getHexString());
		 * 
		 * log.info("收到消息:"+JSON.toJSONString(ruleDetail104));
		 */
		
		//Telemetry104 telemetry104=new Telemetry104();
		//telemetry104.NoTime_Point(ruleDetail104.getMessages(), ruleDetail104.getMeasgLength(),(int) ruleDetail104.getTypeIdentifier().getValue(),ruleDetail104.isContinuous()?0:1);
	}

}
