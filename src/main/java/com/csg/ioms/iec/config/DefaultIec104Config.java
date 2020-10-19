package com.csg.ioms.iec.config;

import lombok.Data;

/**
 * 默认的配置
 */
@Data
public class DefaultIec104Config extends Iec104Config {

    public DefaultIec104Config() {
        setFrameAmountMax((short) 1);
        setTerminnalAddress((short) 1);
    }
}
