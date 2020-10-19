package com.csg.ioms.iec.config;

import lombok.Data;

/**
 * 104规约的配置
 */
@Data
public class Iec104Config {

    /**
     * 接收到帧的数量到该值就要发一个确认帧
     */
    private short frameAmountMax;


    /**
     * 终端地址
     */
    private short terminnalAddress;
}
