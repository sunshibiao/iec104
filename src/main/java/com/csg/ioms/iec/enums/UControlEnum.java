package com.csg.ioms.iec.enums;

import lombok.Getter;

/**
 * U帧 基本指令
 *
 */
public enum UControlEnum {
	/**
	 * 测试命令
	 */
	TESTFR(0x43000000),
	/**
	 * 测试确认指令
	 */
	TESTFR_YES(0x83000000),
	/**
	 * 停止指令
	 */
	STOPDT(0x13000000),
	/**
	 * 停止确认
	 */
	STOPDT_YES(0x23000000),
	/**
	 * 启动命令
	 */
	STARTDT(0x07000000),
	/**
	 * 启动确认命令
	 */
	STARTDT_YES(0x0B000000);

	@Getter
	private  int value;

	UControlEnum(int value) {
		this.value = value;
	}

}
