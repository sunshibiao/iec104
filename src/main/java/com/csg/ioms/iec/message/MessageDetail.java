package com.csg.ioms.iec.message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.csg.ioms.iec.enums.QualifiersEnum;
import com.csg.ioms.iec.enums.TypeIdentifierEnum;
import com.csg.ioms.iec.utils.Iec104Util;

import lombok.Data;

/**
 *  一条报文对应的消息体
 * @author sun
 */
@Data
public class MessageDetail {
	
	/**
	 * 启动字符 固定 一个字节
	 */
	private byte start = 0x68;
	
	/**
	 * APUU 长度1个字节
	 */
	private int apuuLength = 0;
	
	/**
	 * 控制域 四个字节
	 */
	private byte[] control;
	
	
	/**
	 * 类型标识 1字节
	 */
	private TypeIdentifierEnum typeIdentifier;
	
	
	/**
	 * 可变结构限定词 1个字节 
	 * true SQ = 0   true 数目number 是 信息对象的数目
	 * false SQ = 1 单个对象的信息元素或者信息元素的集合的数目
	 */
	
	private boolean  isContinuous;

	/**
	 * 消息长度
	 */
	private int measgLength;
	/**
	 * 传输原因 两个字节
	 */
	private  short transferReason;
	
	/**
	 *  终端地址 也就是应用服务数据单元公共地址
	 */
	private short terminalAddress;
	
	/**
	 * 消息地址 字节
	 */
	private int messageAddress;
	
	/**
	 * 消息结构
	 */
	private List<MessageInfo> messages;
	
	
	/**
	 * 判断是否有消息元素
	 */
	private boolean isMessage;
	/**
	 * 判断是否有限定词
	 */
	private boolean isQualifiers;
	/**
	 * 判断是否有时标
	 */
	private boolean isTimeScaleExit;
	
	private QualifiersEnum qualifiers;
	/**
	 * 
	 * 时标
	 */
	private  Date timeScale;

	/**
	 * 十六进制 字符串
	 */
	private String hexString;

	public MessageDetail() {
	}

	/**
	 * 
	 * @param control 控制域
	 * @param typeIdentifierEnum 类型标识
	 * @param sq  0 地址不连续  1 地址连续
	 * @param isTest 传输原因  0 未试验 1 试验
	 * @param isPn 肯定确认 和否定确认
	 * @param transferReason 传输原因 后六个比特位
	 * @param terminalAddress 服务地址
	 * @param messageAddress 消息地址
	 * @param messages 消息列表
	 * @param timeScale 时间
	 * @param qualifiers 限定词
	 * @return
	 */
	public MessageDetail(byte[] control, TypeIdentifierEnum typeIdentifierEnum, boolean sq,
						 boolean isTest, boolean isPn, short transferReason, short terminalAddress, int messageAddress,
						 List<MessageInfo> messages, Date timeScale, QualifiersEnum qualifiers) {
		this.control = control;
		this.typeIdentifier = typeIdentifierEnum;
		this.isContinuous = sq;
		this.measgLength = messages.size();
		this.transferReason = Iec104Util.getTransferReasonShort(isTest, isPn, transferReason);
		this.messages = messages;
		this.terminalAddress = terminalAddress;
		this.timeScale = timeScale;
		if (timeScale != null) {
			this.isTimeScaleExit = true;
		}
		this.qualifiers = qualifiers;
	}
	
	
	/**
	 *  U 帧或者S帧
	 * @param control 控制域
	 */
	public MessageDetail(byte[] control) {
		this.control = control;
		this.messages = new ArrayList<>();
	}
	
}
