package com.csg.ioms.iec.enums;

/**
 * 
* @ClassName: TypeIdentifierEnum   类型标识
* @author sun
 */
public enum TypeIdentifierEnum {
	
	/**
	 * 单点摇信
	 */
	onePointTeleindication(0x01),
	/**
	 * 双点摇信
	 */
	twoPointTeleindication(0x03),
	/**
	 * 测量值 归一化值 遥测
	 */
	normalizedTelemetry(0x09),
	/**
	 * 测量值  标度化值 遥测
	 */
	scaledTelemetry(0x0B),
	/**
	 * 测量值 短浮点数 遥测   Short floating point
	 */
	shortFloatingPointTelemetry(0x0D),
	/**
	 * 摇信带时标 单点
	 */
	onePointTimeTeleindication(0x1E),
	/**
	 * 摇信带时标 双点
	 */
	twoPointTimeTeleindication(0x1F),
	/**
	 * 单命令 遥控
	 */
	onePointTelecontrol(0x2D),
	/**
	 * 双命令遥控
	 */
	twoPointTelecontrol(0x2E),
	/**
	 * 读单个参数
	 */
	readOneParameter(0x66),
	/**
	 * 读多个参数
	 */
	readMultipleParameter(0x84),
	/**
	 * 预置单个参数命令
	 */
	prefabActivationOneParameter(0x30),
	/**
	 * 预置多个个参数
	 */
	prefabActivationMultipleParameter(0x88),
	/**
	 * 初始化结束
	 */
	initEnd(0x46),
	/**
	 * 召唤命令
	 */
	generalCall(0x64),
	/**
	 * 时钟同步
	 */
	timeSynchronization(0x67),
	/**
	 * 复位进程
	 */
	resetPprocess(0x69);

	private byte value;
	TypeIdentifierEnum(int value) {
		this.value = (byte) value;
	}
	public byte getValue() {
		return value;
	}
	
	public static TypeIdentifierEnum getTypeIdentifierEnum(byte value) {
		for (TypeIdentifierEnum type : TypeIdentifierEnum.values()) {
			if (type.getValue() == value) {
				return type;
			}
		}
		return null;
	}
}
