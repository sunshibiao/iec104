package com.csg.ioms.iec.server;

import com.csg.ioms.iec.server.master.Iec104TcpClientMaster;

/**
 * 主站 工厂类
 * @ClassName:  Iec104MasterFactory   
 * @Description: IEC104规约主站
 * @author: sun
 */
public class Iec104MasterFactory {

 

	/**
	* @Title: createTcpClientMaster
	* @Description: 创建一个TCM客户端的104主站
	* @param host 从机地址
	* @param port 端口
	* @return
	 */
	public static  Iec104Master createTcpClientMaster(String host, int port) {
		return new Iec104TcpClientMaster(host, port);
	}
}
