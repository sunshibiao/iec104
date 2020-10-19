package com.csg.ioms.iec.common;

import java.util.ArrayList;
import java.util.List;

import com.csg.ioms.iec.enums.QualifiersEnum;
import com.csg.ioms.iec.enums.TypeIdentifierEnum;
import com.csg.ioms.iec.enums.UControlEnum;
import com.csg.ioms.iec.message.MessageDetail;
import com.csg.ioms.iec.message.MessageInfo;
import com.csg.ioms.iec.utils.ByteUtil;
import com.csg.ioms.iec.utils.Iec104Util;

/**
 * 104 规约的基本指令封装
* @ClassName: BasicInstruction104  
* @Description: 返回指定的指令 
* @author sun 
 */

public class BasicInstruction104 {
	// 68040B 00 00 00
	/**
	 * 初始确认指令
	 */
	public static final byte[] STARTDT_YES = new byte[] {0x68, 0x04, 0x0B, 0x00, 0x00, 0x00};
	
	/**
	 * 链路启动指令
	 */
	public static final byte[] STARTDT = new byte[] {0x68, 0x04, 0x07, 0x00, 0x00, 0x00};
	
	
	/**
	 * 测试确认
	 */
	public static final byte[] TESTFR_YES = new byte[] {0x68, 0x04, (byte) 0x83, 0x00, 0x00, 0x00};
	 
	/**
	 * 测试命令指令
	 */
	public static final byte[] TESTFR = new byte[] {0x68, 0x04, (byte) 0x43, 0x00, 0x00, 0x00};
	
	
	/**
	 * 停止确认
	 */
	public static final byte[] STOPDT_YES = new byte[] {0x68, 0x04, 0x23, 0x00, 0x00, 0x00};
	
	
	
	
	
	/**
	 * 
	* @Title: getGeneralCallRuleDetail104  
	* @Description: 总召唤指令 
	* @param @return
	* @param @throws IOException 
	* @return MessageDetail
	* @throws
	 */
	public static MessageDetail getGeneralCallRuleDetail104() {
		TypeIdentifierEnum typeIdentifierEnum = TypeIdentifierEnum.generalCall;
		int sq = 0;
		boolean isContinuous = sq == 0 ? false : true;
		// 接收序号
		short accept = 0;
		// 发送序号
		short send = 0;
		byte[] control = Iec104Util.getIcontrol(accept, send);
		// 传输原因
		short transferReason = 6;
		boolean isTest = false;
		boolean isPn = true;
		// 终端地址 实际发生的时候会被替换
		short terminalAddress = 1;
		// 消息地址 总召唤地址为0
		int messageAddress = 0;
		QualifiersEnum qualifiers = QualifiersEnum.generalCallGroupingQualifiers;
		List<MessageInfo> messages = new ArrayList<>();
		MessageInfo message = new MessageInfo();
		message.setQualifiers(qualifiers);
		message.setMessageInfos(new byte[] {});
		messages.add(message);
		MessageDetail ruleDetail104 = new MessageDetail(control, typeIdentifierEnum, isContinuous, isTest, isPn, transferReason,
				terminalAddress, messageAddress, messages, null, null);
		return ruleDetail104;
	}
	
	
	/**
	 * 
	* @Title: getYesGeneralCallRuleDetail104  
	* @Description: 总召唤确认指令 
	* @return 
	* @return MessageDetail
	* @throws
	 */
	public static MessageDetail getYesGeneralCallRuleDetail104() {
		TypeIdentifierEnum typeIdentifierEnum = TypeIdentifierEnum.generalCall; 
		 //SQ=0 length =1
		int sq = 0;
		boolean isContinuous = sq == 0 ? false : true;
		// 接收序号
		short accept = 0;
		// 发送序号
		short send = 0;
		byte[] control = Iec104Util.getIcontrol(accept, send);
		// 传输原因
		short transferReason = 7;
		// true：1 ; false ： 0
		boolean isTest = false;
		// true:0 false;1
		boolean isPN = true;
		
		short terminalAddress = 1;
		// 消息地址 总召唤地址为0
		int messageAddress = 0;
		
		QualifiersEnum qualifiers = QualifiersEnum.generalCallGroupingQualifiers;
		List<MessageInfo> messages = new ArrayList<>();
		MessageInfo message = new MessageInfo();
		message.setQualifiers(qualifiers);
		message.setMessageInfos(new byte[] {});
		
		messages.add(message);
		MessageDetail ruleDetail104 = new MessageDetail(control, typeIdentifierEnum, isContinuous, isTest, isPN, transferReason,
				terminalAddress, messageAddress, messages, null, null);
		return ruleDetail104;
	}
	
	
	/**
	 * 
	* @Title: getEndGeneralCallRuleDetail104  
	* @Description: 总召唤结束指令 
	* @return 
	* @return MessageDetail
	* @throws
	 */
	public static MessageDetail getEndGeneralCallRuleDetail104() {
		TypeIdentifierEnum typeIdentifierEnum = TypeIdentifierEnum.generalCall; 
		 //SQ=0 length =1
		int sq = 0;
		boolean isContinuous = sq == 0 ? false : true;
		// 接收序号
		short accept = 1;
		// 发送序号
		short send = 4;
		byte[] control = Iec104Util.getIcontrol(accept, send);
		// 传输原因
		short transferReason = 0x0A;
		// true：1 ; false ： 0
		boolean isTest = false;
		// true:0 false;1
		boolean isPN = true;
		
		short terminalAddress = 1;
		// 消息地址 总召唤地址为0
		int messageAddress = 0;
		// 老板限定词
		QualifiersEnum qualifiers = QualifiersEnum.generalCallGroupingQualifiers;
		List<MessageInfo> messages = new ArrayList<>();
		MessageInfo message = new MessageInfo();
		message.setQualifiers(qualifiers);
		message.setMessageInfos(new byte[] {});
		
		messages.add(message);
		MessageDetail ruleDetail104 = new MessageDetail(control, typeIdentifierEnum, isContinuous, isTest, isPN, transferReason,
				terminalAddress, messageAddress, messages, null, null);
		return ruleDetail104;
	}

	public static MessageDetail getInitRuleDetail104() {
		byte[] control = ByteUtil.intToByteArray(UControlEnum.STARTDT.getValue());
		MessageDetail ruleDetail104 = new MessageDetail(control);
		return ruleDetail104;
	}
}
