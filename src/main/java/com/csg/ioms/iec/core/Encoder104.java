package com.csg.ioms.iec.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.csg.ioms.iec.message.MessageDetail;
import com.csg.ioms.iec.message.MessageInfo;
import com.csg.ioms.iec.utils.ByteUtil;
import com.csg.ioms.iec.utils.Iec104Util;

/**
 *
 * @author sun
 *
 */
public class Encoder104 {


	public static byte[] encoder(MessageDetail ruleDetail104) throws IOException {
		Iec104Util.setMeaageAttribute(ruleDetail104);
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		bytes.write(ruleDetail104.getStart());
		byte[]  apduBytes = getApduBytes(ruleDetail104);
		int messageLen =  apduBytes.length;
		ruleDetail104.setApuuLength(messageLen);
		bytes.write((byte) messageLen);
		bytes.write(apduBytes);
		return bytes.toByteArray();
	}
	
	private static byte[] getApduBytes(MessageDetail ruleDetail104) throws IOException {
		ByteArrayOutputStream bOutput = new ByteArrayOutputStream();
		//  控制域
		bOutput.write(ruleDetail104.getControl());
		if (ruleDetail104.getTypeIdentifier() == null) {
			// U帧或者S帧
			return bOutput.toByteArray();
		} 
		// 类型标识
		bOutput.write((byte) ruleDetail104.getTypeIdentifier().getValue());
		// 可变结构限定词
		bOutput.write(Iec104Util.getChangedQualifiers(ruleDetail104));
		// 传输原因
		bOutput.write(ByteUtil.shortToByteArray(ruleDetail104.getTransferReason()));
		// 终端地址
		bOutput.write((Iec104Util.getTerminalAddressByte(ruleDetail104.getTerminalAddress())));
//		如果是是连续的则数据地址 只需要在开头写以后的数据单元就不需要再写了
		if (ruleDetail104.isContinuous()) {
			bOutput.write(Iec104Util.intToMessageAddress(ruleDetail104.getMessageAddress()));
			// 地址只取三个字节
			if (ruleDetail104.isMessage()) {
				for (MessageInfo ruleDetail104Message : ruleDetail104.getMessages()) {
					bOutput.write(ruleDetail104Message.getMessageInfos());
				}
			} 
			if (ruleDetail104.isQualifiers()) {
				bOutput.write(ruleDetail104.getQualifiers().getValue());
			} 
			if (ruleDetail104.isTimeScaleExit()) {
				bOutput.write(ByteUtil.date2Hbyte(ruleDetail104.getTimeScale()));
			} 
		} else {
			for (MessageInfo ruleDetail104Message : ruleDetail104.getMessages()) {
				bOutput.write(Iec104Util.intToMessageAddress(ruleDetail104Message.getMessageAddress()));
				if (ruleDetail104.isMessage()) {
					bOutput.write(ruleDetail104Message.getMessageInfos());
				} 
				if (ruleDetail104.isQualifiers()) {
					bOutput.write(ruleDetail104Message.getQualifiers().getValue());
				} 
				if (ruleDetail104.isTimeScaleExit()) {
					bOutput.write(ByteUtil.date2Hbyte(ruleDetail104Message.getTimeScale()));
				} 
			}
		}
		return bOutput.toByteArray();
	}

}
