package com.csg.ioms.iec.message;

import java.util.Date;

import com.csg.ioms.iec.enums.QualifiersEnum;

import lombok.Data;

/**
 * 报文中 的消息部分
 */
@Data
public class MessageInfo {
	/**
	 * 消息地址 字节
	 */
	private int messageAddress;
	
	/**
	 * 信息元素集合 1 2 4 个字节
	 */
	private byte[] messageInfos;

	/**
	 * 限定词
	 */
	private QualifiersEnum qualifiers;
	/**
	 * 
	 * 时标
	 */
	private  Date timeScale;

	/**
	 * 消息详情
	 */
	private int messageInfoLength;

}
