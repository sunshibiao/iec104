package com.csg.ioms.iec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.csg.ioms.iec.config.Iec104Config;
import com.csg.ioms.iec.server.Iec104MasterFactory;
import com.csg.ioms.iec.server.master.handler.SysDataHandler;

@SpringBootApplication
public class Iec104Application {

	public static void main(String[] args) {
		try {
			SpringApplication.run(Iec104Application.class, args);
			Iec104Config iec104Config  = new Iec104Config();
			iec104Config.setFrameAmountMax((short) 1);
			iec104Config.setTerminnalAddress((short) 1);
			Iec104MasterFactory.createTcpClientMaster("192.168.6.112", 2404).setDataHandler(new SysDataHandler()).setConfig(iec104Config).run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
