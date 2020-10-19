package com.csg.ioms.iec.server;

import com.csg.ioms.iec.config.Iec104Config;
import com.csg.ioms.iec.server.handler.DataHandler;

/**
 * 主站抽象类
 */
public interface Iec104Master {

	/**
	 * 服务启动方法
	 * @throws Exception
	 */
	void run() throws Exception;
	
	/**
	 * 
	* @Title: setDataHandler
	* @Description: 设置数据处理类
	* @param dataHandler
	 */
	Iec104Master setDataHandler(DataHandler dataHandler);

	/**
	 * 设置配置文件
	 * @param iec104Confiig
	 * @return
	 */
	Iec104Master setConfig(Iec104Config iec104Confiig);

}
