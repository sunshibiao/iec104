package com.csg.ioms.iec.common;

/**
 * 
* @ClassName: Iec104Constant  
* @Description: TODO 
* @author sun 
 */
public class Iec104Constant {
	
	/**
	 * 开始字符
	 */
	public static final  byte  HEAD_DATA = 0x68;
	
	/**
	 * 控制域长度
	 */
	public static final  byte CPNTROL_LENGTH = 0x04;
	
	/**
	 * APCI 长度
	 */
	public static final byte APCI_LENGTH = 0x06;

	/**
	 * APCI 中 发送序号低位坐标
	 */
	public static final int ACCEPT_LOW_INDEX = 2;
	
	/**
	 * APCI 中 发送序号高位坐标
	 */
	public static final int ACCEPT_HIGH_INDEX = 3;


	/**
	 *最大接收序号
	 */
	public static final Short SEND_MAX = 32767;

	/**
	 * 最小接收序号
	 */
	public static final Short SEND_MIN = 0;


//	/**
//	 *
//	 */
//	public static final short FRAME_SUM_MAX  = 1;
//
//	/**
//	 * 终端地址
//	 */
//	public static final short TERMINNAL_ADDRESS = 1;
}
